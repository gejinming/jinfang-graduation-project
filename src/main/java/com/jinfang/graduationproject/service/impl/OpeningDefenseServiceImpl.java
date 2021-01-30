package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.domain.CcTeacher;
import com.jinfang.graduationproject.domain.GpOpeningDefense;
import com.jinfang.graduationproject.domain.GpOpeningDefenseScore;
import com.jinfang.graduationproject.domain.GpOpeningReport;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpOpeningDefenseMapper;
import com.jinfang.graduationproject.mapper.GpOpeningDefenseScoreMapper;
import com.jinfang.graduationproject.service.*;
import com.jinfang.graduationproject.vo.defense.SubjectStudentOpeningDefenseRes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class OpeningDefenseServiceImpl implements OpeningDefenseService {

    private TeacherService teacherService;
    private GpOpeningDefenseMapper gpOpeningDefenseMapper;
    private GpOpeningDefenseScoreMapper gpOpeningDefenseScoreMapper;
    private CcScoreStuIndigradeService ccScoreStuIndigradeService;
    private GpSubjectStudentService gpSubjectStudentService;
    private GpOpeningReportService gpOpeningReportService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(GpOpeningDefense record) {
        int rows = gpOpeningDefenseMapper.add(record);
        if (rows <= 0) {
            return 0;
        }

        if (CollectionUtils.isEmpty(record.getScoreList())) {
            log.warn("No scores from {}", record);
            return 0;
        }

        Long studentId = gpSubjectStudentService.getStudentId(record.getSubjectStudentId());

        for (GpOpeningDefenseScore openingDefenseScore : record.getScoreList()) {
            openingDefenseScore.setOpeningDefenseId(record.getId());

            boolean isOk = ccScoreStuIndigradeService.add(openingDefenseScore.getCourseGradecomposeId(),
                    openingDefenseScore.getIndicationId(), studentId,
                    openingDefenseScore.getScore());

            if (!isOk) {
                throw new RuntimeException("Insert scoreStuIndigrade failed, rollback");
            }
        }

        return gpOpeningDefenseScoreMapper.batchInsert(record.getScoreList());
    }

    @Override
    public int delete(GpOpeningDefense record) {
        return 0;
    }

    @Override
    public int delete(List<GpOpeningDefense> records) {
        return 0;
    }

    @Override
    public GpOpeningDefense findById(Long id) {
        return gpOpeningDefenseMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, gpOpeningDefenseMapper, "findPage",
                pageRequest.getAttrValue("studentName"),
                pageRequest.getAttrValue("majorName"),
                pageRequest.getAttrValue("subjectName"),
                pageRequest.getAttrValue("subjectType"),
                pageRequest.getAttrValue("subjectSource"),
                pageRequest.getExtProps().get("grade"),
                pageRequest.getExtProps().get("schoolId"));
    }

    private String getOpeningReportSuggest(Long subjectStudentId) {
        GpOpeningReport gpOpeningReport = gpOpeningReportService.getBySubjectStudentId(subjectStudentId);
        if (gpOpeningReport == null) {
            log.warn("Empty opening report result by subjectStudentId[{}]", subjectStudentId);
            return "";
        }

        return StringUtils.isEmpty(gpOpeningReport.getApproveSuggest()) ? "" :
                gpOpeningReport.getApproveSuggest();

    }

    @Override
    public GpOpeningDefense findDefenseBySubjectStudentId(Long subjectStudentId) {
        GpOpeningDefense openingDefense = gpOpeningDefenseMapper.selectDefenseBySubjectStudentId(subjectStudentId);
        if (openingDefense == null) {
            log.warn("Empty result by subjectStudentId[{}]", subjectStudentId);
            return null;
        }

        if (StringUtils.isNotEmpty(openingDefense.getOperateUser())) {
            try {
                CcTeacher ccTeacher = teacherService.findById(Long.valueOf(openingDefense.getOperateUser()));
                if (ccTeacher != null) {
                    openingDefense.setHeadmanName(ccTeacher.getName());
                }
            } catch (NumberFormatException e) {
                log.warn("Find teacher failed, teacherId:{}", openingDefense.getOperateUser(), e);
            }
        }

        return openingDefense;
    }

    @Override
    public SubjectStudentOpeningDefenseRes findBySubjectStudentId(Long subjectStudentId) {
        SubjectStudentOpeningDefenseRes res = gpOpeningDefenseMapper.selectBySubjectStudentId(subjectStudentId);
        if (res == null) {
            log.warn("Empty result by subjectStudentId[{}]", subjectStudentId);
            return null;
        }

        res.setOpeningReportSuggest(getOpeningReportSuggest(subjectStudentId));

        return res;
    }


    @Override
    public SubjectStudentOpeningDefenseRes findByOpeningDefenseId(Long openingDefenseId) {
        SubjectStudentOpeningDefenseRes res = gpOpeningDefenseMapper.selectByOpeningDefenseId(openingDefenseId);
        if (res == null) {
            log.error("Empty result by openingDefenseId[{}]", openingDefenseId);
            return null;
        }

        res.setOpeningReportSuggest(getOpeningReportSuggest(res.getSubjectStudentId()));

        // 设置评分信息
        res.setScoreList(gpOpeningDefenseScoreMapper.findByOpeningDefenseId(openingDefenseId));

        return res;
    }

}

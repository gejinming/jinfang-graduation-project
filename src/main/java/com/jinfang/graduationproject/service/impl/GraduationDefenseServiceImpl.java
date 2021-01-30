package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.domain.CcTeacher;
import com.jinfang.graduationproject.domain.GpGraduationDefense;
import com.jinfang.graduationproject.domain.GpGraduationDefenseScore;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpGraduationDefenseMapper;
import com.jinfang.graduationproject.mapper.GpGraduationDefenseScoreMapper;
import com.jinfang.graduationproject.service.*;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import com.jinfang.graduationproject.vo.defense.GraduationDefenseScoreReq;
import com.jinfang.graduationproject.vo.defense.SubjectStudentGraduationDefenseRes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class GraduationDefenseServiceImpl implements GraduationDefenseService {

    private GpGraduationDefenseMapper gpGraduationDefenseMapper;
    private GpGraduationDefenseScoreMapper gpGraduationDefenseScoreMapper;
    private GpSubjectStudentService gpSubjectStudentService;
    private CcScoreStuIndigradeService ccScoreStuIndigradeService;
    private DefenseTeacherService defenseTeacherService;
    private TeacherService teacherService;

    @Override
    public int save(GpGraduationDefense record) {
        return gpGraduationDefenseMapper.add(record);
    }

    @Override
    public int delete(GpGraduationDefense record) {
        return 0;
    }

    @Override
    public int delete(List<GpGraduationDefense> records) {
        return 0;
    }

    @Override
    public GpGraduationDefense findById(Long id) {
        return gpGraduationDefenseMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, gpGraduationDefenseMapper, "findPage",
                pageRequest.getAttrValue("studentName"),
                pageRequest.getAttrValue("majorName"),
                pageRequest.getAttrValue("subjectName"),
                pageRequest.getAttrValue("subjectType"),
                pageRequest.getAttrValue("subjectSource"),
                pageRequest.getExtProps().get("grade"),
                (Long) pageRequest.getExtProps().get("schoolId"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult score(GraduationDefenseScoreReq graduationDefenseScoreReq) {
        if (graduationDefenseScoreReq == null || graduationDefenseScoreReq.getId() == null) {
            log.error("Illegal arguments graduationDefenseScoreReq[{}]", graduationDefenseScoreReq);
            return HttpResult.error("记录无效");
        }

        if (CollectionUtils.isEmpty(graduationDefenseScoreReq.getGraduationDefenseScoreList())) {
            log.error("No scores found");
            return HttpResult.error("无评分记录");
        }

        GpGraduationDefense gpGraduationDefense = findById(graduationDefenseScoreReq.getId());

        Long studentId = gpSubjectStudentService.getStudentId(gpGraduationDefense.getSubjectStudentId());
        for (GpGraduationDefenseScore graduationDefenseScore : graduationDefenseScoreReq.getGraduationDefenseScoreList()) {
            graduationDefenseScore.setGraduationDefenseId(graduationDefenseScoreReq.getId());

            boolean isOk = ccScoreStuIndigradeService.add(graduationDefenseScore.getCourseGradecomposeId(),
                    graduationDefenseScore.getIndicationId(), studentId,
                    graduationDefenseScore.getScore());

            if (!isOk) {
                throw new RuntimeException("Insert scoreStuIndigrade failed, rollback");
            }
        }

        int rows = gpGraduationDefenseScoreMapper.batchInsert(graduationDefenseScoreReq.getGraduationDefenseScoreList());

        return HttpResult.ok(rows > 0);
    }

    @Override
    public SubjectStudentGraduationDefenseRes findBySubjectStudentId(Long subjectStudentId, LoginUserMeta userMeta) {
        SubjectStudentGraduationDefenseRes res = gpGraduationDefenseMapper.selectExtBySubjectStudentId(subjectStudentId);
        if (res == null) {
            log.error("Empty graduation record result by subjectStudentId[{}]", subjectStudentId);
            return null;
        }

        res.setDefenseGroupMembers(defenseTeacherService.findMembersByOne(userMeta.getGrade(), userMeta.getTeacherId()));
        if(StringUtils.isNotEmpty(res.getRecordUser())) {
            CcTeacher teacher = teacherService.findById(Long.valueOf(res.getRecordUser()));
            if(teacher != null) {
                res.setRecordTeacherName(teacher.getName());
            }
        }

        return res;
    }

    @Override
    public SubjectStudentGraduationDefenseRes findByGraduationDefenseId(Long graduationDefenseId) {
        SubjectStudentGraduationDefenseRes res = gpGraduationDefenseMapper.selectByGraduationDefenseId(graduationDefenseId);
        if (res == null) {
            log.error("Empty graduation record result by graduationDefenseId[{}]", graduationDefenseId);
            return null;
        }

        res.setDefenseGroupMembers(defenseTeacherService.findMembersByOne(res.getGrade(), Long.valueOf(res.getRecordUser())));

        // 设置评分信息
        res.setScoreList(gpGraduationDefenseScoreMapper.findByGraduationDefenseId(graduationDefenseId));

        return res;
    }
}

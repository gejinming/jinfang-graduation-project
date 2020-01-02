package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.domain.GpSubjectStudentScore;
import com.jinfang.graduationproject.domain.GpSubjectStudentScoreWeight;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpSubjectStudentScoreMapper;
import com.jinfang.graduationproject.mapper.GpSubjectStudentScoreWeightMapper;
import com.jinfang.graduationproject.service.SubjectStudentScoreService;
import com.jinfang.graduationproject.vo.score.SubjectStudentCompleteVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SubjectStudentScoreServiceImpl implements SubjectStudentScoreService {

    private GpSubjectStudentScoreMapper gpSubjectStudentScoreMapper;
    private GpSubjectStudentScoreWeightMapper gpSubjectStudentScoreWeightMapper;

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, gpSubjectStudentScoreMapper, "findPage",
                pageRequest.getExtProps().get("grade"),
                (Long) pageRequest.getExtProps().get("schoolId"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult save(GpSubjectStudentScoreWeight gpSubjectStudentScoreWeight) {
        HttpResult httpResult = checkScoreWeight(gpSubjectStudentScoreWeight);
        if (!httpResult.isSuccess()) {
            return httpResult;
        }

        List<GpSubjectStudentScore> originList = gpSubjectStudentScoreMapper.findByGradeIdAndSchoolId(gpSubjectStudentScoreWeight.getGrade(),
                gpSubjectStudentScoreWeight.getSchoolId());
        if (CollectionUtils.isEmpty(originList)) {
            return HttpResult.error("没有学生选题可以生成最终成绩");
        }


        ArrayList<Long> subjectStudentIds = new ArrayList<>();
        List<GpSubjectStudentScore> finalList = new ArrayList<>();
        for (GpSubjectStudentScore sss : originList) {
            GpSubjectStudentScore score = new GpSubjectStudentScore();
            score.setSubjectStudentId(sss.getId());
            if (!subjectStudentIds.contains(sss.getId())){
                subjectStudentIds.add(sss.getId());
            }
            BigDecimal reviewScore = getBDValue(sss.getReviewScore())
                    .multiply(getBDValue(gpSubjectStudentScoreWeight.getReviewWeight()));
            score.setReviewScore(reviewScore.doubleValue());

            BigDecimal translationScore = getBDValue(sss.getTranslationScore())
                    .multiply(getBDValue(gpSubjectStudentScoreWeight.getTranslationWeight()));
            score.setTranslationScore(translationScore.doubleValue());

            BigDecimal openingReportScore = getBDValue(sss.getOpeningReportScore())
                    .multiply(getBDValue(gpSubjectStudentScoreWeight.getOpeningReportWeight()));
            score.setOpeningReportScore(openingReportScore.doubleValue());

            BigDecimal openingDefenseScore = getBDValue(sss.getOpeningDefenseScore())
                    .multiply(getBDValue(gpSubjectStudentScoreWeight.getOpeningDefenseWeight()));
            score.setOpeningDefenseScore(openingDefenseScore.doubleValue());

            BigDecimal dissertationScore = getBDValue(sss.getDissertationScore())
                    .multiply(getBDValue(gpSubjectStudentScoreWeight.getDissertationWeight()));
            score.setDissertationScore(dissertationScore.doubleValue());

            BigDecimal graduationDefenseScore = getBDValue(sss.getGraduationDefenseScore())
                    .multiply(getBDValue(gpSubjectStudentScoreWeight.getGraduationDefenseWeight()));
            score.setGraduationDefenseScore(graduationDefenseScore.doubleValue());

            // 设置总分
            score.setFinalScore(reviewScore.add(translationScore).add(openingReportScore).add(openingDefenseScore)
                    .add(dissertationScore).add(graduationDefenseScore).doubleValue());

            score.setOperateUser(gpSubjectStudentScoreWeight.getOperateUser());

            score.setGrade(gpSubjectStudentScoreWeight.getGrade());
            score.setSchoolId(gpSubjectStudentScoreWeight.getSchoolId());

            finalList.add(score);
        }
        // 清除原有成绩，重新批量生成
        //TODO gjm 2021/2/10 修改 这里写的语句错误，不存在数据库字段
        gpSubjectStudentScoreMapper.deleteByGradeAndsSchoolId(subjectStudentIds);
        int rows = gpSubjectStudentScoreMapper.batchInsert(finalList);
        if (rows > 0) {
            gpSubjectStudentScoreWeightMapper.deleteByGradeAndsSchoolId(gpSubjectStudentScoreWeight.getGrade(),
                    gpSubjectStudentScoreWeight.getSchoolId());

            rows = gpSubjectStudentScoreWeightMapper.add(gpSubjectStudentScoreWeight);
        }

        return HttpResult.by(rows > 0);
    }

    private BigDecimal getBDValue(Double value) {
        return value == null ? new BigDecimal(0) : new BigDecimal(value);
    }

    private HttpResult checkScoreWeight(GpSubjectStudentScoreWeight sw) {
        if (sw == null) {
            log.error("args[gpSubjectStudentScoreWeight] is null");
            return HttpResult.error("参数无效");
        }

        BigDecimal reviewWeight = getBDValue(sw.getReviewWeight());
        BigDecimal translationWeight = getBDValue(sw.getTranslationWeight());
        BigDecimal openingReportWeight = getBDValue(sw.getOpeningReportWeight());
        BigDecimal openingDefenseWeight = getBDValue(sw.getOpeningDefenseWeight());
        BigDecimal dissertationWeight = getBDValue(sw.getDissertationWeight());
        BigDecimal graduationDefenseWeight = getBDValue(sw.getGraduationDefenseWeight());

        BigDecimal totalWeight = reviewWeight.add(translationWeight).add(openingReportWeight)
                .add(openingDefenseWeight).add(dissertationWeight).add(graduationDefenseWeight);

        if (totalWeight.doubleValue() != 1d) {
            return HttpResult.error("权重合值不为1");
        }

        return HttpResult.ok();
    }

    @Override
    public List<SubjectStudentCompleteVo> findCompleteStudents(String grade, Long schoolId) {
        return gpSubjectStudentScoreMapper.findCompleteRecord(grade, schoolId);
    }

}

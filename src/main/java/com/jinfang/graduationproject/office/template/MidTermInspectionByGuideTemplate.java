package com.jinfang.graduationproject.office.template;

import com.google.common.collect.Lists;
import com.jinfang.graduationproject.domain.GpInspection;
import com.jinfang.graduationproject.office.BaseOfficeTemplate;
import com.jinfang.graduationproject.office.FileName;
import com.jinfang.graduationproject.service.GpInspectionService;
import com.jinfang.graduationproject.vo.score.SubjectStudentCompleteVo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class MidTermInspectionByGuideTemplate extends BaseOfficeTemplate {

    private GpInspectionService gpInspectionService;

    private void setGroupVars(Map<String, Object> params, String baseWord, List<String> allWords, String currentWord) {
        for (int i = 0; i < allWords.size(); i++) {
            params.put(baseWord + (i + 1), StringUtils.isNotEmpty(currentWord) && allWords.get(i).equalsIgnoreCase(currentWord) ? "√" : " ");
        }
    }

    private String getStrVal(String val) {
        return StringUtils.isNotEmpty(val) ? val : "";
    }

    @Override
    protected Map<String, Object> transformParams(SubjectStudentCompleteVo completeVo) {

        GpInspection inspection = gpInspectionService.findBySubjectStudentId(completeVo.getSubjectStudentId());

        Map<String, Object> params = new HashMap<>();
        params.put("subjectName", completeVo.getSubjectName());
        params.put("instituteName", completeVo.getInstituteName());
        params.put("studentName", completeVo.getStudentName());
        params.put("className", completeVo.getClassName());
        params.put("teacherName", completeVo.getTeacherName());

        setGroupVars(params, "subjectNature",
                Lists.newArrayList("1", "2", "3", "4", "5"), inspection.getSubjectNature() + "");

        setGroupVars(params, "subjectMajorDegree",
                Lists.newArrayList("好", "较好", "中", "差"), inspection.getSubjectMajorDegree());

        setGroupVars(params, "subjectDifficulty",
                Lists.newArrayList("较难", "适中", "较易"), inspection.getSubjectDifficulty());

        setGroupVars(params, "reviewSummary",
                Lists.newArrayList("好", "较好", "中", "差"), inspection.getReviewSummary());


        params.put("reviewAmount", getStrVal(inspection.getReviewAmount()));
        params.put("reviewBibliography", getStrVal(inspection.getReviewBibliography()));
        params.put("reviewDigestAmount", getStrVal(inspection.getReviewDigestAmount()));

        setGroupVars(params, "translationRelativity",
                Lists.newArrayList("好", "较好", "中", "差"), inspection.getTranslationRelativity());

        setGroupVars(params, "translationQuality",
                Lists.newArrayList("好", "较好", "中", "差"), inspection.getTranslationQuality());

        params.put("translationAmount", getStrVal(inspection.getTranslationAmount()));

        setGroupVars(params, "reviewReadiness",
                Lists.newArrayList("好", "较好", "中", "差"), inspection.getReviewReadiness());

        setGroupVars(params, "openingReportSummary",
                Lists.newArrayList("好", "较好", "中", "差"), inspection.getOpeningReportSummary());

        setGroupVars(params, "dissertationPlanExecution",
                Lists.newArrayList("是", "基本是", "否"), inspection.getDissertationPlanExecution());


        params.put("dissertationProcess", getStrVal(inspection.getDissertationProcess()));

        setGroupVars(params, "dissertationReportProcess",
                Lists.newArrayList("好", "较好", "中", "差"), inspection.getDissertationReportProcess());

        setGroupVars(params, "guideAssessmentComment",
                Lists.newArrayList("好", "较好", "中", "差"), inspection.getGuideAssessmentComment());

        setGroupVars(params, "guideWorkEvaluation",
                Lists.newArrayList("认真", "一般", "不认真"), inspection.getGuideWorkEvaluation());

        setGroupVars(params, "isAlwaysWork",
                Lists.newArrayList("是", "不是"), inspection.getIsAlwaysWork());

        params.put("dayWorkTime", getStrVal(inspection.getDayWorkTime()));
        params.put("weekWorkTime", getStrVal(inspection.getWeekWorkTime()));
        params.put("isLeave", getStrVal(inspection.getIsLeave()));
        params.put("leaveDays", getStrVal(inspection.getLeaveDays()));
        params.put("isTruancy", getStrVal(inspection.getIsTruancy()));
        params.put("truancyDays", getStrVal(inspection.getTruancyDays()));


        params.put("reason", getStrVal(inspection.getReason()));

        setGroupVars(params, "summaryEvaluation",
                Lists.newArrayList("好", "较好", "中", "差"), inspection.getSummaryEvaluation());

        params.put("suggest", inspection.getSuggest());

        return params;
    }

    @Override
    protected FileName.Mapping fileMapping() {
        return FileName.Mapping.MID_TERM_INSPECTION_BY_GUIDE;
    }

}

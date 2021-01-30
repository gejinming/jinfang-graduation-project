package com.jinfang.graduationproject.office.template;

import com.jinfang.graduationproject.domain.GpSetting;
import com.jinfang.graduationproject.office.BaseOfficeTemplate;
import com.jinfang.graduationproject.office.FileName;
import com.jinfang.graduationproject.service.GpCheckAssessmentService;
import com.jinfang.graduationproject.vo.score.SubjectStudentCompleteVo;
import com.jinfang.graduationproject.vo.teacher.checker.assessment.FindCheckAssessmentRespVo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class CheckTeacherAssessmentTemplate extends BaseOfficeTemplate {

    private GpCheckAssessmentService gpCheckAssessmentService;

    @Override
    protected Map<String, Object> transformParams(SubjectStudentCompleteVo completeVo) {
        Map<String, Object> params = new HashMap<>();
        params.put("subjectName", completeVo.getSubjectName());
        params.put("instituteName", completeVo.getInstituteName());
        params.put("studentName", completeVo.getStudentName());
        params.put("majorName", completeVo.getMajorName());
        params.put("studentNo", completeVo.getStudentNo());

        FindCheckAssessmentRespVo checkAssessment = gpCheckAssessmentService.findBySubjectIdAndStudentId(completeVo.getSubjectId(),
                completeVo.getStudentId());

        if(checkAssessment != null) {
            params.put("comment", checkAssessment.getComment());
            params.put("isPassText", (checkAssessment.getIsPass() != null && checkAssessment.getIsPass() == 2) ? "通过" : "不通过");
            params.put("score", checkAssessment.getScore());
            params.put("checkTeacherName", checkAssessment.getTeacherName());
        }

        GpSetting gpSetting = getSetting(completeVo);
        if(gpSetting !=null && gpSetting.getCheckAssessmentTime() != null) {
            params.put("assessmentDate", DateFormatUtils.format(gpSetting.getCheckAssessmentTime(), "yyyy年MM月dd日"));
        }

        return params;
    }

    @Override
    protected FileName.Mapping fileMapping() {
        return FileName.Mapping.CHECK_TEACHER_ASSESSMENT;
    }

}

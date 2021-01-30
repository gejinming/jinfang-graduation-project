package com.jinfang.graduationproject.office.template;

import com.jinfang.graduationproject.domain.GpGuideAssessment;
import com.jinfang.graduationproject.domain.GpSetting;
import com.jinfang.graduationproject.office.BaseOfficeTemplate;
import com.jinfang.graduationproject.office.FileName;
import com.jinfang.graduationproject.service.GpGuideAssessmentService;
import com.jinfang.graduationproject.vo.score.SubjectStudentCompleteVo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class GuideTeacherAssessmentTemplate extends BaseOfficeTemplate {

    private GpGuideAssessmentService gpGuideAssessmentService;

    @Override
    protected Map<String, Object> transformParams(SubjectStudentCompleteVo completeVo) {
        Map<String, Object> params = new HashMap<>();
        params.put("subjectName", completeVo.getSubjectName());
        params.put("instituteName", completeVo.getInstituteName());
        params.put("studentName", completeVo.getStudentName());
        params.put("majorName", completeVo.getMajorName());
        params.put("studentNo", completeVo.getStudentNo());

        GpGuideAssessment guideAssessment = gpGuideAssessmentService.findBySubjectIdAndStudentId(completeVo.getSubjectId(),
                completeVo.getStudentId());

        if(guideAssessment != null) {
            params.put("comment", guideAssessment.getComment());
            params.put("isPassText", (guideAssessment.getIsPass() != null && guideAssessment.getIsPass() == 2) ? "通过" : "不通过");
            params.put("score", guideAssessment.getScore());
        }

        GpSetting gpSetting = getSetting(completeVo);
        if(gpSetting !=null && gpSetting.getCheckAssessmentTime() != null) {
            params.put("assessmentDate", DateFormatUtils.format(gpSetting.getCheckAssessmentTime(), "yyyy年MM月dd日"));
        }

        params.put("teacherName", completeVo.getTeacherName());

        return params;
    }

    @Override
    protected FileName.Mapping fileMapping() {
        return FileName.Mapping.GUIDE_TEACHER_ASSESSMENT;
    }

}

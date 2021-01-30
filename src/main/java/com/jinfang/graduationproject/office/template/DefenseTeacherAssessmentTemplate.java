package com.jinfang.graduationproject.office.template;

import com.jinfang.graduationproject.domain.GpDefenseAssessment;
import com.jinfang.graduationproject.domain.GpSetting;
import com.jinfang.graduationproject.office.BaseOfficeTemplate;
import com.jinfang.graduationproject.office.FileName;
import com.jinfang.graduationproject.service.DefenseAssessmentService;
import com.jinfang.graduationproject.vo.score.SubjectStudentCompleteVo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class DefenseTeacherAssessmentTemplate extends BaseOfficeTemplate {

    private DefenseAssessmentService defenseAssessmentService;

    @Override
    protected Map<String, Object> transformParams(SubjectStudentCompleteVo completeVo) {
        Map<String, Object> params = new HashMap<>();
        params.put("subjectName", completeVo.getSubjectName());
        params.put("studentNo", completeVo.getStudentNo());
        params.put("studentName", completeVo.getStudentName());
        params.put("className", completeVo.getClassName());
        params.put("majorName", completeVo.getMajorName());

        GpDefenseAssessment gpDefenseAssessment = defenseAssessmentService.findBySubjectIdAndStudentId(completeVo.getSubjectId(), completeVo.getStudentId());
        if (gpDefenseAssessment != null) {
            params.put("content", gpDefenseAssessment.getContent());

            //1:优秀，2：良好，3：中等，4：及格，5：不合格
            params.put("level", getLevel(gpDefenseAssessment.getLevel()));

            params.put("defenseHeadman", gpDefenseAssessment.getHeadmanName());
        }

        GpSetting setting = getSetting(completeVo);
        if (setting != null && setting.getGraduactionDefenseTime() != null) {
            params.put("assessmentDate", DateFormatUtils.format(setting.getGraduactionDefenseTime(), "yyyy年MM月dd日"));
        }

        return params;
    }

    private String getLevel(int level) {
        if (level == 1) {
            return "优秀";
        } else if (level == 2) {
            return "良好";
        } else if (level == 3) {
            return "中等";
        } else if (level == 4) {
            return "及格";
        } else if (level == 5) {
            return "不合格";
        }

        return "";
    }

    @Override
    protected FileName.Mapping fileMapping() {
        return FileName.Mapping.DEFENSE_ASSESSMENT;
    }

}

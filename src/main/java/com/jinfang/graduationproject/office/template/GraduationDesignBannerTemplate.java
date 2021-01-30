package com.jinfang.graduationproject.office.template;

import com.jinfang.graduationproject.domain.GpSetting;
import com.jinfang.graduationproject.office.BaseOfficeTemplate;
import com.jinfang.graduationproject.office.FileName;
import com.jinfang.graduationproject.service.TeacherService;
import com.jinfang.graduationproject.vo.score.SubjectStudentCompleteVo;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GraduationDesignBannerTemplate extends BaseOfficeTemplate {

    @Override
    protected Map<String, Object> transformParams(SubjectStudentCompleteVo completeVo) {
        Map<String, Object> params = new HashMap<>();
        params.put("grade", completeVo.getGrade());
        params.put("subjectType", completeVo.getSubjectType() == 1 ? "毕业设计" : "毕业论文");
        params.put("subjectName", completeVo.getSubjectName());
        params.put("instituteName", completeVo.getInstituteName());
        params.put("majorName", completeVo.getMajorName());
        params.put("className", completeVo.getClassName());
        params.put("studentNo", completeVo.getStudentNo());
        params.put("studentName", completeVo.getStudentName());
        params.put("teacherName", completeVo.getTeacherName());
        params.put("teacherJobTitle", TeacherService.JobTitle.of(completeVo.getTeacherJobTitle()).getDes());

        GpSetting setting = getSetting(completeVo);
        if(setting != null && setting.getOverallFinishTime() != null) {
            params.put("overallFinishTime", DateFormatUtils.format(setting.getOverallFinishTime(), "yyyy-MM-dd"));
        }

        return params;
    }

    @Override
    protected FileName.Mapping fileMapping() {
        return FileName.Mapping.GRADUATION_DESIGN_BANNER;
    }

}

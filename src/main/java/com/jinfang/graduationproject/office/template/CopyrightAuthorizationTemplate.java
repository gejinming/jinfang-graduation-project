package com.jinfang.graduationproject.office.template;

import com.jinfang.graduationproject.office.BaseOfficeTemplate;
import com.jinfang.graduationproject.office.FileName;
import com.jinfang.graduationproject.vo.score.SubjectStudentCompleteVo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CopyrightAuthorizationTemplate extends BaseOfficeTemplate {

    @Override
    protected Map<String, Object> transformParams(SubjectStudentCompleteVo completeVo) {
        Map<String, Object> params = new HashMap<>();
        params.put("subjectName", completeVo.getSubjectName());
        params.put("studentNo", completeVo.getStudentNo());
        params.put("studentName", completeVo.getStudentName());

        return params;
    }

    @Override
    protected FileName.Mapping fileMapping() {
        return FileName.Mapping.COPYRIGHT_AUTHORIZATION;
    }

}

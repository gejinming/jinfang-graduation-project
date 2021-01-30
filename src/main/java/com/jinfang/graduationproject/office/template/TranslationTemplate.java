package com.jinfang.graduationproject.office.template;

import com.jinfang.graduationproject.domain.GpTranslation;
import com.jinfang.graduationproject.office.BaseOfficeTemplate;
import com.jinfang.graduationproject.office.FileName;
import com.jinfang.graduationproject.service.GpTranslationService;
import com.jinfang.graduationproject.vo.score.SubjectStudentCompleteVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class TranslationTemplate extends BaseOfficeTemplate {

    private GpTranslationService gpTranslationService;

    @Override
    protected Map<String, Object> transformParams(SubjectStudentCompleteVo completeVo) {
        Map<String, Object> params = new HashMap<>();

        GpTranslation translation = gpTranslationService.findBySubjectIdAndStudentId(completeVo.getSubjectId(), completeVo.getStudentId());
        if (translation != null) {
            params.put("contentEn", translation.getContentEn());
            params.put("contentCn", translation.getContentCn());
        }

        return params;
    }

    @Override
    protected FileName.Mapping fileMapping() {
        return FileName.Mapping.TRANSLATION;
    }

}

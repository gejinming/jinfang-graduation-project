package com.jinfang.graduationproject.office.template;

import com.jinfang.graduationproject.office.FileName;
import org.springframework.stereotype.Component;

@Component
public class GraduationProjectBannerTemplate extends GraduationDesignBannerTemplate {

    @Override
    protected FileName.Mapping fileMapping() {
        return FileName.Mapping.GRADUATION_PROJECT_BANNER;
    }

}

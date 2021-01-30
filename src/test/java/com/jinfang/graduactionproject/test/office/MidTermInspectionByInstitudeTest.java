package com.jinfang.graduactionproject.test.office;

import org.junit.Before;

public class MidTermInspectionByInstitudeTest extends BasicOfficeTest {

    @Before
    public void init() {
        params.put("grade", "2020");
        params.put("instituteName", "浙江科技学院");

        sourceFileName = "15-mid_term_inspection_by_institute.docx";
        targetFileName = "15-mid_term_inspection_by_institute-" + System.currentTimeMillis() + ".docx";
    }

}

package com.jinfang.graduactionproject.test.office;

import org.junit.Before;

public class CopyrightAuthorizationTest extends BasicOfficeTest {

    @Before
    public void init() {
        params.put("subjectName", "电气化在大数据中的实践应用");
        params.put("studentNo", "334444431");
        params.put("studentName", "王达");

        sourceFileName = "19-copyright_authorization.docx";
        targetFileName = "19-copyright_authorization-" + System.currentTimeMillis() + ".docx";
    }

}

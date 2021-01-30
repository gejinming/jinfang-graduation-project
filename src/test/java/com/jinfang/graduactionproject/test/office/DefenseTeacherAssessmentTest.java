package com.jinfang.graduactionproject.test.office;

import org.junit.Before;

public class DefenseTeacherAssessmentTest extends BasicOfficeTest {

    @Before
    public void init() {
        params.put("subjectName", "电气化在大数据中的实践应用");
        params.put("className", "电气化114班");
        params.put("studentName", "王达");
        params.put("majorName", "电气自动化");
        params.put("studentNo", "222998333223");

        params.put("comment", "阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的");

        params.put("level", "优秀");

        params.put("defenseHeadman", "张翼德");

        // 从系统中获取（参数：指导教师评语表时间）
        params.put("assessmentDate", "2020年9月22日");

        sourceFileName = "12-defense_assessment.docx";
        targetFileName = "12-defense_assessment-" + System.currentTimeMillis() + ".docx";
    }

}

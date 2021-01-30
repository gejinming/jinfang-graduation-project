package com.jinfang.graduactionproject.test.office;

import org.junit.Before;

public class GuideTeacherAssessmentTest extends BasicOfficeTest {

    @Before
    public void init() {
        params.put("subjectName", "电气化在大数据中的实践应用");
        params.put("instituteName", "浙江科技学院");
        params.put("studentName", "王达");
        params.put("majorName", "电气自动化");
        params.put("studentNo", "222998333223");

        params.put("comment", "阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的");

        params.put("isPassText", "通过");
        params.put("score", "89.00");

        params.put("guideTeacherName", "张守义");

        // 从系统中获取（参数：指导教师评语表时间）
        params.put("assessmentYear", "2020");
        params.put("assessmentMonth", "09");
        params.put("assessmentDay", "27");

        sourceFileName = "9-guide_teacher_assessment.docx";
        targetFileName = "9-guide_teacher_assessment-" + System.currentTimeMillis() + ".docx";
    }

}

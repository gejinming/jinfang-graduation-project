package com.jinfang.graduactionproject.test.office;

import org.junit.Before;

public class GraduationBannerTest extends BasicOfficeTest {

    @Before
    public void init() {
        params.put("grade", "2020");
        params.put("subjectType", "毕业论文");
        params.put("subjectName", "电气化在大数据中的实践应用");
        params.put("instituteName", "浙江科技学院");
        params.put("majorName", "电气自动化");
        params.put("className", "电气化114班");
        params.put("studentNo", "334444431");
        params.put("studentName", "王达");
        params.put("teacherName", "张守义");
        params.put("teacherJobTitle", "高级教师");
        params.put("overallFinishTime", "2020-10-30");

        sourceFileName = "2-graduation_banner.docx";
        targetFileName = "2-graduation_banner-" + System.currentTimeMillis() + ".docx";
    }

}

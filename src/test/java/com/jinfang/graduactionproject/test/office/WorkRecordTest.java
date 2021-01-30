package com.jinfang.graduactionproject.test.office;

import org.junit.Before;

public class WorkRecordTest extends BasicOfficeTest {

    @Before
    public void init() {
        params.put("instituteName", "浙江科技学院");
        params.put("majorName", "电气自动化");
        params.put("className", "电气化114班");
        params.put("studentName", "王达");


        params.put("subjectTime", "2020-05-10");
        params.put("subjectContent", "选题内容测试");

        params.put("openingReportTime", "2020-05-25");
        params.put("openingReportContent", "开题报告内容测试");

        params.put("collectTime", "2020-06-02");
        params.put("collectContent", "收集资料内容测试");

        params.put("firstVersionTime", "2020-06-10");
        params.put("firstVersionContent", "初稿内容测试");

        params.put("secondVersionTime", "2020-06-20");
        params.put("secondVersionContent", "二稿内容测试");

        params.put("finalVersionTime", "2020-06-25");
        params.put("finalVersionContent", "定稿内容测试");

        params.put("defenseTime", "2020-09-10");
        params.put("defenseContent", "答辩内容测试");

        sourceFileName = "7-work_record.docx";
        targetFileName = "7-work_record-" + System.currentTimeMillis() + ".docx";
    }

}

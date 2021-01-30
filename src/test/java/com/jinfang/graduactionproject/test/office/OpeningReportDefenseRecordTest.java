package com.jinfang.graduactionproject.test.office;

import org.junit.Before;

public class OpeningReportDefenseRecordTest extends BasicOfficeTest {

    @Before
    public void init() {
        params.put("studentName", "王达");
        params.put("gender", "男");
        params.put("grade", "2020");
        params.put("majorName", "电气自动化");
        params.put("teacherName", "张守义");
        params.put("subjectName", "电气化在大数据中的实践应用");

        params.put("defenseYear", "2020");
        params.put("defenseMonth", "10");
        params.put("defenseDay", "15");


        params.put("defenseAddress", "教学楼2楼");

        params.put("approveSuggest", "阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的");

        params.put("year1", "2020");
        params.put("month1", "10");
        params.put("day1", "15");

        params.put("defenseHeadman", "张扬");
        params.put("year2", "2020");
        params.put("month2", "11");
        params.put("day2", "20");

        sourceFileName = "6-opening_report_defense_record.docx";
        targetFileName = "6-opening_report_defense_record-" + System.currentTimeMillis() + ".docx";
    }

}

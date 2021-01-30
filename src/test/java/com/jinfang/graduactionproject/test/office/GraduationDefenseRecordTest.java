package com.jinfang.graduactionproject.test.office;

import org.junit.Before;

public class GraduationDefenseRecordTest extends BasicOfficeTest {

    @Before
    public void init() {
        params.put("subjectName", "电气化在大数据中的实践应用");
        params.put("gender", "男");
        params.put("majorName", "电气自动化");
        params.put("className", "电气化114班");
        params.put("studentName", "王达");
        params.put("teacherName", "宋义");

        params.put("studentContent", "阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的");

        params.put("teacherContent", "三生三世十里桃花33打开已及已已肌肤就系啊啊啊哦");

        params.put("defenseHeadman", "沈腾");

        // 从系统中获取（参数：阅卷教师评语表时间）
        params.put("defenseYear", "2020");
        params.put("defenseMonth", "10");
        params.put("defenseDay", "25");

        params.put("defenseAddress", "教学楼2楼");

        params.put("recordTeacherName", "李思彤");

        params.put("dt1", "王守义");
        params.put("dt2", "赵清华");

        // 评语表 时间
        params.put("year", "2020");
        params.put("month", "10");
        params.put("day", "25");


        sourceFileName = "11-graduaction_defense_record.docx";
        targetFileName = "11-graduaction_defense_record-" + System.currentTimeMillis() + ".docx";
    }

}

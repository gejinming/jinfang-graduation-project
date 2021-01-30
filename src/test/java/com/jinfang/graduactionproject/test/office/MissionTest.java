package com.jinfang.graduactionproject.test.office;

import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.NumbericRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import org.junit.Before;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MissionTest extends BasicOfficeTest {

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
        params.put("missionDispathTime", "2020-02-01");

        params.put("year", "2020");
        params.put("month", "02");
        params.put("day", "01");

        params.put("target", "完成33开口23来2及4及3楼及3可及342斤靠及靠4斤靠加4看了奇偶hi噢hi42 ");
        params.put("content", "阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的");

        NumbericRenderData literatureList = new NumbericRenderData(NumbericRenderData.FMT_DECIMAL,
                new ArrayList<TextRenderData>() {
                    {
                        add(new TextRenderData("文献232是的.张三.33223.234122"));
                        add(new TextRenderData("442345.张三.33223.234122"));
                        add(new TextRenderData("ss44dx.张三.33223.33222"));
                    }
                });


        params.put("literatureList", literatureList);

//        RowRenderData header = RowRenderData.build(new TextRenderData("000000", "时  间"),
//                new TextRenderData("000000", "毕业设计（论文）工作内容"));

//        RowRenderData row0 = RowRenderData.build("2020年1月20日～ 2020年3月20日", "内容232323几点");
//        RowRenderData row1 = RowRenderData.build("2020年4月10日～ 2020年6月20日", "嗷嗷欧欧吉4就");
//
//        params.put("planList", new MiniTableRenderData(header, Arrays.asList(row0, row1)));

        List<Map<String, Object>> plans = new ArrayList<>();

        Map<String, Object> plan = new HashMap<>();
        plan.put("startTime", "2020年1月20日");
        plan.put("stopTime", "2020年3月20日");
        plan.put("content", "内容232323几点");

        plans.add(plan);

        plan = new HashMap<>();
        plan.put("startTime", "2020年4月10日");
        plan.put("stopTime", "2020年6月20日");
        plan.put("content", "嗷嗷欧欧吉4就");

        plans.add(plan);

        params.put("plans", plans);

        params.put("leadTeacherName", "袁少天");


        sourceFileName = "3-mission.docx";
        targetFileName = "3-mission-" + System.currentTimeMillis() + ".docx";

        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();

        configure = Configure.newBuilder().bind("plans", policy).build();
    }

}

package com.jinfang.graduactionproject.test.office;

import com.deepoove.poi.data.NumbericRenderData;
import com.deepoove.poi.data.TextRenderData;
import org.junit.Before;

import java.util.ArrayList;

public class ReviewTest extends BasicOfficeTest {

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
        params.put("finishTime", "2020-10-21");

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

        params.put("approveSuggest", "啊啊啊啦啦啦劣迹贱兮兮及金额IQ巨额利润尽全力为人家玩IE节日礼物吉尔里危机任务IER级为例；李秋菊；了建设大街2爱了几家乐山大佛");


        params.put("year", "2020");
        params.put("month", "10");
        params.put("day", "21");


        sourceFileName = "5-review.docx";
        targetFileName = "5-review-" + System.currentTimeMillis() + ".docx";
    }

}

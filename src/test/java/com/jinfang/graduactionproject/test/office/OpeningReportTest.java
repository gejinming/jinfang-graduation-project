package com.jinfang.graduactionproject.test.office;

import com.deepoove.poi.data.NumbericRenderData;
import com.deepoove.poi.data.TextRenderData;
import org.junit.Before;

import java.util.ArrayList;

public class OpeningReportTest extends BasicOfficeTest {

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
        params.put("openingReportTime", "2020-10-30");


        params.put("background", "据《史记·吕不韦列传》记载,秦始皇的母亲赵姬原是吕不韦的姬妾,吕不韦出于政治目的将已怀孕的赵姬献给异人(即秦庄襄王),后来赵姬至大期生子名政;又据《史记·秦始皇本纪》记载,“秦始皇帝者,秦庄襄王子也。庄襄王为秦质子于赵,见吕不韦姬,悦而取之,生始皇。”作为一个并不受宠爱的");

        params.put("content", "出身跟成功没有关系吧,有,肯定有,这些东西可以让你少走很多弯路,这些东西就是你成功的捷径,我们不可否认,隔壁老王家买房子的,儿子能跟你一起当销售");

        params.put("method", "杜甫出身于京兆杜氏,一个北方的大士族。其远祖为汉武帝有名的酷吏杜周。他与唐代另一大诗人即“小李杜”的杜牧同为晋代大学者、名将杜预之后。不过两");

        params.put("plan", "50%");

        NumbericRenderData reviewLiteratureList = new NumbericRenderData(NumbericRenderData.FMT_DECIMAL,
                new ArrayList<TextRenderData>() {
                    {
                        add(new TextRenderData("文献232是的.张三.33223.234122"));
                        add(new TextRenderData("442345.张三.33223.234122"));
                        add(new TextRenderData("ss44dx.张三.33223.33222"));
                    }
                });


        params.put("literatureList", reviewLiteratureList);

        params.put("approveSuggest", "阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的阿达的发的发二娃馨儿非常小的");

        // 参数表的开题报告时间
        params.put("openingReportTime", "2020-10-21");

        sourceFileName = "6-opening_report.docx";
        targetFileName = "6-opening_report-" + System.currentTimeMillis() + ".docx";
    }

}

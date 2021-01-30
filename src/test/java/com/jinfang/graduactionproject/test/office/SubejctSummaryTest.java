package com.jinfang.graduactionproject.test.office;

import com.jinfang.graduationproject.util.OfficeUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class SubejctSummaryTest {

    private static final String SOURCE_FILE_PATH = "/Users/tenx/git/jinfang-graduation-project/src/main/resources/template/";

    private static final String TARGET_FILE_PATH = "/Users/tenx/Documents/test/jinfang/target/";

    String sourceFileName;
    String targetFileName;
    final Map<String, Object> params = new HashMap<>();

    @Before
    public void init() {
        params.put("instituteName", "浙江科技学院");
        params.put("majorName", "电气自动化");
        params.put("className", "电气化114班");

        int amount = 20;

        params.put("amount", amount);

        params.put("percent", 50);

        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("no", i + 1);
            tempMap.put("studentName", "张三" + i);
            tempMap.put("subjectName", "大数据应用测试" + i);
            tempMap.put("type1", "√");
            tempMap.put("type2", "");
            tempMap.put("nature1", "");
            tempMap.put("nature2", "");
            tempMap.put("nature3", "√");
            tempMap.put("nature4", "");
            tempMap.put("nature5", "");
            tempMap.put("source1", "√");
            tempMap.put("source2", "");
            tempMap.put("source3", "");
            tempMap.put("source4", "");
            tempMap.put("source5", "");
            tempMap.put("source6", "");
            tempMap.put("address", "");
            tempMap.put("teacherName", "王"+ i);

            String score = new Random().nextInt(100) + "";

            tempMap.put("score", score);
            list.add(tempMap);
        }

        params.put("studentSubjectList", list);

        sourceFileName = "13-subject_summary.xlsx";
        targetFileName = "13-subject_summary-" + System.currentTimeMillis() + ".xlsx";


    }

    @Test
    public void test() throws Exception {
        OfficeUtil.renderExcel(SOURCE_FILE_PATH + sourceFileName,
                TARGET_FILE_PATH + targetFileName, params);
    }

}

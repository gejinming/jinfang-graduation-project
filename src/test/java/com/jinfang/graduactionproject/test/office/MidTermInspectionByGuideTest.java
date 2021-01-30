package com.jinfang.graduactionproject.test.office;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;

import java.util.List;
import java.util.Map;

public class MidTermInspectionByGuideTest extends BasicOfficeTest {

    @Before
    public void init() {
        params.put("instituteName", "浙江科技学院");
        params.put("className", "电气化114班");
        params.put("studentName", "王达");
        params.put("teacherName", "张守义");
        params.put("subjectName", "电气化在大数据中的实践应用");

        setGroupVars(params, "subjectNature",
                Lists.newArrayList("1", "2", "3", "4", "5"), "1");

        setGroupVars(params, "subjectMajorDegree",
                Lists.newArrayList("好", "较好", "中", "差"), "好");

        setGroupVars(params, "subjectDifficulty",
                Lists.newArrayList("较难", "适中", "较易"), "适中");

        setGroupVars(params, "reviewSummary",
                Lists.newArrayList("好", "较好", "中", "差"), "较好");


        params.put("reviewAmount", getStrVal("4"));
        params.put("reviewBibliography", getStrVal("34"));
        params.put("reviewDigestAmount", getStrVal("5"));

        setGroupVars(params, "translationRelativity",
                Lists.newArrayList("好", "较好", "中", "差"), "较好");

        setGroupVars(params, "translationQuality",
                Lists.newArrayList("好", "较好", "中", "差"), "中");

        params.put("translationAmount", getStrVal("23"));

        setGroupVars(params, "reviewReadiness",
                Lists.newArrayList("好", "较好", "中", "差"), "中");

        setGroupVars(params, "openingReportSummary",
                Lists.newArrayList("好", "较好", "中", "差"), "差");

        setGroupVars(params, "dissertationPlanExecution",
                Lists.newArrayList("是", "基本是", "否"), "基本是");


        params.put("dissertationProcess", getStrVal("70%"));

        setGroupVars(params, "dissertationReportProcess",
                Lists.newArrayList("好", "较好", "中", "差"), "差");

        setGroupVars(params, "guideAssessmentComment",
                Lists.newArrayList("好", "较好", "中", "差"), "较好");

        setGroupVars(params, "guideWorkEvaluation",
                Lists.newArrayList("认真", "一般", "不认真"), "不认真");

        setGroupVars(params, "isAlwaysWork",
                Lists.newArrayList("是", "不是"), "是");

        params.put("dayWorkTime", getStrVal("10"));
        params.put("weekWorkTime", getStrVal("14"));
        params.put("isLeave", getStrVal("是"));
        params.put("leaveDays", getStrVal("222"));
        params.put("isTruancy", getStrVal("31"));
        params.put("truancyDays", getStrVal("5"));


        params.put("reason", getStrVal("生病"));

        setGroupVars(params, "summaryEvaluation",
                Lists.newArrayList("好", "较好", "中", "差"), "中");

        params.put("suggest", "继续努力...");

        sourceFileName = "14-mid_term_inspection_by_guide.docx";
        targetFileName = "14-mid_term_inspection_by_guide-" + System.currentTimeMillis() + ".docx";
    }

    private void setGroupVars(Map<String, Object> params, String baseWord, List<String> allWords, String currentWord) {
        for (int i = 0; i < allWords.size(); i++) {
            params.put(baseWord + (i + 1), StringUtils.isNotEmpty(currentWord) && allWords.get(i).equalsIgnoreCase(currentWord) ? "√" : " ");
        }
    }

    private String getStrVal(String val) {
        return StringUtils.isNotEmpty(val) ? val : "";
    }

}

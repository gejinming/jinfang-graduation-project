package com.jinfang.graduationproject.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ---------------------------
 * 中期检查表 (GpInspection)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-09 13:46:25
 * 说明：
 * ---------------------------
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="中期检查操作对象")
public class GpInspection {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "学生课题ID", required = true)
    private Long subjectStudentId;

    @ApiModelProperty(value = "课题性质", example = "1:学术论文,2:工程设计 3:实验  4:理论计算  5:其他", required = true)
    private Integer subjectNature;

    @ApiModelProperty(value = "题目与专业的结合程度", example = "好/较好/中/差", required = true)
    private String subjectMajorDegree;

    @ApiModelProperty(value = "题目的难易程度", example = "较难/适中/较易", required = true)
    private String subjectDifficulty;

    @ApiModelProperty(value = "文献查阅与资料总结", example = "好/较好/中/差", required = true)
    private String reviewSummary;

    @ApiModelProperty(value = "共计篇数", required = true)
    private String reviewAmount;

    @ApiModelProperty(value = "文献查阅题录", required = true)
    private String reviewBibliography;

    @ApiModelProperty(value = "摘要共计篇数", required = true)
    private String reviewDigestAmount;

    @ApiModelProperty(value = "文献综述书面材料准备情况", example = "好/较好/中/差", required = true)
    private String reviewReadiness;

    @ApiModelProperty(value = "外文文献内容与课题的相关性", example = "好/较好/中/差", required = true)
    private String translationRelativity;

    @ApiModelProperty(value = "外文献翻译质量", example = "好/较好/中/差", required = true)
    private String translationQuality;

    @ApiModelProperty(value = "译文数量", required = true)
    private String translationAmount;

    @ApiModelProperty(value = "开题报告情况", example = "好/较好/中/差", required = true)
    private String openingReportSummary;

    @ApiModelProperty(value = "是否按原定计划进程执行", example = "好/较好/中/差", required = true)
    private String dissertationPlanExecution;

    @ApiModelProperty(value = "开题报告情况", example = "好/较好/中/差", required = true)
    private String dissertationProcess;

    @ApiModelProperty(value = "毕业实习报告或实地考察报告进展情况", example = "好/较好/中/差", required = true)
    private String dissertationReportProcess;

    @ApiModelProperty(value = "指导教师对阶段性工作的评语", example = "好/较好/中/差", required = true)
    private String guideAssessmentComment;

    @ApiModelProperty(value = "指导教师对学生工作态度的评价", example = "认真/一般/不认真", required = true)
    private String guideWorkEvaluation;

    @ApiModelProperty(value = "是否经常在设计场所（实验室、设计院（所）、机房等）工作", example = "是/不是", required = true)
    private String isAlwaysWork;

    @ApiModelProperty(value = "每天平均工作时间", required = true)
    private String dayWorkTime;

    @ApiModelProperty(value = "每周平均工作时间", required = true)
    private String weekWorkTime;

    @ApiModelProperty(value = "是否请假", required = true)
    private String isLeave;

    @ApiModelProperty(value = "请假天数")
    private String leaveDays;

    @ApiModelProperty(value = "是否旷课", required = true)
    private String isTruancy;

    @ApiModelProperty(value = "旷课天数")
    private String truancyDays;

    @ApiModelProperty(value = "主要原因")
    private String reason;

    @ApiModelProperty(value = "总体评价", example = "好/较好/中/差", required = true)
    private String summaryEvaluation;

    @ApiModelProperty(value = "对下一阶段工作的意见和建议")
    private String suggest;

    @ApiModelProperty(value="操作账号", hidden = true)
    private String operateUser;

    @ApiModelProperty(value="创建时间", hidden = true)
    private java.util.Date createDate;

    @ApiModelProperty(value="最后修改时间", hidden = true)
    private java.util.Date modifyDate;


    @ApiModelProperty(value="是否已删除", hidden = true)
    private Integer isDel;

}
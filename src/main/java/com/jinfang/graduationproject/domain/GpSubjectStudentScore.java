package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 学生课题最终分数表 (GpSubjectStudentScore)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-20 00:37:29
 * 说明：
 * ---------------------------
 */
public class GpSubjectStudentScore {

    /**
     *
     */
    private Long id;

    /**
     * 届别
     */
    private String grade;
    /**
     * 学校ID
     */
    private Long schoolId;

    /**
     * 课题学生ID
     */
    private Long subjectStudentId;
    /**
     * 综合文献得分
     */
    private Double reviewScore;
    /**
     * 外文翻译得分
     */
    private Double translationScore;
    /**
     * 开题报告得分
     */
    private Double openingReportScore;
    /**
     * 开题答辩得分
     */
    private Double openingDefenseScore;
    /**
     * 毕业论文得分
     */
    private Double dissertationScore;
    /**
     * 毕业答辩得分
     */
    private Double graduationDefenseScore;
    /**
     * 最终得分
     */
    private Double finalScore;
    /**
     * 操作账号
     */
    private String operateUser;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private java.util.Date createDate;
    /**
     * 最后修改时间
     */
    private java.util.Date modifyDate;
    /**
     * 是否删除 0 :未删除，1：已删除
     */
    private Integer isDel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectStudentId() {
        return subjectStudentId;
    }

    public void setSubjectStudentId(Long subjectStudentId) {
        this.subjectStudentId = subjectStudentId;
    }

    public Double getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(Double reviewScore) {
        this.reviewScore = reviewScore;
    }

    public Double getTranslationScore() {
        return translationScore;
    }

    public void setTranslationScore(Double translationScore) {
        this.translationScore = translationScore;
    }

    public Double getOpeningReportScore() {
        return openingReportScore;
    }

    public void setOpeningReportScore(Double openingReportScore) {
        this.openingReportScore = openingReportScore;
    }

    public Double getOpeningDefenseScore() {
        return openingDefenseScore;
    }

    public void setOpeningDefenseScore(Double openingDefenseScore) {
        this.openingDefenseScore = openingDefenseScore;
    }

    public Double getDissertationScore() {
        return dissertationScore;
    }

    public void setDissertationScore(Double dissertationScore) {
        this.dissertationScore = dissertationScore;
    }

    public Double getGraduationDefenseScore() {
        return graduationDefenseScore;
    }

    public void setGraduationDefenseScore(Double graduationDefenseScore) {
        this.graduationDefenseScore = graduationDefenseScore;
    }

    public Double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }

    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    public java.util.Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(java.util.Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
}
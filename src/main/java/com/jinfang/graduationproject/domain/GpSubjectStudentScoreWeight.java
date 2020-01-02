package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 学生课题最终分数权重表 (GpSubjectStudentScoreWeight)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-20 00:37:29
 * 说明：
 * ---------------------------
 */
public class GpSubjectStudentScoreWeight {

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
     * 综合文献权重
     */
    private Double reviewWeight;
    /**
     * 外文翻译权重
     */
    private Double translationWeight;
    /**
     * 开题报告权重
     */
    private Double openingReportWeight;
    /**
     * 开题答辩权重
     */
    private Double openingDefenseWeight;
    /**
     * 毕业论文权重
     */
    private Double dissertationWeight;
    /**
     * 毕业答辩权重
     */
    private Double graduationDefenseWeight;
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

    public Double getReviewWeight() {
        return reviewWeight;
    }

    public void setReviewWeight(Double reviewWeight) {
        this.reviewWeight = reviewWeight;
    }

    public Double getTranslationWeight() {
        return translationWeight;
    }

    public void setTranslationWeight(Double translationWeight) {
        this.translationWeight = translationWeight;
    }

    public Double getOpeningReportWeight() {
        return openingReportWeight;
    }

    public void setOpeningReportWeight(Double openingReportWeight) {
        this.openingReportWeight = openingReportWeight;
    }

    public Double getOpeningDefenseWeight() {
        return openingDefenseWeight;
    }

    public void setOpeningDefenseWeight(Double openingDefenseWeight) {
        this.openingDefenseWeight = openingDefenseWeight;
    }

    public Double getDissertationWeight() {
        return dissertationWeight;
    }

    public void setDissertationWeight(Double dissertationWeight) {
        this.dissertationWeight = dissertationWeight;
    }

    public Double getGraduationDefenseWeight() {
        return graduationDefenseWeight;
    }

    public void setGraduationDefenseWeight(Double graduationDefenseWeight) {
        this.graduationDefenseWeight = graduationDefenseWeight;
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

}
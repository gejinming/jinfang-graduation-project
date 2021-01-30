package com.jinfang.graduationproject.domain;

import java.util.Date;

/**
 * ---------------------------
 * 开题报告 (GpOpeningReport)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-30 00:58:44
 * 说明：
 * ---------------------------
 */
public class GpOpeningReport {

    /**
     *
     */
    private Long id;
    /**
     * 课题ID
     */
    private Long subjectId;
    /**
     * 学生ID
     */
    private Long studentId;
    /**
     * 学生学号
     */
    private String studentNo;
    /**
     * 背景与意义
     */
    private String background;
    /**
     * 研究的基本内容与拟解决的主要问题
     */
    private String content;
    /**
     * 研究的方法与技术路线
     */
    private String method;
    /**
     * 研究的总体安排与进度
     */
    private String plan;
    /**
     * 提交用户
     */
    private String submitUser;
    /**
     * 提交时间
     */
    private java.util.Date submitTime;
    /**
     * 状态  0: 未提交 1:未检查 2:已通过 3:未通过
     */
    private Integer status;
    /**
     * 审批时间
     */
    private java.util.Date approveTime;
    /**
     * 审批账号
     */
    private String approveUser;
    /**
     * 审批建议
     */
    private String approveSuggest;
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
    /**
     * 得分
     */
    private Double totalScores;
    private String name;
    private String grade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getSubmitUser() {
        return submitUser;
    }

    public void setSubmitUser(String submitUser) {
        this.submitUser = submitUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public java.util.Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(java.util.Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
    }

    public String getApproveSuggest() {
        return approveSuggest;
    }

    public void setApproveSuggest(String approveSuggest) {
        this.approveSuggest = approveSuggest;
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

    public Double getTotalScores() {
        return totalScores;
    }

    public void setTotalScores(Double totalScores) {
        this.totalScores = totalScores;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

}
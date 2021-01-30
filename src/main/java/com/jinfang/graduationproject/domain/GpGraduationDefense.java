package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 评阅老师学生关系表 (GpGraduationDefense)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-18 23:00:12
 * 说明：
 * ---------------------------
 */
public class GpGraduationDefense {

    /**
     *
     */
    private Long id;
    /**
     * 学生选题ID
     */
    private Long subjectStudentId;
    /**
     * 答辩时间
     */
    private java.util.Date defenseTime;
    /**
     * 答辩地址
     */
    private String defenseAddress;
    /**
     * 学生陈述要点
     */
    private String studentContent;
    /**
     * 教师提出的问题及学生回答要点
     */
    private String teacherContent;
    /**
     * 参与答辩组教师ID集合
     */
    private String teacherIds;
    /**
     * 记录人教师ID
     */
    private String recordUser;
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
     * 总得分
     */
    private Double totalScores;

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

    public java.util.Date getDefenseTime() {
        return defenseTime;
    }

    public void setDefenseTime(java.util.Date defenseTime) {
        this.defenseTime = defenseTime;
    }

    public String getDefenseAddress() {
        return defenseAddress;
    }

    public void setDefenseAddress(String defenseAddress) {
        this.defenseAddress = defenseAddress;
    }

    public String getStudentContent() {
        return studentContent;
    }

    public void setStudentContent(String studentContent) {
        this.studentContent = studentContent;
    }

    public String getTeacherContent() {
        return teacherContent;
    }

    public void setTeacherContent(String teacherContent) {
        this.teacherContent = teacherContent;
    }

    public String getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
    }

    public String getRecordUser() {
        return recordUser;
    }

    public void setRecordUser(String recordUser) {
        this.recordUser = recordUser;
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
}
package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 答辩组教师 (GpDefenseTeacher)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-27 00:18:12
 * 说明：
 * ---------------------------
 */
public class GpDefenseTeacher {

    /**
     *
     */
    private Long id;
    /**
     * 届别
     */
    private String grade;
    /**
     * 答辩组名
     */
    private String groupName;
    /**
     * 教师ID
     */
    private Long teacherId;
    /**
     * 教师姓名
     */
    private String teacherName;
    /**
     * 专业ID
     */
    private Long majorId;
    /**
     * 专业名称
     */
    private String majorName;
    /**
     * 角色 1：答辩组教师，2：答辩组组长
     */
    private Integer type;
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

    /**
     * 已分配学生数
     */
    private Long studentAmount;

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Long getStudentAmount() {
        return studentAmount;
    }

    public void setStudentAmount(Long studentAmount) {
        this.studentAmount = studentAmount;
    }
}
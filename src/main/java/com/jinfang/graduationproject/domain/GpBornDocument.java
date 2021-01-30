package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 学生文件资料 (GpBornDocument)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-11-03 10:51:56
 * 说明：
 * ---------------------------
 */
public class GpBornDocument {

    /**
     *
     */
    private Long id;
    /**
     * 课题ID
     */
    private Long subjectId;

    private String subjectName;

    /**
     * 学生ID
     */
    private Long studentId;

    private String studentName;
    /**
     * 指导教师ID
     */
    private Long teacherId;
    /**
     * 学校ID
     */
    private Long schoolId;
    /**
     * 届别
     */
    private String grade;
    /**
     * 生成方式  1: 定时任务,2:手动触发
     */
    private Integer bornType;
    /**
     * 文件路径（相对路径），多个以逗号隔开
     */
    private String filePath;
    /**
     * 文件模板枚举，用于最终生成中文文件
     */
    private String fileType;
    /**
     * 下载次数
     */
    private Integer downloadTimes;
    /**
     * 是否专业负责人独有，0：不是，1：是
     */
    private Integer isSuper = 0;
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

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getBornType() {
        return bornType;
    }

    public void setBornType(Integer bornType) {
        this.bornType = bornType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getDownloadTimes() {
        return downloadTimes;
    }

    public void setDownloadTimes(Integer downloadTimes) {
        this.downloadTimes = downloadTimes;
    }

    public Integer getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(Integer isSuper) {
        this.isSuper = isSuper;
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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
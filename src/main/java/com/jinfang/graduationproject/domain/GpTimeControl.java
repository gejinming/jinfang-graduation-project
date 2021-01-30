package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 时间控制 (GpTimeControl)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-27 00:18:12
 * 说明：
 * ---------------------------
 */
public class GpTimeControl {

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
     * 环节名称
     */
    private String nodeName;
    /**
     * 环节编码
     */
    private String nodeCode;
    /**
     * 具体内容
     */
    private String content;
    /**
     * 状态 0：关闭，1：开启
     */
    private Integer status;
    /**
     * 顺序
     */
    private Integer sort;
    /**
     * 开启时间
     */
    private java.util.Date openTime;
    /**
     * 开启账号
     */
    private String openUser;
    /**
     * 关闭时间
     */
    private java.util.Date closeTime;
    /**
     * 关闭账号
     */
    private String closeUser;
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

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public java.util.Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(java.util.Date openTime) {
        this.openTime = openTime;
    }

    public String getOpenUser() {
        return openUser;
    }

    public void setOpenUser(String openUser) {
        this.openUser = openUser;
    }

    public java.util.Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(java.util.Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getCloseUser() {
        return closeUser;
    }

    public void setCloseUser(String closeUser) {
        this.closeUser = closeUser;
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

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
}
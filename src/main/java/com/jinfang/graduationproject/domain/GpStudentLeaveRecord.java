package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 学生请假记录 (GpStudentLeaveRecord)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-19 15:58:01
 * 说明：  
 * ---------------------------
 */
public class GpStudentLeaveRecord {

	/**  */
	private Long id;
	/** 课题ID */
	private Long subjectId;
	/** 学生ID */
	private Long studentId;
	/** 日期 */
	private java.util.Date recordTime;
	/** 请假原因 */
	private String content;
	/** 操作账号 */
	private String operateUser;
	/** 创建时间 */
	private java.util.Date createDate;
	/** 最后修改时间 */
	private java.util.Date modifyDate;
	/** 是否删除 0 :未删除，1：已删除 */
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

	public java.util.Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(java.util.Date recordTime) {
		this.recordTime = recordTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
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
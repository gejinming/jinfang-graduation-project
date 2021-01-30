package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 学生选题统计表（更新） (GpSubjectStatistics)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-14 23:07:27
 * 说明：  
 * ---------------------------
 */
public class GpSubjectStatistics {

	/** 课题ID */
	private Long id;
	/** 课题ID */
	private Long subjectId;
	/** 未处理学生数 */
	private Integer waitingAmount;
	/** 已接收学生数 */
	private Integer acceptAmount;
	/** 已拒绝学生数 */
	private Integer rejectAmount;
	/** 状态  0: 未完成 1:已完成 */
	private Integer status;
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

	public Integer getWaitingAmount() {
		return waitingAmount;
	}

	public void setWaitingAmount(Integer waitingAmount) {
		this.waitingAmount = waitingAmount;
	}

	public Integer getAcceptAmount() {
		return acceptAmount;
	}

	public void setAcceptAmount(Integer acceptAmount) {
		this.acceptAmount = acceptAmount;
	}

	public Integer getRejectAmount() {
		return rejectAmount;
	}

	public void setRejectAmount(Integer rejectAmount) {
		this.rejectAmount = rejectAmount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
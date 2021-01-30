package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 任务书历史 (GpMissionHistory)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-19 15:58:00
 * 说明：  
 * ---------------------------
 */
public class GpMissionHistory {

	/**  */
	private Long id;
	/** 任务ID */
	private Long missionId;
	/** 课题ID */
	private Long subjectId;
	/** 学生ID */
	private Long studentId;
	/** 学生学号 */
	private String studentNo;
	/** 任务目标 */
	private String target;
	/** 内容和要求 */
	private String content;
	/** 选题时间 */
	private java.util.Date sendTime;
	/** 选题账号（教师） */
	private String sendUser;
	/** 状态  0: 未下发 1:已下发 2:已接收 */
	private Integer status;
	/** 接收时间 */
	private java.util.Date receiveTime;
	/** 接收账号（学生） */
	private String receiveUser;
	/** 备注 */
	private String remark;
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

	public Long getMissionId() {
		return missionId;
	}

	public void setMissionId(Long missionId) {
		this.missionId = missionId;
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

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public java.util.Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(java.util.Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getSendUser() {
		return sendUser;
	}

	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public java.util.Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(java.util.Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
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
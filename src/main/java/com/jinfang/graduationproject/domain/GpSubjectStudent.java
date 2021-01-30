package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 学生选题关系表一对多） (GpSubjectStudent)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-30 00:58:44
 * 说明：  
 * ---------------------------
 */
public class GpSubjectStudent {

	/**  */
	private Long id;
	/** 课题ID */
	private Long subjectId;
	/** 学生ID */
	private Long studentId;
	/** 学生学号 */
	private String studentNo;
	/** 选题时间 */
	private java.util.Date chooseTime;
	/** 选题账号（学生） */
	private String chooseUser;
	/** 状态  0: 待处理 1:已接收 2:已拒绝 */
	private Integer status;
	/** 处理时间 */
	private java.util.Date approveTime;
	/** 处理账号（教师） */
	private String approveUser;
	/** 备注 */
	private String remark;
	/** 创建时间 */
	private java.util.Date createDate;
	/** 最后修改时间 */
	private java.util.Date modifyDate;
	/** 是否删除 0 :未删除，1：已删除 */
	private Integer isDel;
	private String name;

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

	public java.util.Date getChooseTime() {
		return chooseTime;
	}

	public void setChooseTime(java.util.Date chooseTime) {
		this.chooseTime = chooseTime;
	}

	public String getChooseUser() {
		return chooseUser;
	}

	public void setChooseUser(String chooseUser) {
		this.chooseUser = chooseUser;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
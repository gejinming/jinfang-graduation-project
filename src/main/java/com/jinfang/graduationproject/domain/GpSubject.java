package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 课题表 (GpSubject)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-27 00:19:38
 * 说明：  
 * ---------------------------
 */
public class GpSubject {

	/**  */
	private Long id;
	/** 课题名称 */
	private String name;
	/** 限选学生数 */
	private Integer memberLimit;
	/** 届别/所属年级 */
	private String grade;
	/** 课题类型  1: 毕业设计,2:毕业论文 */
	private Integer type;
	/** 课题性质 1:学术论文,2:工程设计 3:实验  4:理论计算  5:其他 */
	private Integer nature;
	/** 课题来源 1: 科研 2:生产实际 3:工程实际 4:实验 5:教师研究课题 6:其他 */
	private Integer source;
	/** 论文简介 */
	private String description;
	/**  */
	private String content;
	/** 状态  0: 未提交 1:未审核 2:已通过 3:未通过 */
	private Integer status;
	/** 备注 */
	private String remark;
	/** 提交时间 */
	private java.util.Date submitTime;
	/** 指导教师id */
	private String teacherId;
	/** 指导教师姓名 */
	private String teacherName;
	/** 审核时间 */
	private java.util.Date approveTime;
	/** 审核账号 */
	private String approveUser;
	/** 审核不通过原因 */
	private String approveReason;
	/** 审核备注 */
	private String approveRemark;
	/** 复议状态， 0: 初始化、1、申请修改 2:允许修改，3:不允许修改 针对status为已通过后申请修改时触发此状态 */
	private Integer remodifyStatus;
	/** 申请重新修改提交时间 */
	private java.util.Date remodifySubmitTime;
	/** 申请重新修改账号 */
	private String remodifySubmitUser;
	/** 申请重新修改审核时间 */
	private java.util.Date remodifyApproveTime;
	/** 申请重新修改审核账号 */
	private String remodifyApproveUser;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMemberLimit() {
		return memberLimit;
	}

	public void setMemberLimit(Integer memberLimit) {
		this.memberLimit = memberLimit;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getNature() {
		return nature;
	}

	public void setNature(Integer nature) {
		this.nature = nature;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public java.util.Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(java.util.Date submitTime) {
		this.submitTime = submitTime;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
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

	public String getApproveReason() {
		return approveReason;
	}

	public void setApproveReason(String approveReason) {
		this.approveReason = approveReason;
	}

	public String getApproveRemark() {
		return approveRemark;
	}

	public void setApproveRemark(String approveRemark) {
		this.approveRemark = approveRemark;
	}

	public Integer getRemodifyStatus() {
		return remodifyStatus;
	}

	public void setRemodifyStatus(Integer remodifyStatus) {
		this.remodifyStatus = remodifyStatus;
	}

	public java.util.Date getRemodifySubmitTime() {
		return remodifySubmitTime;
	}

	public void setRemodifySubmitTime(java.util.Date remodifySubmitTime) {
		this.remodifySubmitTime = remodifySubmitTime;
	}

	public String getRemodifySubmitUser() {
		return remodifySubmitUser;
	}

	public void setRemodifySubmitUser(String remodifySubmitUser) {
		this.remodifySubmitUser = remodifySubmitUser;
	}

	public java.util.Date getRemodifyApproveTime() {
		return remodifyApproveTime;
	}

	public void setRemodifyApproveTime(java.util.Date remodifyApproveTime) {
		this.remodifyApproveTime = remodifyApproveTime;
	}

	public String getRemodifyApproveUser() {
		return remodifyApproveUser;
	}

	public void setRemodifyApproveUser(String remodifyApproveUser) {
		this.remodifyApproveUser = remodifyApproveUser;
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
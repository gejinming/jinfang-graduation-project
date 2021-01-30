package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 外文翻译历史 (GpTranslationHistory)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-13 14:12:21
 * 说明：  
 * ---------------------------
 */
public class GpTranslationHistory {

	/**  */
	private Long id;
	/** 外文翻译ID */
	private Long translationId;
	/** 课题ID */
	private Long subjectId;
	/** 学生ID */
	private Long studentId;
	/** 学生学号 */
	private String studentNo;
	/** 中文内容 */
	private String contentCn;
	/** 外文内容 */
	private String contentEn;
	/** 提交时间 */
	private java.util.Date submitTime;
	/** 提交用户 */
	private String submitUser;
	/** 状态  0: 未提交 1:未检查 2:已通过 3:未通过 */
	private Integer status;
	/** 审批时间 */
	private java.util.Date approveTime;
	/** 审批账号 */
	private String approveUser;
	/** 审批建议 */
	private String approveSuggest;
	/** 备注 */
	private String remark;
	/** 创建时间 */
	private java.util.Date createDate;
	/** 最后修改时间 */
	private java.util.Date modifyDate;
	/** 是否删除 0 :未删除，1：已删除 */
	private Integer isDel;
	/** 得分 */
	private Double totalScores;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTranslationId() {
		return translationId;
	}

	public void setTranslationId(Long translationId) {
		this.translationId = translationId;
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

	public String getContentCn() {
		return contentCn;
	}

	public void setContentCn(String contentCn) {
		this.contentCn = contentCn;
	}

	public String getContentEn() {
		return contentEn;
	}

	public void setContentEn(String contentEn) {
		this.contentEn = contentEn;
	}

	public java.util.Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(java.util.Date submitTime) {
		this.submitTime = submitTime;
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

}
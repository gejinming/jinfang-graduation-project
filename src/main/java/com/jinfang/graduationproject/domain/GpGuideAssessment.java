package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 指导教师学生评语表 (GpGuideAssessment)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-27 00:18:12
 * 说明：  
 * ---------------------------
 */
public class GpGuideAssessment {

	/**  */
	private Long id;
	/** 课题ID */
	private Long subjectId;
	/** 学生ID */
	private Long studentId;
	/** 学生学号 */
	private String studentNo;
	/** 是否同意答辩 1：不同意，2：同意 */
	private Integer isPass;
	/**   建议成绩（百分制) */
	private Integer score;
	/** 指导教师评语  */
	private String comment;
	/** 状态  0: 未填写 1:已填写 */
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

	public Integer getIsPass() {
		return isPass;
	}

	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
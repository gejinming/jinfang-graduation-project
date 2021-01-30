package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 考核成绩分析法学生课程目标成绩 (CcScoreStuIndigrade)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-07 21:30:49
 * 说明：  
 * ---------------------------
 */
public class CcScoreStuIndigrade {

	/** 编号 */
	private Long id;
	/** 创建日期 */
	private java.util.Date createDate;
	/** 修改日期 */
	private java.util.Date modifyDate;
	/** 开课课程成绩组成元素与课程目标关联 */
	private Long gradecomposeIndicationId;
	/** 学生编号 */
	private Long studentId;
	/** 成绩 */
	private Double grade;
	/** 等级明细编号 */
	private Long levelDetailId;
	/** 学生成绩类型（默认1，1：考核成绩，2：评分表） */
	private Integer type;
	/** 是否已删除 */
	private Boolean isDel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getGradecomposeIndicationId() {
		return gradecomposeIndicationId;
	}

	public void setGradecomposeIndicationId(Long gradecomposeIndicationId) {
		this.gradecomposeIndicationId = gradecomposeIndicationId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public Long getLevelDetailId() {
		return levelDetailId;
	}

	public void setLevelDetailId(Long levelDetailId) {
		this.levelDetailId = levelDetailId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

}
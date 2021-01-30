package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 开课课程成绩组成元素课程目标关联的分数范围备注 (CcGradecomposeIndicationScoreRemark)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-30 00:58:44
 * 说明：  
 * ---------------------------
 */
public class CcGradecomposeIndicationScoreRemark {

	/** 编号 */
	private Long id;
	/** 创建时间 */
	private java.util.Date createDate;
	/** 修改时间 */
	private java.util.Date modifyDate;
	/** 开课课程成绩组成元素与课程目标关联编号 */
	private Long gradecomposeIndicationId;
	/** 分数区间 */
	private String scoreSection;
	/** 分数备注 */
	private String scoreRemark;

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

	public String getScoreSection() {
		return scoreSection;
	}

	public void setScoreSection(String scoreSection) {
		this.scoreSection = scoreSection;
	}

	public String getScoreRemark() {
		return scoreRemark;
	}

	public void setScoreRemark(String scoreRemark) {
		this.scoreRemark = scoreRemark;
	}

}
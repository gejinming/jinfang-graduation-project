package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 外文翻译评分历史表 (GpTranslationScoreHistory)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-17 20:29:06
 * 说明：  
 * ---------------------------
 */
public class GpTranslationScoreHistory {

	/**  */
	private Long id;
	/** 外文翻译历史ID */
	private Long translationHistoryId;
	/** 成绩组成id */
	private Long courseGradecomposeId;
	/** 课程目标ID */
	private Long indicationId;
	/** 分数 */
	private Integer score;
	/** 创建时间 */
	private java.util.Date createDate;
	/** 最后修改时间 */
	private java.util.Date modifyDate;
	/** 是否已删除 0：未删除，1：已删除 */
	private Integer isDel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTranslationHistoryId() {
		return translationHistoryId;
	}

	public void setTranslationHistoryId(Long translationHistoryId) {
		this.translationHistoryId = translationHistoryId;
	}

	public Long getCourseGradecomposeId() {
		return courseGradecomposeId;
	}

	public void setCourseGradecomposeId(Long courseGradecomposeId) {
		this.courseGradecomposeId = courseGradecomposeId;
	}

	public Long getIndicationId() {
		return indicationId;
	}

	public void setIndicationId(Long indicationId) {
		this.indicationId = indicationId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
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
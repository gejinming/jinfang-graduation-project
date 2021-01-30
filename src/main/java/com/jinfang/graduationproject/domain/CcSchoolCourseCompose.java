package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 *  (CcSchoolCourseCompose)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-30 00:58:44
 * 说明：  
 * ---------------------------
 */
public class CcSchoolCourseCompose {

	/**  */
	private Long id;
	/** 届别 */
	private String grade;
	/** 学校ID */
	private Long schoolId;
	/** 学校ID */
	private Long courseId;
	/**  */
	private Integer isDel;
	/**  */
	private java.util.Date createDate;
	/**  */
	private java.util.Date modifyDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
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

}
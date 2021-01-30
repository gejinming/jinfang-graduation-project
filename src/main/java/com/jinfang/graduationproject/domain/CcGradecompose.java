package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 成绩组成元素，如：试卷分、作业分、实验分。表全名(certification gradecompose) (CcGradecompose)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-30 00:58:44
 * 说明：  
 * ---------------------------
 */
public class CcGradecompose {

	/** 编号 */
	private Long id;
	/** 创建日期 */
	private java.util.Date createDate;
	/** 修改日期 */
	private java.util.Date modifyDate;
	/** 成绩组成元素名称 */
	private String name;
	/** 备注 */
	private String remark;
	/** 专业编号 */
	private Long majorId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getMajorId() {
		return majorId;
	}

	public void setMajorId(Long majorId) {
		this.majorId = majorId;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

}
package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 评分角色 (GpScoreRole)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-27 00:18:12
 * 说明：  
 * ---------------------------
 */
public class GpScoreRole {

	/**  */
	private Long id;
	/** 届别 */
	private String grade;
	/** 学校ID */
	private Long schoolId;
	/** 成绩组成ID */
	private Long gradeComposeId;

	/** 成绩组成名称 */
	private String gradeComposeName;
	/** 评分角色，1:指导教师、2:评阅教师、3:答辩组教师、4:答辩组组长，多个角色以逗号隔开 */
	private String roles;
	/** 操作账号 */
	private String operateUser;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Long getGradeComposeId() {
		return gradeComposeId;
	}

	public void setGradeComposeId(Long gradeComposeId) {
		this.gradeComposeId = gradeComposeId;
	}

	public String getGradeComposeName() {
		return gradeComposeName;
	}

	public void setGradeComposeName(String gradeComposeName) {
		this.gradeComposeName = gradeComposeName;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
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

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}
}
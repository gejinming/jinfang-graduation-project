package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 参数设置 (GpSetting)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-18 23:06:14
 * 说明：  
 * ---------------------------
 */
public class GpSetting {

	/**  */
	private Long id;
	/** 届别 */
	private String grade;
	/** 学校ID */
	private Long schoolId;
	/** 指导学生数上限 */
	private Integer studentLimit;
	/** 开题答辩时间 */
	private java.util.Date openingDefenseTime;
	/** 开题答辩地点 */
	private String openingDefenseAddress;
	/** 毕业答辩时间 */
	private java.util.Date graduactionDefenseTime;
	/** 毕业答辩地点 */
	private String graduactionDefenseAddress;
	/** 总体完成时间 */
	private java.util.Date overallFinishTime;
	/** 任务书下达时间 */
	private java.util.Date missionDispathTime;
	/**  */
	private java.util.Date viewFinishTime;
	/** 开题报告时间 */
	private java.util.Date openingReportTime;
	/** 指导教师评语表时间 */
	private java.util.Date guideAssessmentTime;
	/** 评阅教师评语表时间 */
	private java.util.Date checkAssessmentTime;
	/** 状态 0：未设置，1：已设置 */
	private Integer status;
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

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getStudentLimit() {
		return studentLimit;
	}

	public void setStudentLimit(Integer studentLimit) {
		this.studentLimit = studentLimit;
	}

	public java.util.Date getOpeningDefenseTime() {
		return openingDefenseTime;
	}

	public void setOpeningDefenseTime(java.util.Date openingDefenseTime) {
		this.openingDefenseTime = openingDefenseTime;
	}

	public String getOpeningDefenseAddress() {
		return openingDefenseAddress;
	}

	public void setOpeningDefenseAddress(String openingDefenseAddress) {
		this.openingDefenseAddress = openingDefenseAddress;
	}

	public java.util.Date getGraduactionDefenseTime() {
		return graduactionDefenseTime;
	}

	public void setGraduactionDefenseTime(java.util.Date graduactionDefenseTime) {
		this.graduactionDefenseTime = graduactionDefenseTime;
	}

	public String getGraduactionDefenseAddress() {
		return graduactionDefenseAddress;
	}

	public void setGraduactionDefenseAddress(String graduactionDefenseAddress) {
		this.graduactionDefenseAddress = graduactionDefenseAddress;
	}

	public java.util.Date getOverallFinishTime() {
		return overallFinishTime;
	}

	public void setOverallFinishTime(java.util.Date overallFinishTime) {
		this.overallFinishTime = overallFinishTime;
	}

	public java.util.Date getMissionDispathTime() {
		return missionDispathTime;
	}

	public void setMissionDispathTime(java.util.Date missionDispathTime) {
		this.missionDispathTime = missionDispathTime;
	}

	public java.util.Date getViewFinishTime() {
		return viewFinishTime;
	}

	public void setViewFinishTime(java.util.Date viewFinishTime) {
		this.viewFinishTime = viewFinishTime;
	}

	public java.util.Date getOpeningReportTime() {
		return openingReportTime;
	}

	public void setOpeningReportTime(java.util.Date openingReportTime) {
		this.openingReportTime = openingReportTime;
	}

	public java.util.Date getGuideAssessmentTime() {
		return guideAssessmentTime;
	}

	public void setGuideAssessmentTime(java.util.Date guideAssessmentTime) {
		this.guideAssessmentTime = guideAssessmentTime;
	}

	public java.util.Date getCheckAssessmentTime() {
		return checkAssessmentTime;
	}

	public void setCheckAssessmentTime(java.util.Date checkAssessmentTime) {
		this.checkAssessmentTime = checkAssessmentTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

}
package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 任务计划历史	（一对多） (GpMissionPlanHistory)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-19 15:58:01
 * 说明：  
 * ---------------------------
 */
public class GpMissionPlanHistory {

	/**  */
	private Long id;
	/** 任务书历史ID */
	private Long messionHistoryId;
	/** 开始时间 */
	private java.util.Date startTime;
	/** 截止时间 */
	private java.util.Date stopTime;
	/** 计划内容 */
	private String content;
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

	public Long getMessionHistoryId() {
		return messionHistoryId;
	}

	public void setMessionHistoryId(Long messionHistoryId) {
		this.messionHistoryId = messionHistoryId;
	}

	public java.util.Date getStartTime() {
		return startTime;
	}

	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}

	public java.util.Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(java.util.Date stopTime) {
		this.stopTime = stopTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 开题报告文献关系表（一对多） (GpOpeningReportLiterature)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-14 23:07:27
 * 说明：  
 * ---------------------------
 */
public class GpOpeningReportLiterature {

	/**  */
	private Long id;
	/** 开放报告ID */
	private Long openingReportId;
	/** 文献类型 */
	private Integer type;
	/** 文件内容 */
	private String content;
	/** 显示的文献数据 */
	private String display;
	/** 顺序 */
	private Integer sort;
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

	public Long getOpeningReportId() {
		return openingReportId;
	}

	public void setOpeningReportId(Long openingReportId) {
		this.openingReportId = openingReportId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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
package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 用户表 (SysUser)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-27 14:11:59
 * 说明：  
 * ---------------------------
 */
public class CcSysUser {

	/**  */
	private Long id;
	/**  */
	private java.util.Date createDate;
	/**  */
	private java.util.Date modifyDate;
	/**  */
	private String department;
	/**  */
	private String email;
	/**  */
	private Boolean isEnabled;
	/**  */
	private Boolean isLocked;
	/**  */
	private java.util.Date lockedDate;
	/**  */
	private java.util.Date loginDate;
	/**  */
	private Integer loginFailureCount;
	/**  */
	private String loginIp;
	/**  */
	private String name;
	/**  */
	private String password;
	/**  */
	private String loginname;
	/** 用户类型(0：管理员） */
	private Integer type;
	/** 是否绑定邮箱 */
	private Boolean isBindEmail;
	/** 是否绑定手机 */
	private Boolean isBindMobile;
	/** 是否删除 */
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public java.util.Date getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(java.util.Date lockedDate) {
		this.lockedDate = lockedDate;
	}

	public java.util.Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(java.util.Date loginDate) {
		this.loginDate = loginDate;
	}

	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Boolean getIsBindEmail() {
		return isBindEmail;
	}

	public void setIsBindEmail(Boolean isBindEmail) {
		this.isBindEmail = isBindEmail;
	}

	public Boolean getIsBindMobile() {
		return isBindMobile;
	}

	public void setIsBindMobile(Boolean isBindMobile) {
		this.isBindMobile = isBindMobile;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

}
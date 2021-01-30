package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 教师表,表全名(certification teacher) (CcTeacher)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-27 14:11:59
 * 说明：  
 * ---------------------------
 */
public class CcTeacher {

	/** 编号，与sys_user的编号保持一致 */
	private Long id;
	/** 创建日期 */
	private java.util.Date createDate;
	/** 修改日期 */
	private java.util.Date modifyDate;
	/** 教师工号 */
	private String code;
	/** 教师姓名 */
	private String name;
	/** 性别,0:男,1:女 */
	private Integer sex;
	/** 生日 */
	private java.util.Date birthday;
	/** 籍贯 */
	private String nativePlace;
	/** 民族码 */
	private String nation;
	/** 政治面貌 */
	private String politics;
	/** 国籍/地区码 */
	private String country;
	/** 身份证号 */
	private String idCard;
	/** 最高学历 */
	private String highestEducation;
	/** 最高学位,1:学士，2:硕士，3:博士，4:其它，默认填其它 */
	private Integer highestDegrees;
	/** 本科毕业学校 */
	private String bachelorSchool;
	/** 本科毕业专业 */
	private String bachelorMajor;
	/** 硕士毕业学校 */
	private String masterSchool;
	/** 硕士毕业专业 */
	private String masterMajor;
	/** 博士毕业学校 */
	private String doctorateSchool;
	/** 博士毕业专业 */
	private String doctorateMajor;
	/** 来校日期 */
	private java.util.Date comeSchoolTime;
	/** 从教年月 */
	private java.util.Date startEducationYear;
	/** 职称 */
	private Integer jobTitle;
	/** 行政职务 */
	private String administrative;
	/** 手机号码1 */
	private String mobilePhone;
	/** 手机号码2，建议填短号 */
	private String mobilePhoneSec;
	/** 办公室电话号码1 */
	private String officePhone;
	/** 办公室电话号码2，建议填短号 */
	private String officePhoneSec;
	/** QQ号码 */
	private String qq;
	/** 微信号 */
	private String wechat;
	/** 邮箱 */
	private String email;
	/** 办公室地址 */
	private String officeAddress;
	/** 照片 */
	private String photo;
	/** 是否已删除 */
	private Boolean isDel;
	/** 当教师离开某一学校并未加入到系统中存在的另一所学校时，处于离职状态 */
	private Boolean isLeave;
	/** 隶属专业编号 */
	private Long majorId;
	/** 隶属学院编号 */
	private Long instituteId;
	/** 隶属学校编号 */
	private Long schoolId;

	/**
	 * 专业名称
	 */
	private String majorName;

	/**
	 * 院校名称
	 */
	private String instituteName;

	/**
	 * 学校名称
	 */
	private String schoolName;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public java.util.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getPolitics() {
		return politics;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getHighestEducation() {
		return highestEducation;
	}

	public void setHighestEducation(String highestEducation) {
		this.highestEducation = highestEducation;
	}

	public Integer getHighestDegrees() {
		return highestDegrees;
	}

	public void setHighestDegrees(Integer highestDegrees) {
		this.highestDegrees = highestDegrees;
	}

	public String getBachelorSchool() {
		return bachelorSchool;
	}

	public void setBachelorSchool(String bachelorSchool) {
		this.bachelorSchool = bachelorSchool;
	}

	public String getBachelorMajor() {
		return bachelorMajor;
	}

	public void setBachelorMajor(String bachelorMajor) {
		this.bachelorMajor = bachelorMajor;
	}

	public String getMasterSchool() {
		return masterSchool;
	}

	public void setMasterSchool(String masterSchool) {
		this.masterSchool = masterSchool;
	}

	public String getMasterMajor() {
		return masterMajor;
	}

	public void setMasterMajor(String masterMajor) {
		this.masterMajor = masterMajor;
	}

	public String getDoctorateSchool() {
		return doctorateSchool;
	}

	public void setDoctorateSchool(String doctorateSchool) {
		this.doctorateSchool = doctorateSchool;
	}

	public String getDoctorateMajor() {
		return doctorateMajor;
	}

	public void setDoctorateMajor(String doctorateMajor) {
		this.doctorateMajor = doctorateMajor;
	}

	public java.util.Date getComeSchoolTime() {
		return comeSchoolTime;
	}

	public void setComeSchoolTime(java.util.Date comeSchoolTime) {
		this.comeSchoolTime = comeSchoolTime;
	}

	public java.util.Date getStartEducationYear() {
		return startEducationYear;
	}

	public void setStartEducationYear(java.util.Date startEducationYear) {
		this.startEducationYear = startEducationYear;
	}

	public Integer getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(Integer jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getAdministrative() {
		return administrative;
	}

	public void setAdministrative(String administrative) {
		this.administrative = administrative;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getMobilePhoneSec() {
		return mobilePhoneSec;
	}

	public void setMobilePhoneSec(String mobilePhoneSec) {
		this.mobilePhoneSec = mobilePhoneSec;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getOfficePhoneSec() {
		return officePhoneSec;
	}

	public void setOfficePhoneSec(String officePhoneSec) {
		this.officePhoneSec = officePhoneSec;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

	public Boolean getIsLeave() {
		return isLeave;
	}

	public void setIsLeave(Boolean isLeave) {
		this.isLeave = isLeave;
	}

	public Long getMajorId() {
		return majorId;
	}

	public void setMajorId(Long majorId) {
		this.majorId = majorId;
	}

	public Long getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(Long instituteId) {
		this.instituteId = instituteId;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
}
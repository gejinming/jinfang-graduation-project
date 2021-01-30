package com.jinfang.graduationproject.domain;

/**
 * ---------------------------
 * 学生表，表全名(certification student) (CcStudent)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-27 14:11:59
 * 说明：
 * ---------------------------
 */
public class CcStudent {

    /**
     * 编号
     */
    private Long id;
    /**
     * 创建日期
     */
    private java.util.Date createDate;
    /**
     * 修改日期
     */
    private java.util.Date modifyDate;
    /**
     * 学号
     */
    private String studentNo;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别,0:男,1:女
     */
    private Integer sex;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 出生日期
     */
    private java.util.Date birthday;
    /**
     * 家庭地址
     */
    private String address;
    /**
     * 寝室号
     */
    private String domitory;
    /**
     * 学籍状态：1.在读，2:毕业，3:休学，4:转学，5:退学(辍学)
     */
    private Integer statue;
    /**
     * 政治面貌
     */
    private String politics;
    /**
     * 籍贯
     */
    private String nativePlace;
    /**
     * 国籍/地区码
     */
    private String country;
    /**
     * 民族码
     */
    private String nation;
    /**
     * 手机号码1
     */
    private String mobilePhone;
    /**
     * 手机号码2
     */
    private String mobilePhoneSec;
    /**
     * QQ
     */
    private String qq;
    /**
     * 微信号
     */
    private String wechat;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 照片
     */
    private String photo;
    /**
     * 入学时间
     */
    private java.util.Date matriculateDate;
    /**
     * 毕业时间
     */
    private java.util.Date graduateDate;
    /**
     * 年级，入学时间获得年级，例如2013级
     */
    private Integer grade;
    /**
     * 行政班编号
     */
    private Long classId;
    /**
     * 个人情况
     */
    private String personal;
    /**
     * 最高学历
     */
    private String highestEducation;
    /**
     * 是否已删除
     */
    private Boolean isDel;
    /**
     * 备注
     */
    private String remark;
    /**
     * 密码
     */
    private String password;


    /**
     * 班级名称
     */
    private String className;

    /**
     * 专业ID
     */
    private Long majorId;

    /**
     * 专业名称
     */
    private String majorName;

    /**
     * 院校ID
     */
    private Long instituteId;

    /**
     * 院校名称
     */
    private String instituteName;


    /**
     * 学校ID
     */
    private Long schoolId;

    /**
     * 学校ID
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

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public java.util.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.util.Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDomitory() {
        return domitory;
    }

    public void setDomitory(String domitory) {
        this.domitory = domitory;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public java.util.Date getMatriculateDate() {
        return matriculateDate;
    }

    public void setMatriculateDate(java.util.Date matriculateDate) {
        this.matriculateDate = matriculateDate;
    }

    public java.util.Date getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(java.util.Date graduateDate) {
        this.graduateDate = graduateDate;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getHighestEducation() {
        return highestEducation;
    }

    public void setHighestEducation(String highestEducation) {
        this.highestEducation = highestEducation;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(Long instituteId) {
        this.instituteId = instituteId;
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
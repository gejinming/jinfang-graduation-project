
/**
* @Description: TODO(用一句话描述该文件做什么)
* @author lz
* @date 2020年8月30日
* @version V1.0  
*/

package com.jinfang.graduationproject.vo.teacher.topic;

import java.io.Serializable;

/**
 * @Description: TODO(已接收学生信息)
 * @author lz
 * @date 2020年8月30日
 *
 */

public class SeeReceiveStudentRespVo implements Serializable{
	private static final long serialVersionUID = 7865342199633284997L;
	/** 记录ID */
	private Long id;
	/** 课题ID */
	private Long subjectId;
	/** 学生ID */
	private Long studentId;
	//学生编号
	private String studentNo;
	//学生姓名
	private String studentName;
	//手机号码
	private String mobile;
	//邮箱
	private String email;
	//班级
	private String className;
	//状态
	private Long status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	
	
	
	
	
}

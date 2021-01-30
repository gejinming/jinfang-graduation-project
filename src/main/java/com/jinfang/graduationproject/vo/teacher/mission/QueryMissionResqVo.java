/**
 * 
 */
package com.jinfang.graduationproject.vo.teacher.mission;

import java.io.Serializable;

/**
* @Description: (任务查询参数)
* @author lz
* @date 2020年8月20日
* @version V1.0
*/
public class QueryMissionResqVo implements Serializable{

	private static final long serialVersionUID = 3315355502673280298L;
	//课题id
	private String subjectId;
	//课题名称
	private String subjectName;
	//届别
	private String grade;
	//状态
	private String status;
	//操作账号
	private String teacherId;
	//学生 已接收状态
	private int studentStatus;
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public int getStudentStatus() {
		return studentStatus;
	}
	public void setStudentStatus(int studentStatus) {
		this.studentStatus = studentStatus;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}


	
}

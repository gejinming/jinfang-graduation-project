
/**
* @Description: TODO(用一句话描述该文件做什么)
* @author lz
* @date 2020年9月1日
* @version V1.0  
*/

package com.jinfang.graduationproject.vo.student.dissertation;

import java.io.Serializable;

import com.jinfang.graduationproject.domain.GpDissertation;

/**
 * @Description: TODO(查询毕业论文)
 * @author lz
 * @date 2020年9月1日
 *
 */

public class GetByStudentNoDissertationRespVo extends GpDissertation implements Serializable {

	private static final long serialVersionUID = 7393875922393169956L;
	// 课题名称
	private String subjectName;
	// 教师名
	private String teacherName;
	// 学生编号
	private String studentNo;
	// 学生姓名
	private String studentName;
	
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
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

}

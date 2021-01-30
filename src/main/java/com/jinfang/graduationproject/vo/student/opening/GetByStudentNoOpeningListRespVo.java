
/**
* @Description: TODO(用一句话描述该文件做什么)
* @author lz
* @date 2020年9月1日
* @version V1.0  
*/

package com.jinfang.graduationproject.vo.student.opening;

import java.io.Serializable;
import java.util.List;

import com.jinfang.graduationproject.domain.GpOpeningReport;
import com.jinfang.graduationproject.domain.GpOpeningReportLiterature;
import com.jinfang.graduationproject.domain.GpSubjectLiterature;

/**
 * @Description: TODO(开题报告查询)
 * @author lz
 * @date 2020年9月1日
 *
 */

public class GetByStudentNoOpeningListRespVo extends GpOpeningReport implements Serializable {

	private static final long serialVersionUID = 2893771887892554743L;
	// 课题名称
	private String subjectName;
	// 教师名
	private String teacherName;
	// 学生编号
	private String studentNo;
	// 学生姓名
	private String studentName;
	//文献数据
	private List<GpOpeningReportLiterature> literatureList;
	//课题文献数据
	private List<GpSubjectLiterature> subjectLiteratureList;

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

	public List<GpOpeningReportLiterature> getLiteratureList() {
		return literatureList;
	}

	public void setLiteratureList(List<GpOpeningReportLiterature> literatureList) {
		this.literatureList = literatureList;
	}

	public List<GpSubjectLiterature> getSubjectLiteratureList() {
		return subjectLiteratureList;
	}

	public void setSubjectLiteratureList(List<GpSubjectLiterature> subjectLiteratureList) {
		this.subjectLiteratureList = subjectLiteratureList;
	}

}


package com.jinfang.graduationproject.vo.student.review;

import java.io.Serializable;
import java.util.List;

import com.jinfang.graduationproject.domain.GpReviewLiterature;
import com.jinfang.graduationproject.domain.GpSubjectLiterature;

/**
 * @Description: TODO(文献列表)
 * @author lz
 * @date 2020年9月1日
 *
 */
public class GetByStudentNoReviewListRespVo implements Serializable {

	private static final long serialVersionUID = 7640320347370888794L;
	// 文献id
	private Long id;
	// 文献内容
	private String content;
	// 文献状态 状态 0: 未提交 1:未检查 2:已通过 3:未通过
	private int status;
	// 课题id
	private Long subjectId;
	// 课题名称
	private String subjectName;
	// 教师名
	private String teacherName;
	// 学生编号
	private String studentNo;
	// 学生姓名
	private String studentName;
	// 学生id
	private Long studentId;
	//指导教师意见
	private String approveSuggest;
	//文献综述列表
	private List<GpReviewLiterature> literatureList;
	//课题文献综述列表
	private List<GpSubjectLiterature> subjectLiteratureList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	
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

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public List<GpReviewLiterature> getLiteratureList() {
		return literatureList;
	}

	public void setLiteratureList(List<GpReviewLiterature> literatureList) {
		this.literatureList = literatureList;
	}

	public List<GpSubjectLiterature> getSubjectLiteratureList() {
		return subjectLiteratureList;
	}

	public void setSubjectLiteratureList(List<GpSubjectLiterature> subjectLiteratureList) {
		this.subjectLiteratureList = subjectLiteratureList;
	}

	public String getApproveSuggest() {
		return approveSuggest;
	}

	public void setApproveSuggest(String approveSuggest) {
		this.approveSuggest = approveSuggest;
	}

}

package com.jinfang.graduationproject.vo.student.task;

import java.io.Serializable;
import java.util.List;

import com.jinfang.graduationproject.domain.GpMission;
import com.jinfang.graduationproject.domain.GpMissionLiterature;
import com.jinfang.graduationproject.domain.GpMissionPlan;

/**
 * 
 * @Description: TODO(根据课题id查询任务列表)
 * @author lz
 * @date 2020年9月1日
 *
 */
public class GetByStuidentNoListRespVo extends GpMission implements Serializable {
	private static final long serialVersionUID = -3911627292408790671L;
	//学生姓名
	private String studentName;
	// 课题名称
	private String subjectName;
	// 老师姓名
	private String teacherName;
	// 计划列表
	private List<GpMissionPlan> planList;
	// 文献列表
	private List<GpMissionLiterature> lieratureList;

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

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public List<GpMissionPlan> getPlanList() {
		return planList;
	}

	public void setPlanList(List<GpMissionPlan> planList) {
		this.planList = planList;
	}

	public List<GpMissionLiterature> getLieratureList() {
		return lieratureList;
	}

	public void setLieratureList(List<GpMissionLiterature> lieratureList) {
		this.lieratureList = lieratureList;
	}

}

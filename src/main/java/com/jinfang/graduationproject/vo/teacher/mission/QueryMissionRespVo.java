/**
 * 
 */
package com.jinfang.graduationproject.vo.teacher.mission;

import java.io.Serializable;
import java.util.List;

import com.jinfang.graduationproject.domain.GpMission;
import com.jinfang.graduationproject.domain.GpMissionLiterature;
import com.jinfang.graduationproject.domain.GpMissionPlan;
import com.jinfang.graduationproject.domain.GpSubjectLiterature;

/**
* @Description: (查询返回)
* @author lz
* @date 2020年8月20日
* @version V1.0
*/
public class QueryMissionRespVo extends GpMission implements Serializable{

	private static final long serialVersionUID = 5129631747384878224L;
	//届别
	private String grade;
	//课题名称
	private String name;
	//学生姓名
	private String studentName;
	//学生班级
	private String className;
	//文献集合数据
	private List<GpMissionLiterature> literatureList;
	//可图文献集合数据
	private List<GpSubjectLiterature> subjectLiteratureList;
	//任务集合数据
	private List<GpMissionPlan> planList;
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<GpMissionLiterature> getLiteratureList() {
		return literatureList;
	}
	public void setLiteratureList(List<GpMissionLiterature> literatureList) {
		this.literatureList = literatureList;
	}
	public List<GpMissionPlan> getPlanList() {
		return planList;
	}
	public void setPlanList(List<GpMissionPlan> planList) {
		this.planList = planList;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public List<GpSubjectLiterature> getSubjectLiteratureList() {
		return subjectLiteratureList;
	}
	public void setSubjectLiteratureList(List<GpSubjectLiterature> subjectLiteratureList) {
		this.subjectLiteratureList = subjectLiteratureList;
	}
	
	
}

package com.jinfang.graduationproject.vo.teacher.review;

import java.io.Serializable;
import java.util.List;

import com.jinfang.graduationproject.domain.GpReview;
import com.jinfang.graduationproject.domain.GpReviewLiterature;
import com.jinfang.graduationproject.domain.GpReviewScore;

/**
 * @Description: (文献列表)
 * @author lz
 * @date 2020年8月21日
 * @version V1.0
 */
public class QueryAllResqVo extends GpReview implements Serializable {

	private static final long serialVersionUID = -1018838030633344261L;
	/** 课题名称 */
	private String name;
	/** 届别/所属年级 */
	private String grade;
	//学生姓名
	private String studentName;
	//年级
	private String className;
	// 文献综述文献集合
	private List<GpReviewLiterature> literatureList;
	//评分项集合
	private List<GpReviewScore> scoreList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public List<GpReviewLiterature> getLiteratureList() {
		return literatureList;
	}

	public void setLiteratureList(List<GpReviewLiterature> literatureList) {
		this.literatureList = literatureList;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<GpReviewScore> getScoreList() {
		return scoreList;
	}

	public void setScoreList(List<GpReviewScore> scoreList) {
		this.scoreList = scoreList;
	}
	

}

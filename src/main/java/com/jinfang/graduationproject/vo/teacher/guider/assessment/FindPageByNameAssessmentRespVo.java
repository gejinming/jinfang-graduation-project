package com.jinfang.graduationproject.vo.teacher.guider.assessment;

import java.io.Serializable;

import com.jinfang.graduationproject.domain.GpGuideAssessment;

import lombok.Data;

/**
 * 
 * @Description: 查询评语
 * @author lz
 * @date 2020年9月11日
 *
 */
@Data
public class FindPageByNameAssessmentRespVo extends GpGuideAssessment implements Serializable {

	private static final long serialVersionUID = -1538584868154912247L;
	// 届别
	private String grade;
	// 课题名称
	private String subjectName;
	// 课题id
	private Long subjectId;
	// 学生名称
	private String studentName;
	// 班级
	private String className;
	// 专业
	private String majorName;
	// 院校
	private String collegeName;

	/**
	 * 论文文件ID
	 */
	private Long dissertationFileId;

	/**
	 * 论文相似度文件ID
	 */
	private Long dissertationSimilarityFileId;
}

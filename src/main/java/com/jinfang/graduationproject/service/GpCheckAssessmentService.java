package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.GpCheckAssessment;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.vo.teacher.checker.assessment.FindCheckAssessmentRespVo;

/**
 * 
 * @Description: 评阅老师 评语
 * @author lz
 * @date 2020年9月6日
 *
 */
public interface GpCheckAssessmentService {
	/**
	 * 
	 * @Title: findCheckAssessment @Description: 根据状态 查询所有需要评学生数据 @param @param
	 *         status @param @return 参数 @return List
	 *         <FindCheckAssessmentRespVo> 返回类型 @throws
	 */
	PageResult findCheckAssessmentList(PageRequest pageRequest);

	/**
	 * 
	 * @Title: add @Description: 评阅 @param @param assessment @param @return
	 * 参数 @return int 返回类型 @throws
	 */
	int add(GpCheckAssessment assessment);

	FindCheckAssessmentRespVo findBySubjectIdAndStudentId(Long subjectId, Long studentId);
}

package com.jinfang.graduationproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinfang.graduationproject.domain.GpCheckAssessment;
import com.jinfang.graduationproject.vo.teacher.checker.assessment.FindCheckAssessmentRespVo;

/**
 * --------------------------- 审阅教师学生评语表 (GpCheckAssessmentMapper)
 * --------------------------- 作者： lz 时间： 2020-08-27 00:18:12 说明：
 * ---------------------------
 */
public interface GpCheckAssessmentMapper {

	/**
	 * 添加审阅教师学生评语表
	 * 
	 * @param record
	 * @return
	 */
	int add(GpCheckAssessment record);

	/**
	 * 删除审阅教师学生评语表
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 修改审阅教师学生评语表
	 * 
	 * @param record
	 * @return
	 */
	int update(GpCheckAssessment record);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	GpCheckAssessment findById(Long id);

	/**
	 * 基础分页查询
	 * 
	 * @param record
	 * @return
	 */
	List<GpCheckAssessment> findPage();

	/**
	 * 
	 * @Title: findCheckAssessment @Description: 根据状态 查询所有需要评学生数据 @param @param
	 * status @param @return 参数 @return List<FindCheckAssessmentRespVo>
	 * 返回类型 @throws
	 */
	List<FindCheckAssessmentRespVo> selectCheckAssessmentList(
			@Param("schoolId") Long schoolId,
			@Param("teacherId") Long teacherId,
			@Param("grade") String grade,
			@Param("name") String name,
			@Param("status") String status);

	FindCheckAssessmentRespVo selectBySubjectIdAndStudentId(@Param("subjectId") Long subjectId,@Param("studentId") Long studentId);
}
package com.jinfang.graduationproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinfang.graduationproject.domain.GpReview;
import com.jinfang.graduationproject.vo.student.review.GetByStudentNoReviewListRespVo;
import com.jinfang.graduationproject.vo.teacher.mission.QueryMissionResqVo;
import com.jinfang.graduationproject.vo.teacher.review.QueryAllResqVo;

/**
 * --------------------------- 文献综述 (GpReviewMapper) ---------------------------
 * 作者： lz 时间： 2020-08-30 00:58:44 说明： ---------------------------
 */
public interface GpReviewMapper {

	/**
	 * 添加文献综述
	 * 
	 * @param record
	 * @return
	 */
	int add(GpReview record);

	/**
	 * 删除文献综述
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 修改文献综述
	 * 
	 * @param record
	 * @return
	 */
	int update(GpReview record);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	GpReview findById(Long id);

	/**
	 * 基础分页查询
	 * 
	 * @param record
	 * @return
	 */
	List<GpReview> findPage();

	/**
	 * 基础分页查询
	 * 
	 * @param record
	 * @return
	 */
	List<QueryAllResqVo> findPageByName(QueryMissionResqVo record);

	/**
	 * 
	 * @Title: getByStudentNoReviewList @Description:
	 * TODO(根据学生编号已接收查询文献列表分页) @param @param status 状态已接收 @param @param
	 * studentNo 学生编号 @param @return 参数 @return
	 * List<GetByStudentNoReviewListRespVo> 返回类型 @throws
	 */
	List<GetByStudentNoReviewListRespVo> getByStudentNoReviewList(@Param(value="status") Integer status,@Param(value="studentNo")  String studentNo);

	GpReview selectBySubjectIdAndStudentId(@Param("subjectId") Long subjectId,@Param("studentId") Long studentId);
}
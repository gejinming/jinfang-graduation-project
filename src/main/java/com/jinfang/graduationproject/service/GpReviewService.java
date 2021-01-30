package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.GpReview;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.vo.student.review.WriteReqVo;
import com.jinfang.graduationproject.vo.teacher.review.QueryAllResqVo;

/**
 * --------------------------- 文献综述 (GpReviewService)
 * --------------------------- 作者： lz 时间： 2020-08-14 23:07:27 说明：
 * ---------------------------
 */
public interface GpReviewService extends CurdService<GpReview> {

	/**
	 * 查看/检查
	 */
	HttpResult examine(QueryAllResqVo resqVo);

	/**
	 * 
	 * @Title: getByStudentNoReviewList @Description:
	 *         TODO(根据学生编号查询文献数据) @param @param studentNo @param @return
	 *         参数 @return HttpResult 返回类型 @throws
	 */
	HttpResult getByStudentNoReviewList(PageRequest pageRequest);

	/**
	 * 
	 * @Title: write @Description: TODO(编写) @param @param reocrd @param @return
	 *         参数 @return HttpResult 返回类型 @throws
	 */
	HttpResult write(WriteReqVo reocrd);

	/**
	 * 
	 * @Title: write @Description: TODO(提交) @param @param reocrd @param @return
	 *         参数 @return HttpResult 返回类型 @throws
	 */
	HttpResult add(Long id,String studentNo);

	GpReview findBySubjectIdAndStudentId(Long subjectId, Long studentId);

}

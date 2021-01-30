package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.GpTranslation;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.vo.teacher.translate.ExamineResqVo;

/**
 * --------------------------- 外文翻译 (GpTranslationService)
 * --------------------------- 作者： lz 时间： 2020-08-14 23:07:27 说明：
 * ---------------------------
 */
public interface GpTranslationService extends CurdService<GpTranslation> {
	/**
	 * 查看/检查
	 */
	HttpResult examine(ExamineResqVo resqVo);

	/**
	 * 
	 * @Title: getByStudentNoReviewList @Description:
	 *         (根据学生编号查询外文翻译数据) @param @param studentNo @param @return
	 *         参数 @return HttpResult 返回类型 @throws
	 */
	HttpResult getByStudentNoTranslationList(PageRequest pageRequest);

	/**
	 * 
	 * @Title: write @Description: (编写) @param @param reocrd @param @return
	 *         参数 @return HttpResult 返回类型 @throws
	 */
	HttpResult write(GpTranslation reocrd);

	/**
	 * 
	 * @Title: write @Description: (提交) @param @param reocrd @param @return
	 *         参数 @return HttpResult 返回类型 @throws
	 */
	HttpResult add(Long id, String studentNo);

	GpTranslation findBySubjectIdAndStudentId(Long subjectId, Long studentId);
}

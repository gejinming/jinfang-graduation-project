package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpTranslationHistory;
import com.jinfang.graduationproject.vo.teacher.guider.HistoryListRespVo;

/**
 * --------------------------- 外文翻译历史 (GpTranslationHistoryMapper)
 * --------------------------- 作者： lz 时间： 2020-09-13 14:12:21 说明：
 * ---------------------------
 */
public interface GpTranslationHistoryMapper {

	/**
	 * 添加外文翻译历史
	 * 
	 * @param record
	 * @return
	 */
	int add(GpTranslationHistory record);

	/**
	 * 删除外文翻译历史
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 修改外文翻译历史
	 * 
	 * @param record
	 * @return
	 */
	int update(GpTranslationHistory record);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	GpTranslationHistory findById(Long id);

	/**
	 * 基础分页查询
	 * 
	 * @param record
	 * @return
	 */
	List<GpTranslationHistory> findPage();

	/**
	 * 
	 * @Title: copyTranslationInsert @Description: 根据记录id同步数据 @param @param
	 *         record @return void 返回类型 @throws
	 */
	void copyTranslationInsert(Long id);

	/**
	 * 根据id查询列表
	 * 
	 * @param
	 * @return
	 */
	List<HistoryListRespVo> getByTranslationIdList(Long translationId);

}
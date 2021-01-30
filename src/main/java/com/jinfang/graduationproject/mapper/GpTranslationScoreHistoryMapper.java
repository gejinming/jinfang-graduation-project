package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpTranslationScoreHistory;

/**
 * --------------------------- 外文翻译评分历史表 (GpTranslationScoreHistoryMapper)
 * --------------------------- 作者： lz 时间： 2020-09-17 20:29:06 说明：
 * ---------------------------
 */
public interface GpTranslationScoreHistoryMapper {

	/**
	 * 添加外文翻译评分历史表
	 * 
	 * @param record
	 * @return
	 */
	int add(GpTranslationScoreHistory record);

	/**
	 * 删除外文翻译评分历史表
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 修改外文翻译评分历史表
	 * 
	 * @param record
	 * @return
	 */
	int update(GpTranslationScoreHistory record);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	GpTranslationScoreHistory findById(Long id);

	/**
	 * 基础分页查询
	 * 
	 * @param record
	 * @return
	 */
	List<GpTranslationScoreHistory> findPage();

	/**
	 * 
	 * @Description: 根据文献id同步历史 @param @param record @return void 返回类型 @throws
	 */
	void copyTranslationIdScoreInsert(Long translationId);

}
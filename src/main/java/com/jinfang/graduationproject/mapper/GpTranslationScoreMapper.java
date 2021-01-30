package com.jinfang.graduationproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinfang.graduationproject.domain.GpTranslationScore;

/**
 * --------------------------- 外文翻译评分表 (GpTranslationScoreMapper)
 * --------------------------- 作者： lz 时间： 2020-09-03 21:49:17 说明：
 * ---------------------------
 */
public interface GpTranslationScoreMapper {

	/**
	 * 添加外文翻译评分表
	 * 
	 * @param record
	 * @return
	 */
	int add(GpTranslationScore record);

	/**
	 * 删除外文翻译评分表
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 修改外文翻译评分表
	 * 
	 * @param record
	 * @return
	 */
	int update(GpTranslationScore record);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	GpTranslationScore findById(Long id);
	
	/**
	 * 根据translationId查询
	 * 
	 * @param translationId
	 * @return
	 */
	List<GpTranslationScore> findByTranslationIdList(Long translationId);

	/**
	 * 基础分页查询
	 * 
	 * @param record
	 * @return
	 */
	List<GpTranslationScore> findPage();

	/**
	 * 
	 * @Title: addBatch @Description: 批量保存 @param @param items 参数 @return void
	 * 返回类型 @throws
	 */
	void addBatch(@Param("items") List<GpTranslationScore> items);
	
	/**
	 * 根据translationId刪除
	 * 
	 * @param translationId
	 * @return
	 */
	int deleteByTranslationId(Long translationId);

}
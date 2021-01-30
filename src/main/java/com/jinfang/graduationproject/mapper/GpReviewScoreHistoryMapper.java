package com.jinfang.graduationproject.mapper;

import com.jinfang.graduationproject.domain.GpReviewScoreHistory;

import java.util.List;

/**
 * --------------------------- 文献综述评分表 (GpReviewScoreHistoryMapper)
 * --------------------------- 作者： lz 时间： 2020-09-17 20:29:06 说明：
 * ---------------------------
 */
public interface GpReviewScoreHistoryMapper {

	/**
	 * 添加文献综述评分表
	 * 
	 * @param record
	 * @return
	 */
	int add(GpReviewScoreHistory record);

	/**
	 * 删除文献综述评分表
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 修改文献综述评分表
	 * 
	 * @param record
	 * @return
	 */
	int update(GpReviewScoreHistory record);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	GpReviewScoreHistory findById(Long id);

	/**
	 * 基础分页查询
	 * 
	 * @return
	 */
	List<GpReviewScoreHistory> findPage();

	void batchInsert(List<GpReviewScoreHistory> list);

	/**
	 * 根据文献id获取评分集合
	 * 
	 * 
	 * @param
	 * @return
	 */
	List<GpReviewScoreHistory> findByReviewHistoryIdList(Long reviewHistoryId);
}
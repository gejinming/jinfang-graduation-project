package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpOpeningReportScoreHistory;

/**
 * --------------------------- 开题报告评分历史表 (GpOpeningReportScoreHistoryMapper)
 * --------------------------- 作者： lz 时间： 2020-09-17 20:29:06 说明：
 * ---------------------------
 */
public interface GpOpeningReportScoreHistoryMapper {

	/**
	 * 添加开题报告评分历史表
	 * 
	 * @param record
	 * @return
	 */
	int add(GpOpeningReportScoreHistory record);

	/**
	 * 删除开题报告评分历史表
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 修改开题报告评分历史表
	 * 
	 * @param record
	 * @return
	 */
	int update(GpOpeningReportScoreHistory record);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	GpOpeningReportScoreHistory findById(Long id);

	/**
	 * 基础分页查询
	 * 
	 * @param record
	 * @return
	 */
	List<GpOpeningReportScoreHistory> findPage();

	/**
	 * 根据id同步数据
	 * 
	 * @param openingReportId
	 * @return
	 */
	void copyInsert(Long openingReportId);

	/**
	 * 根据开题报告id获取评分集合
	 * 
	 * 
	 * @param
	 * @return
	 */
	List<GpOpeningReportScoreHistory> findByOpeningReportIdList(Long openingReportId);

}
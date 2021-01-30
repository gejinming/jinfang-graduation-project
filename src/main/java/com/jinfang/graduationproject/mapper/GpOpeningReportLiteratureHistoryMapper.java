package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpOpeningReportLiteratureHistory;

/**
 * --------------------------- 开题报告文献关系历史表（一对多）
 * (GpOpeningReportLiteratureHistoryMapper) --------------------------- 作者： lz
 * 时间： 2020-09-13 14:12:21 说明： ---------------------------
 */
public interface GpOpeningReportLiteratureHistoryMapper {

	/**
	 * 添加开题报告文献关系历史表（一对多）
	 * 
	 * @param record
	 * @return
	 */
	int add(GpOpeningReportLiteratureHistory record);

	/**
	 * 删除开题报告文献关系历史表（一对多）
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 修改开题报告文献关系历史表（一对多）
	 * 
	 * @param record
	 * @return
	 */
	int update(GpOpeningReportLiteratureHistory record);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	GpOpeningReportLiteratureHistory findById(Long id);

	/**
	 * 基础分页查询
	 * 
	 * @param record
	 * @return
	 */
	List<GpOpeningReportLiteratureHistory> findPage();

	/**
	 * 根据id同步数据
	 * 
	 * @param openingReportId
	 * @return
	 */
	void copyInsert(Long openingReportId);

	/**
	 * openingReportHistoryId获取集合
	 * 
	 * @param
	 * @return
	 */
	List<GpOpeningReportLiteratureHistory> findByOpeningReportHistoryId(Long openingReportHistoryId);

}
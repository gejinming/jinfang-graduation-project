package com.jinfang.graduationproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinfang.graduationproject.domain.GpOpeningReportScore;

/**
 * --------------------------- 开题报告评分表 (GpOpeningReportScoreMapper)
 * --------------------------- 作者： lz 时间： 2020-09-03 21:49:17 说明：
 * ---------------------------
 */
public interface GpOpeningReportScoreMapper {

	/**
	 * 添加开题报告评分表
	 * 
	 * @param record
	 * @return
	 */
	int add(GpOpeningReportScore record);

	/**
	 * 删除开题报告评分表
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 修改开题报告评分表
	 * 
	 * @param record
	 * @return
	 */
	int update(GpOpeningReportScore record);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	GpOpeningReportScore findById(Long id);
	
	/**
	 * openingReportId查询
	 * 
	 * @param openingReportId
	 * @return
	 */
	List<GpOpeningReportScore> findByOpeningReportIdList(Long openingReportId);

	/**
	 * 基础分页查询
	 * 
	 * @param record
	 * @return
	 */
	List<GpOpeningReportScore> findPage();

	/**
	 * 
	 * @Title: addBatch @Description: 批量保存 @param @param items 参数 @return void
	 * 返回类型 @throws
	 */
	void addBatch(@Param("items") List<GpOpeningReportScore> items);
	
	/**
	 * 根据openingReportId刪除
	 * 
	 * @param openingReportId
	 * @return
	 */
	int deleteByOpeningReportId(Long openingReportId);

}
package com.jinfang.graduationproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinfang.graduationproject.domain.GpOpeningReportLiterature;

/**
 * --------------------------- 开题报告文献关系表（一对多） (GpOpeningReportLiteratureMapper)
 * --------------------------- 作者： lz 时间： 2020-08-14 23:07:27 说明：
 * ---------------------------
 */
public interface GpOpeningReportLiteratureMapper {

	/**
	 * 添加开题报告文献关系表（一对多）
	 * 
	 * @param record
	 * @return
	 */
	int add(GpOpeningReportLiterature record);

	/**
	 * 删除开题报告文献关系表（一对多）
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 修改开题报告文献关系表（一对多）
	 * 
	 * @param record
	 * @return
	 */
	int update(GpOpeningReportLiterature record);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	GpOpeningReportLiterature findById(Long id);

	/**
	 * 基础分页查询
	 * 
	 * @param record
	 * @return
	 */
	List<GpOpeningReportLiterature> findPage();

	/**
	 * 根据openingReportId查询
	 * 
	 * @param openingReportId
	 * @return
	 */
	List<GpOpeningReportLiterature> findByOpeningReportIdList(Long openingReportId);

	/**
	 * 
	 * @Title: addBatch @Description: TODO(批量保存文献描述) @param @param items
	 *         参数 @return void 返回类型 @throws
	 */
	void addBatch(@Param("items") List<GpOpeningReportLiterature> items);

	/**
	 * 
	 * @Title: deleteByOpeningReportId @Description: TODO(删除) @param @param
	 * openingReportId @param @return 参数 @return int 返回类型 @throws
	 */
	int deleteByOpeningReportId(Long openingReportId);

}
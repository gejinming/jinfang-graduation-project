package com.jinfang.graduationproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinfang.graduationproject.domain.GpReviewScore;

/**
 * --------------------------- 文献综述评分表 (GpReviewScoreMapper)
 * --------------------------- 作者： lz 时间： 2020-09-03 21:49:17 说明：
 * ---------------------------
 */
public interface GpReviewScoreMapper {

	/**
	 * 添加文献综述评分表
	 * 
	 * @param record
	 * @return
	 */
	int add(GpReviewScore record);

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
	int update(GpReviewScore record);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	GpReviewScore findById(Long id);

	/**
	 * 基础分页查询
	 * 
	 * @param record
	 * @return
	 */
	List<GpReviewScore> findPage();

	/**
	 * 
	 * @Title: addBatch @Description: TODO(批量保存评分数据) @param @param items
	 * 参数 @return void 返回类型 @throws
	 */
	void addBatch(@Param("items") List<GpReviewScore> items);
	
	/**
	 * 根据文献id查询集合
	 * 
	 * @param id
	 * @return
	 */
	List<GpReviewScore> findByViewIdList(Long viewId);
	
	
	/**
	 * 根据viewId刪除
	 * 
	 * @param viewId
	 * @return
	 */
	int deleteByViewId(Long viewId);

}
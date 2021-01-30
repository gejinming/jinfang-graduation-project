package com.jinfang.graduationproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinfang.graduationproject.domain.GpReviewLiterature;

/**
 * --------------------------- 文献综述文献关系表（一对多） (GpReviewLiteratureMapper)
 * --------------------------- 作者： lz 时间： 2020-08-14 23:07:27 说明：
 * ---------------------------
 */
public interface GpReviewLiteratureMapper {

	/**
	 * 添加文献综述文献关系表（一对多）
	 * 
	 * @param record
	 * @return
	 */
	int add(GpReviewLiterature record);

	/**
	 * 删除文献综述文献关系表（一对多）
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 修改文献综述文献关系表（一对多）
	 * 
	 * @param record
	 * @return
	 */
	int update(GpReviewLiterature record);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	GpReviewLiterature findById(Long id);

	/**
	 * 基础分页查询
	 * 
	 * @param record
	 * @return
	 */
	List<GpReviewLiterature> findPage();

	/**
	 * 根据reviewId 查询数据
	 * 
	 * @param id
	 * @return
	 */
	List<GpReviewLiterature> findByReviewIdList(Long reviewId);

	/**
	 * 
	 * @Title: addBatch @Description: TODO(批量保存文献描述) @param @param items
	 *         参数 @return void 返回类型 @throws
	 */
	void addBatch(@Param("items") List<GpReviewLiterature> items);

	/**
	 * 
	 * @Title: deleteByReviewId @Description: TODO(根据文献id删除) @param @param
	 * reviewId @param @return 参数 @return int 返回类型 @throws
	 */
	int deleteByReviewId(Long reviewId);

}
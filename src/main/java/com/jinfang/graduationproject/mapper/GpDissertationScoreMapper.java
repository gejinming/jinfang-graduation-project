package com.jinfang.graduationproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinfang.graduationproject.domain.GpDissertationScore;

/**
 * --------------------------- 毕业论文评分表 (GpDissertationScoreMapper)
 * --------------------------- 作者： lz 时间： 2020-09-03 21:49:17 说明：
 * ---------------------------
 */
public interface GpDissertationScoreMapper {

	/**
	 * 添加毕业论文评分表
	 * 
	 * @param record
	 * @return
	 */
	int add(GpDissertationScore record);

	/**
	 * 删除毕业论文评分表
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 修改毕业论文评分表
	 * 
	 * @param record
	 * @return
	 */
	int update(GpDissertationScore record);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	GpDissertationScore findById(Long id);

	/**
	 * 基础分页查询
	 * 
	 * @param record
	 * @return
	 */
	List<GpDissertationScore> findPage();
	
	/**
	 * 根据dissertationId查询
	 * 
	 * @param dissertationId
	 * @return
	 */
	List<GpDissertationScore> findByDissertationIdList(Long dissertationId);
	

	/**
	 * 根据dissertationId刪除
	 * 
	 * @param dissertationId
	 * @return
	 */
	int deleteByDissertationId(Long dissertationId);
	
	/**
	 * 
	 * @Title: addBatch @Description: 批量保存 @param @param items 参数 @return void
	 * 返回类型 @throws
	 */
	void addBatch(@Param("items") List<GpDissertationScore> items);

}
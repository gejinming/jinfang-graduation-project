package com.jinfang.graduationproject.mapper;

import com.jinfang.graduationproject.domain.GpReviewLiteratureHistory;

import java.util.List;

/**
 * ---------------------------
 * 文献综述文献关系历史表（一对多） (GpReviewLiteratureHistoryMapper)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-13 14:12:21
 * 说明：  
 * ---------------------------
 */
public interface GpReviewLiteratureHistoryMapper {

	/**
	 * 添加文献综述文献关系历史表（一对多）
	 * @param record
	 * @return
	 */
    int add(GpReviewLiteratureHistory record);

    /**
     * 删除文献综述文献关系历史表（一对多）
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改文献综述文献关系历史表（一对多）
     * @param record
     * @return
     */
    int update(GpReviewLiteratureHistory record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    GpReviewLiteratureHistory findById(Long id);

    /**
     * 基础分页查询
     */
    List<GpReviewLiteratureHistory> findPage();

    int batchInsert(List<GpReviewLiteratureHistory> list);
    
    /**
     * 根据reviewHistoryId查询
     * @param reviewHistoryId
     * @return
     */    
    List<GpReviewLiteratureHistory> findByReviewHistoryId(Long reviewHistoryId);
    
}
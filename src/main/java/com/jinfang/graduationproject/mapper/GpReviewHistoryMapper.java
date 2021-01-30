package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpReviewHistory;
import com.jinfang.graduationproject.vo.teacher.guider.HistoryListRespVo;

/**
 * ---------------------------
 * 文献综述历史表 (GpReviewHistoryMapper)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-13 14:12:21
 * 说明：  
 * ---------------------------
 */
public interface GpReviewHistoryMapper {

	/**
	 * 添加文献综述历史表
	 * @param record
	 * @return
	 */
    int add(GpReviewHistory record);

    /**
     * 删除文献综述历史表
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改文献综述历史表
     * @param record
     * @return
     */
    int update(GpReviewHistory record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    GpReviewHistory findById(Long id);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<GpReviewHistory> findPage();
    
    /**
     * 根据文献id查询列表
     * @param reviewId
     * @return
     */    
    List<HistoryListRespVo> getReviewIdList(Long reviewId);
    
}
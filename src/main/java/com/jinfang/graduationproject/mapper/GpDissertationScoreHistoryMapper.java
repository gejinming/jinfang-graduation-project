package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpDissertationScoreHistory;

/**
 * ---------------------------
 * 毕业论文评分历史表 (GpDissertationScoreHistoryMapper)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-17 20:29:05
 * 说明：  
 * ---------------------------
 */
public interface GpDissertationScoreHistoryMapper {

	/**
	 * 添加毕业论文评分历史表
	 * @param record
	 * @return
	 */
    int add(GpDissertationScoreHistory record);

    /**
     * 删除毕业论文评分历史表
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改毕业论文评分历史表
     * @param record
     * @return
     */
    int update(GpDissertationScoreHistory record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    GpDissertationScoreHistory findById(Long id);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<GpDissertationScoreHistory> findPage();
    
    
    /**
     * 根据id同步数据
     * @param record
     * @return
     */    
	void copyInsert(Long dissertationId);
    
}
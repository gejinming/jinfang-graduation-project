package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpMissionLiteratureHistory;

/**
 * ---------------------------
 * 任务文献历史（一对多） (GpMissionLiteratureHistoryMapper)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-19 15:58:00
 * 说明：  
 * ---------------------------
 */
public interface GpMissionLiteratureHistoryMapper {

	/**
	 * 添加任务文献历史（一对多）
	 * @param record
	 * @return
	 */
    int add(GpMissionLiteratureHistory record);

    /**
     * 删除任务文献历史（一对多）
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改任务文献历史（一对多）
     * @param record
     * @return
     */
    int update(GpMissionLiteratureHistory record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    GpMissionLiteratureHistory findById(Long id);

    /**
     * 基础分页查询
     * @return
     */    
    List<GpMissionLiteratureHistory> findPage();
    
    /**
     * 根据id同步数据
     * @return
     */    
	int copyInsert(Long missionId);
	
	/**
	 * 根据missionHistoryId查询列表
	 * 
	 * @param
	 * @return
	 */
	List<GpMissionLiteratureHistory> getByMissionHistoryIdList(Long missionHistoryId);
    
}
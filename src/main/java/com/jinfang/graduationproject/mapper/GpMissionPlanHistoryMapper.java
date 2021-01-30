package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpMissionPlanHistory;

/**
 * ---------------------------
 * 任务计划历史	（一对多） (GpMissionPlanHistoryMapper)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-19 15:58:01
 * 说明：  
 * ---------------------------
 */
public interface GpMissionPlanHistoryMapper {

	/**
	 * 添加任务计划历史	（一对多）
	 * @param record
	 * @return
	 */
    int add(GpMissionPlanHistory record);

    /**
     * 删除任务计划历史	（一对多）
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改任务计划历史	（一对多）
     * @param record
     * @return
     */
    int update(GpMissionPlanHistory record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    GpMissionPlanHistory findById(Long id);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<GpMissionPlanHistory> findPage();
    
    /**
     * 根据id同步数据
     * @return
     */    
	int copyInsert(Long missionId);
	
	/**
	 * 根据messionHistoryId查询列表
	 * 
	 * @param
	 * @return
	 */
	List<GpMissionPlanHistory> findByMessionHistoryIdList(Long messionHistoryId);
    
}
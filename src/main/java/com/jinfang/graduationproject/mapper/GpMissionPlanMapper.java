package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpMissionPlan;

/**
 * ---------------------------
 * 任务计划（一对多） (GpMissionPlanMapper)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-14 23:07:27
 * 说明：  
 * ---------------------------
 */
public interface GpMissionPlanMapper {

	/**
	 * 添加任务计划（一对多）
	 * @param record
	 * @return
	 */
    int add(GpMissionPlan record);

    /**
     * 删除任务计划（一对多）
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改任务计划（一对多）
     * @param record
     * @return
     */
    int update(GpMissionPlan record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    GpMissionPlan findById(Long id);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<GpMissionPlan> findPage();
    
    /**
     * 根据任务id查询
     * @param record
     * @return
     */    
    List<GpMissionPlan> findByMessionId(Long messionId);
    
    void deleteByMessionId(Long messionId);
    
}
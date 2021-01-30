package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpMissionHistory;
import com.jinfang.graduationproject.vo.teacher.mission.FindByMissionIdListRespVo;

/**
 * ---------------------------
 * 任务书历史 (GpMissionHistoryMapper)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-19 15:58:00
 * 说明：  
 * ---------------------------
 */
public interface GpMissionHistoryMapper {

	/**
	 * 添加任务书历史
	 * @param record
	 * @return
	 */
    int add(GpMissionHistory record);

    /**
     * 删除任务书历史
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改任务书历史
     * @param record
     * @return
     */
    int update(GpMissionHistory record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    GpMissionHistory findById(Long id);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<GpMissionHistory> findPage();
    
    /**
     * 根据id同步数据
     * @return
     */    
	int copyInsert(Long id);
	
	/**
	 * 根据missionHistoryId查询列表
	 * 
	 * @param
	 * @return
	 */
	List<FindByMissionIdListRespVo> findByMissionIdList(Long missionHistoryId);
    
}
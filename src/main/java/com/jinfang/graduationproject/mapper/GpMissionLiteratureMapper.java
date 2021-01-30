package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpMissionLiterature;

/**
 * ---------------------------
 * 任务文献（一对多） (GpMissionLiteratureMapper)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-14 23:07:27
 * 说明：  
 * ---------------------------
 */
public interface GpMissionLiteratureMapper {

	/**
	 * 添加任务文献（一对多）
	 * @param record
	 * @return
	 */
    int add(GpMissionLiterature record);

    /**
     * 删除任务文献（一对多）
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改任务文献（一对多）
     * @param record
     * @return
     */
    int update(GpMissionLiterature record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    GpMissionLiterature findById(Long id);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<GpMissionLiterature> findPage();
    
    /**
     * 根据任务id查询文献列表
     * @param record
     * @return
     */    
    List<GpMissionLiterature> findByMessionId(Long messionId);
    
    void deleteByMessionId(Long messionId);
    
    
}
package com.jinfang.graduationproject.mapper;

import com.jinfang.graduationproject.domain.GpSetting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ---------------------------
 * 参数设置 (GpSettingMapper)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-18 23:06:14
 * 说明：  
 * ---------------------------
 */
public interface GpSettingMapper {

	/**
	 * 添加参数设置
	 * @param record
	 * @return
	 */
    int add(GpSetting record);

    /**
     * 删除参数设置
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改参数设置
     * @param record
     * @return
     */
    int update(GpSetting record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    GpSetting findById(Long id);


    /**
     * 基础分页查询
     */
    List<GpSetting> findPage(@Param("schoolId") Long schoolId);

    GpSetting selectByGradeAndSchoolId(@Param("grade") String grade, @Param("schoolId") Long schoolId);
    
}
package com.jinfang.graduationproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinfang.graduationproject.domain.GpModifyPermission;

/**
 * ---------------------------
 * 设置评分角色 (GpSubjectPermissionMapper)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-27 00:18:12
 * 说明：  
 * ---------------------------
 */
public interface GpModifyPermissionMapper {

	/**
	 * 添加设置评分角色
	 * @param record
	 * @return
	 */
    int add(GpModifyPermission record);

    /**
     * 删除设置评分角色
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改设置评分角色
     * @param record
     * @return
     */
    int update(GpModifyPermission record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    GpModifyPermission findById(Long id);

    /**
     * 基础分页查询
     * @return
     */    
    List<GpModifyPermission> findPage(@Param("grade") String grade, @Param("schoolId") Long schoolId);

    /**
     * 根据届别和学校ID获取记录
     * @param grade 届别
     * @param schoolId 学校ID
     */
    GpModifyPermission findByGradeAndSchoolId(@Param("grade") String grade, @Param("schoolId") Long schoolId);
    
}
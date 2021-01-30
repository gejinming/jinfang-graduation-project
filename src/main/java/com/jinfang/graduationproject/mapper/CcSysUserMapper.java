package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.CcSysUser;

/**
 * ---------------------------
 * 用户表 (SysUserMapper)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-27 14:11:59
 * 说明：  
 * ---------------------------
 */
public interface CcSysUserMapper {

	/**
	 * 添加用户表
	 * @param record
	 * @return
	 */
    int add(CcSysUser record);

    /**
     * 删除用户表
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改用户表
     * @param record
     * @return
     */
    int update(CcSysUser record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    CcSysUser findById(Long id);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<CcSysUser> findPage();
    
}
package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.CcSchoolCourseCompose;

/**
 * ---------------------------
 *  (CcSchoolCourseComposeMapper)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-30 00:58:44
 * 说明：  
 * ---------------------------
 */
public interface CcSchoolCourseComposeMapper {

	/**
	 * 添加
	 * @param record
	 * @return
	 */
    int add(CcSchoolCourseCompose record);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改
     * @param record
     * @return
     */
    int update(CcSchoolCourseCompose record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    CcSchoolCourseCompose findById(Long id);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<CcSchoolCourseCompose> findPage();
    
}
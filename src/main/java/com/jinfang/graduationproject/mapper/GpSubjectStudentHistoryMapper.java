package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpSubjectStudentHistory;

/**
 * ---------------------------
 * 学生选题关系表一对多） (GpSubjectStudentHistoryMapper)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-13 14:12:21
 * 说明：  
 * ---------------------------
 */
public interface GpSubjectStudentHistoryMapper {

	/**
	 * 添加学生选题关系表一对多）
	 * @param record
	 * @return
	 */
    int add(GpSubjectStudentHistory record);

    /**
     * 删除学生选题关系表一对多）
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改学生选题关系表一对多）
     * @param record
     * @return
     */
    int update(GpSubjectStudentHistory record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    GpSubjectStudentHistory findById(Long id);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<GpSubjectStudentHistory> findPage();
    
}
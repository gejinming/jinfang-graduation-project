package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpInspection;
import org.apache.ibatis.annotations.Param;

/**
 * ---------------------------
 * 中期检查表 (GpInspectionMapper)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-09 13:46:25
 * 说明：  
 * ---------------------------
 */
public interface GpInspectionMapper {

	/**
	 * 添加中期检查表
	 * @param record
	 * @return
	 */
    int add(GpInspection record);

    /**
     * 删除中期检查表
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改中期检查表
     * @param record
     * @return
     */
    int update(GpInspection record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    GpInspection findById(Long id);

    /**
     * 基础分页查询
     */
    List<GpInspection> findPage(@Param("schoolId") Long schoolId,
                                @Param("grade") String grade,
                                @Param("subjectName") String subjectName,
                                @Param("status") String status,
                                @Param("teacherId") Long teacherId);

    GpInspection selectBySubjectStudentId(@Param("subjectStudentId") Long subjectId);
}
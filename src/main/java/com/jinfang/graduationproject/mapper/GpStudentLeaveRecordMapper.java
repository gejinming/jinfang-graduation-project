package com.jinfang.graduationproject.mapper;

import com.jinfang.graduationproject.domain.GpStudentLeaveRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ---------------------------
 * 学生请假记录 (GpStudentLeaveRecordMapper)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-19 15:58:01
 * 说明：  
 * ---------------------------
 */
public interface GpStudentLeaveRecordMapper {

	/**
	 * 添加学生请假记录
	 * @param record
	 * @return
	 */
    int add(GpStudentLeaveRecord record);

    /**
     * 删除学生请假记录
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改学生请假记录
     * @param record
     * @return
     */
    int update(GpStudentLeaveRecord record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    GpStudentLeaveRecord findById(Long id);

    int batchInsert(List<GpStudentLeaveRecord> list);

    List<GpStudentLeaveRecord> selectBySubjectId(@Param("subjectId") Long subjectId,
                                                     @Param("studentId") Long studentId);

    int deleteBySubjectIdAndStudentId(@Param("subjectId") Long subjectId,
                                      @Param("studentId") Long studentId);
    
}
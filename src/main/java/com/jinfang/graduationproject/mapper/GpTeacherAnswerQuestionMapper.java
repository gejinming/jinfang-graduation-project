package com.jinfang.graduationproject.mapper;

import com.jinfang.graduationproject.domain.GpTeacherAnswerQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ---------------------------
 * 工作指导计划进程 (GpTeacherAnswerQuestionMapper)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-19 15:58:01
 * 说明：  
 * ---------------------------
 */
public interface GpTeacherAnswerQuestionMapper {

	/**
	 * 添加工作指导计划进程
	 * @param record
	 * @return
	 */
    int add(GpTeacherAnswerQuestion record);

    /**
     * 删除工作指导计划进程
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改工作指导计划进程
     * @param record
     * @return
     */
    int update(GpTeacherAnswerQuestion record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    GpTeacherAnswerQuestion findById(Long id);

    int batchInsert(@Param("list") List<GpTeacherAnswerQuestion> list);

    List<GpTeacherAnswerQuestion> selectBySubjectId(@Param("subjectId") Long subjectId,
                                                     @Param("studentId") Long studentId);

    int deleteBySubjectIdAndStudentId(@Param("subjectId") Long subjectId,
                                      @Param("studentId") Long studentId);
    
}
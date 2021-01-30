package com.jinfang.graduationproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinfang.graduationproject.domain.GpWorkInstructionProcess;

/**
 * ---------------------------
 * 工作指导计划进程 (GpWorkInstructionProcessMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-19 15:58:01
 * 说明：
 * ---------------------------
 */
public interface GpWorkInstructionProcessMapper {

    /**
     * 添加工作指导计划进程
     *
     * @param record
     * @return
     */
    int add(GpWorkInstructionProcess record);

    /**
     * 删除工作指导计划进程
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改工作指导计划进程
     *
     * @param record
     * @return
     */
    int update(GpWorkInstructionProcess record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpWorkInstructionProcess findById(Long id);

    /**
     * 基础分页查询
     */
    List<GpWorkInstructionProcess> findPage(@Param("teacherId") Long teacherId,
            @Param("grade") String grade,
            @Param("subjectName") String subjectName,
            @Param("status") String status);

    int batchInsert(List<GpWorkInstructionProcess> list);

    List<GpWorkInstructionProcess> selectBySubjectId(@Param("subjectId") Long subjectId,
                                                     @Param("studentId") Long studentId);

    int deleteBySubjectIdAndStudentId(@Param("subjectId") Long subjectId,
                                      @Param("studentId") Long studentId);

}
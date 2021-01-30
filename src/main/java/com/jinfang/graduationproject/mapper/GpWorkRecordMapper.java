package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpDefenseTeacher;
import com.jinfang.graduationproject.domain.GpWorkRecord;
import org.apache.ibatis.annotations.Param;

/**
 * ---------------------------
 * 工作记录卡 (GpWorkRecordMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-19 15:58:01
 * 说明：
 * ---------------------------
 */
public interface GpWorkRecordMapper {

    /**
     * 添加工作记录卡
     *
     * @param record
     * @return
     */
    int add(GpWorkRecord record);

    /**
     * 删除工作记录卡
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改工作记录卡
     *
     * @param record
     * @return
     */
    int update(GpWorkRecord record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpWorkRecord findById(Long id);

    /**
     * 基础分页查询
     */
    List<GpWorkRecord> findPage(@Param("teacherId") Long teacherId,
                                @Param("grade") String grade,
                                @Param("subjectName") String subjectName,
                                @Param("status") String status);

    int deleteBySubjectIdAndStudentId(@Param("subjectId") Long subjectId,
                                      @Param("studentId") Long studentId);

    int batchInsert(List<GpWorkRecord> list);

    /**
     * 根基课题ID获取工作记录卡
     *
     * @param subjectId 课题ID
     * @param studentId 学生ID
     * @return 集合
     */
    List<GpWorkRecord> selectBySubjectId(@Param("subjectId") Long subjectId,
                                         @Param("studentId") Long studentId);
}
package com.jinfang.graduationproject.mapper;

import com.jinfang.graduationproject.domain.GpCheckTeacher;
import com.jinfang.graduationproject.vo.teacher.checker.CheckerStudentRespVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ---------------------------
 * 评阅老师学生关系表 (GpCheckTeacherMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-27 00:18:11
 * 说明：
 * ---------------------------
 */
public interface GpCheckTeacherMapper {

    /**
     * 添加评阅老师学生关系表
     *
     * @param record
     * @return
     */
    int add(GpCheckTeacher record);

    /**
     * 删除评阅老师学生关系表
     *
     * @param id
     * @return
     */
    int delete(Long id);

    int deleteByTeacherIdAndStudentId(@Param("defenseTeacherId") Long defenseTeacherId,
                                      @Param("studentId") Long studentId);

    /**
     * 修改评阅老师学生关系表
     *
     * @param record
     * @return
     */
    int update(GpCheckTeacher record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpCheckTeacher findById(Long id);

    /**
     * 基础分页查询
     *
     * @return
     */
    List<GpCheckTeacher> findPage();

    int batchInsert(List<GpCheckTeacher> list);


    /**
     * 查找未分配评教教师学生列表
     *
     * @param grade    届别
     * @param schoolId 学校ID
     */
    List<CheckerStudentRespVo> findUndistributedList(@Param("grade") String grade,
                                                     @Param("schoolId") Long schoolId,
                                                     @Param("studentName") String studentName,
                                                     @Param("studentNo") String studentNo,
                                                     @Param("className") String className);


    /**
     * 根据答辩组教师记录ID查询数量
     *
     * @param defenseTeacherId 答辩组教师记录ID
     */
    long findCountByDefenseTeacherId(@Param("defenseTeacherId") Long defenseTeacherId);

    /**
     * 查找已分配评教教师学生数
     *
     * @param grade    届别
     * @param schoolId 学校ID
     */
    long findUndistributedCount(@Param("grade") String grade, @Param("schoolId") Long schoolId);

    /**
     * 查找已分配评教教师学生列表
     *
     * @param defenseTeacherId 答辩组教师记录ID
     */
    List<CheckerStudentRespVo> findDistributedList(@Param("defenseTeacherId") Long defenseTeacherId);


    /**
     * 查找已分配评教教师学生数
     *
     * @param defenseTeacherId 答辩组教师记录ID
     */
    long findDistributedCount(@Param("defenseTeacherId") Long defenseTeacherId);

}
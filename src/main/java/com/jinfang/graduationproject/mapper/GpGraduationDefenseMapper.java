package com.jinfang.graduationproject.mapper;

import com.jinfang.graduationproject.domain.GpGraduationDefense;
import com.jinfang.graduationproject.vo.defense.SubjectStudentGraduationDefenseRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ---------------------------
 * 评阅老师学生关系表 (GpGraduactionDefenseMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-27 00:18:12
 * 说明：
 * ---------------------------
 */
public interface GpGraduationDefenseMapper {

    /**
     * 添加评阅老师学生关系表
     *
     * @param record
     * @return
     */
    int add(GpGraduationDefense record);

    /**
     * 删除评阅老师学生关系表
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改评阅老师学生关系表
     *
     * @param record
     * @return
     */
    int update(GpGraduationDefense record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpGraduationDefense findById(Long id);

    /**
     * 基础分页查询
     */
    List<GpGraduationDefense> findPage(@Param("studentName") String studentName,
                                       @Param("majorName") String majorName,
                                       @Param("subjectName") String subjectName,
                                       @Param("subjectType") String subjectType,
                                       @Param("subjectSource") String subjectSource,
                                       @Param("grade") String grade,
                                       @Param("schoolId") Long schoolId);

    /**
     * 根据课题学生ID获取 学生开题毕业相关数据
     *
     * @param subjectStudentId 课题学生ID
     * @return 学生开题答辩相关数据
     */
    SubjectStudentGraduationDefenseRes selectExtBySubjectStudentId(@Param("subjectStudentId") Long subjectStudentId);

    /**
     * 根据毕业答辩记录ID获取 学生开题答辩相关数据
     *
     * @param graduationDefenseId 毕业答辩记录ID
     * @return 学生开题答辩相关数据
     */
    SubjectStudentGraduationDefenseRes selectByGraduationDefenseId(@Param("graduationDefenseId") Long graduationDefenseId);


    /**
     * 根据课题和学生ID获取毕业答辩信息
     *
     * @param subjectStudentId 课题学生ID
     * @return 毕业答辩记录
     */
    GpGraduationDefense selectDefenseBySubjectStudentId(@Param("subjectStudentId") Long subjectStudentId);
}
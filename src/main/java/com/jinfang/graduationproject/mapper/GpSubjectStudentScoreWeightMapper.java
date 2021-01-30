package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpSubjectStudentScoreWeight;
import org.apache.ibatis.annotations.Param;

/**
 * ---------------------------
 * 学生课题最终分数权重表 (GpSubjectStudentScoreWeightMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-20 00:37:29
 * 说明：
 * ---------------------------
 */
public interface GpSubjectStudentScoreWeightMapper {

    /**
     * 添加学生课题最终分数权重表
     *
     * @param record
     * @return
     */
    int add(GpSubjectStudentScoreWeight record);

    /**
     * 删除学生课题最终分数权重表
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改学生课题最终分数权重表
     *
     * @param record
     * @return
     */
    int update(GpSubjectStudentScoreWeight record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpSubjectStudentScoreWeight findById(Long id);

    /**
     * 基础分页查询
     *
     * @return
     */
    List<GpSubjectStudentScoreWeight> findPage();

    int deleteByGradeAndsSchoolId(@Param("grade") String grade,
                                  @Param("schoolId") Long schoolId);
}
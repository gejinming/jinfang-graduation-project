package com.jinfang.graduationproject.mapper;

import com.jinfang.graduationproject.domain.GpOpeningDefense;
import com.jinfang.graduationproject.vo.defense.SubjectStudentOpeningDefenseRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ---------------------------
 * 评阅老师学生关系表 (GpOpeningDefenseMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-27 00:18:12
 * 说明：
 * ---------------------------
 */
public interface GpOpeningDefenseMapper {

    /**
     * 添加评阅老师学生关系表
     *
     * @param record
     * @return
     */
    int add(GpOpeningDefense record);

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
    int update(GpOpeningDefense record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpOpeningDefense findById(Long id);

    /**
     * 基础分页查询
     */
    List<SubjectStudentOpeningDefenseRes> findPage(@Param("studentName") String studentName,
                                                   @Param("majorName") String majorName,
                                                   @Param("subjectName") String subjectName,
                                                   @Param("subjectType") String subjectType,
                                                   @Param("subjectSource") String subjectSource,
                                                   @Param("grade") String grade,
                                                   @Param("schoolId") Long schoolId);

    /**
     * 根据课题学生ID获取 学生开题答辩相关数据
     *
     * @param subjectStudentId 课题学生ID
     * @return 学生开题答辩相关数据
     */
    SubjectStudentOpeningDefenseRes selectBySubjectStudentId(@Param("subjectStudentId") Long subjectStudentId);

    /**
     * 根据开题答辩记录ID获取 学生开题答辩相关数据
     *
     * @param openingDefenseId 开题答辩记录ID
     * @return 学生开题答辩相关数据
     */
    SubjectStudentOpeningDefenseRes selectByOpeningDefenseId(@Param("openingDefenseId") Long openingDefenseId);

    GpOpeningDefense selectDefenseBySubjectStudentId(@Param("subjectStudentId") Long subjectStudentId);
}
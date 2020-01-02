package com.jinfang.graduationproject.mapper;

import com.jinfang.graduationproject.domain.GpSubjectStudentScore;
import com.jinfang.graduationproject.vo.score.SubjectStudentCompleteVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ---------------------------
 * 学生课题最终分数表 (GpSubjectStudentScoreMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-20 00:37:29
 * 说明：
 * ---------------------------
 */
public interface GpSubjectStudentScoreMapper {

    /**
     * 添加学生课题最终分数表
     *
     * @param record
     * @return
     */
    int add(GpSubjectStudentScore record);

    /**
     * 删除学生课题最终分数表
     *
     * @param id
     * @return
     */
    int delete(Long id);

    int deleteByGradeAndsSchoolId(List<Long> subjectStudentIds);

    /**
     * 修改学生课题最终分数表
     *
     * @param record
     * @return
     */
    int update(GpSubjectStudentScore record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpSubjectStudentScore findById(Long id);

    /**
     * 基础分页查询
     *
     * @return
     */
    List<GpSubjectStudentScore> findPage(@Param("grade") String grade,
                                         @Param("schoolId") Long schoolId);

    List<GpSubjectStudentScore> findByGradeIdAndSchoolId(@Param("grade") String grade,
                                                         @Param("schoolId") Long schoolId);


    List<SubjectStudentCompleteVo> findCompleteRecord(@Param("grade") String grade,
                                                      @Param("schoolId") Long schoolId);

    int batchInsert(List<GpSubjectStudentScore> list);
}
package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpDefenseAssessment;
import com.jinfang.graduationproject.vo.defense.SubjectStudentDefenseAssessmentRes;
import com.jinfang.graduationproject.vo.defense.SubjectStudentOpeningDefenseRes;
import org.apache.ibatis.annotations.Param;

/**
 * ---------------------------
 * 答辩评语表 (GpDefenseAssessmentMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-19 15:58:01
 * 说明：
 * ---------------------------
 */
public interface GpDefenseAssessmentMapper {

    /**
     * 添加答辩评语表
     *
     * @param record
     * @return
     */
    int add(GpDefenseAssessment record);

    /**
     * 删除答辩评语表
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改答辩评语表
     *
     * @param record
     * @return
     */
    int update(GpDefenseAssessment record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpDefenseAssessment findById(Long id);

    /**
     * 基础分页查询
     */
    List<SubjectStudentDefenseAssessmentRes> findPage(@Param("studentName") String studentName,
                                                      @Param("majorName") String majorName,
                                                      @Param("subjectName") String subjectName,
                                                      @Param("subjectType") String subjectType,
                                                      @Param("subjectSource") String subjectSource,
                                                      @Param("grade") String grade,
                                                      @Param("schoolId") Long schoolId);


    GpDefenseAssessment selectBySubjectIdAndStudentId(@Param("subjectId") Long subjectId,@Param("studentId") Long studentId);
}
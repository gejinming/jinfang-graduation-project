package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpGuideAssessment;
import com.jinfang.graduationproject.vo.teacher.guider.assessment.FindPageByNameAssessmentRespVo;
import com.jinfang.graduationproject.vo.teacher.translate.QueryTranslateResqVo;
import org.apache.ibatis.annotations.Param;

/**
 * --------------------------- 指导教师学生评语表 (GpGuideAssessmentMapper)
 * --------------------------- 作者： lz 时间： 2020-08-27 00:18:12 说明：
 * ---------------------------
 */
public interface GpGuideAssessmentMapper {

    /**
     * 添加指导教师学生评语表
     *
     * @param record
     * @return
     */
    int add(GpGuideAssessment record);

    /**
     * 删除指导教师学生评语表
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改指导教师学生评语表
     *
     * @param record
     * @return
     */
    int update(GpGuideAssessment record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpGuideAssessment findById(Long id);

    GpGuideAssessment selectBySubjectIdAndStudentId(@Param("subjectId") Long subjectId, @Param("studentId") Long studentId);

    /**
     * 基础分页查询
     *
     * @param record
     * @return
     */
    List<GpGuideAssessment> findPage();

    /**
     * 基础分页查询
     *
     * @param record
     * @return
     */
    List<FindPageByNameAssessmentRespVo> findPageByName(QueryTranslateResqVo record);

}
package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.CcScoreStuIndigrade;
import org.apache.ibatis.annotations.Param;

/**
 * ---------------------------
 * 考核成绩分析法学生课程目标成绩 (CcScoreStuIndigradeMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-07 21:30:49
 * 说明：
 * ---------------------------
 */
public interface CcScoreStuIndigradeMapper {

    /**
     * 添加考核成绩分析法学生课程目标成绩
     *
     * @param record
     * @return
     */
    int add(CcScoreStuIndigrade record);

    /**
     * 删除考核成绩分析法学生课程目标成绩
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改考核成绩分析法学生课程目标成绩
     *
     * @param record
     * @return
     */
    int update(CcScoreStuIndigrade record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    CcScoreStuIndigrade findById(Long id);

    /**
     * 基础分页查询
     *
     * @return
     */
    List<CcScoreStuIndigrade> findPage();

    /**
     * 根据成绩组成ID和 课程目标ID获取 成绩组成课程目标ID
     *
     * @param courseGradecomposeId 成绩组成ID
     * @param indicationId         课程目标ID
     * @return 成绩组成课程目标ID
     */
    Long selectCourseGradecomposeIndicationId(@Param("courseGradecomposeId") Long courseGradecomposeId,
                                              @Param("indicationId") Long indicationId);
}
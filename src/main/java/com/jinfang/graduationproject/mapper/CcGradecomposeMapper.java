package com.jinfang.graduationproject.mapper;

import com.jinfang.graduationproject.domain.CcGradecompose;
import com.jinfang.graduationproject.vo.score.GradeComposeIndicationReq;
import com.jinfang.graduationproject.vo.score.GradeComposeReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ---------------------------
 * 成绩组成元素，如：试卷分、作业分、实验分。表全名(certification gradecompose) (CcGradecomposeMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-30 00:58:44
 * 说明：
 * ---------------------------
 */
public interface CcGradecomposeMapper {

    /**
     * 添加成绩组成元素，如：试卷分、作业分、实验分。表全名(certification gradecompose)
     *
     * @param record
     * @return
     */
    int add(CcGradecompose record);

    /**
     * 删除成绩组成元素，如：试卷分、作业分、实验分。表全名(certification gradecompose)
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改成绩组成元素，如：试卷分、作业分、实验分。表全名(certification gradecompose)
     *
     * @param record
     * @return
     */
    int update(CcGradecompose record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    CcGradecompose findById(Long id);

    /**
     * 基础分页查询
     *
     * @return
     */
    List<CcGradecompose> findPage();

    /**
     * 根据届别和学校ID获取所有的成绩组成信息
     *
     * @param grade    届别
     * @param schoolId 学校ID
     * @return 数据集合
     */
    List<GradeComposeReq> selectListBySchoolIdAndPeriod(@Param("grade") String grade,
                                                        @Param("schoolId") Long schoolId);

    /**
     * 根据课程组成成绩ID获取所有的课程目标信息
     *
     * @param courseGradecomposeId 课程组成id
     * @return 课程目标集合
     */
    List<GradeComposeIndicationReq> selectIndications(@Param("courseGradecomposeId") Long courseGradecomposeId);
}
package com.jinfang.graduationproject.mapper;

import com.jinfang.graduationproject.domain.CcCoursePeriode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ---------------------------
 * (CcCoursePeriodeMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-30 17:46:43
 * 说明：
 * ---------------------------
 */
public interface CcCoursePeriodeMapper {

    /**
     * 添加
     *
     * @param record
     * @return
     */
    int add(CcCoursePeriode record);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改
     *
     * @param record
     * @return
     */
    int update(CcCoursePeriode record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    CcCoursePeriode findById(Long id);

    /**
     * 基础分页查询
     *
     * @return
     */
    List<CcCoursePeriode> findPage();

    /**
     * 根据届别和学校ID获取毕业设计相关课程组成及 行政班关联信息
     *
     * @param grade    届别
     * @param schoolId 学校id
     */
    CcCoursePeriode selectByGradeAndSchoolId(@Param("grade") String grade,
                                             @Param("schoolId") Long schoolId);

    /**
     * 根据届别和学校ID获取毕业设计相关课程组成及 ，扩展信息：专业名称，院校名称
     *
     * @param grade    届别
     * @param schoolId 学校ID
     */
    CcCoursePeriode selectExtByGradeAndSchoolId(@Param("grade") String grade,
                                                @Param("schoolId") Long schoolId);

}
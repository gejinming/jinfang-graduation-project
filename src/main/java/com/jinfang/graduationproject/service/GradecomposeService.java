package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.vo.score.GradeComposeIndicationReq;
import com.jinfang.graduationproject.vo.score.GradeComposeReq;

import java.util.List;
import java.util.Set;

public interface GradecomposeService {

    /**
     * 根据届别和学校ID获取所有的成绩组成信息
     *
     * @param grade    届别
     * @param schoolId 学校ID
     * @param roleNames 当前登录人角色名称集合（可能属于多种角色）
     * @return 数据集合
     */
    List<GradeComposeReq> findBySchoolIdAndPeriod(String grade, Long schoolId, Set<String> roleNames);

    /**
     * 根据课程组成成绩ID获取所有的课程目标信息
     *
     * @param courseGradecomposeId 课程组成id
     * @return 课程目标集合
     */
    List<GradeComposeIndicationReq> findIndications(Long courseGradecomposeId);
}

package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.CcStudent;

import java.util.List;

public interface StudentService extends CurdService<CcStudent> {

    /**
     * 根据学号获取学生信息
     *
     * @return 学生信息
     */
    CcStudent findInfoById(Long id);

    /**
     * 根据学校和专业获取学生列表
     *
     * @param schoolId 学校ID
     * @param majorId  专业ID
     * @return 学生列表
     */
    List<CcStudent> findBySchoolAndMajor(Long schoolId, Long majorId);

    /**
     * 是否是毕业班成员
     *
     * @param studentId 学生ID
     * @return true/false
     */
    boolean isGraduationProjectMember(Long studentId);


}

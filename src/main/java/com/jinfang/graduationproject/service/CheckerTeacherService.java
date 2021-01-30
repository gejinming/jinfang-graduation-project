package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.GpCheckTeacher;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.vo.teacher.checker.CheckerStudentRespVo;
import com.jinfang.graduationproject.vo.teacher.checker.CheckerTeacherDispatchReq;

import java.util.List;

public interface CheckerTeacherService extends CurdService<GpCheckTeacher> {

    PageResult findPage(PageRequest pageRequest);

    /**
     * 分配学生
     *
     * @param checkerTeacherDispatchReq 请求
     * @return 处理结果
     */
    HttpResult distribute(CheckerTeacherDispatchReq checkerTeacherDispatchReq);

    /**
     * 删除分配
     *
     * @param checkerTeacherDispatchReq 请求
     * @return 处理结果
     */
    HttpResult remove(CheckerTeacherDispatchReq checkerTeacherDispatchReq);

    /**
     * 获取未分配的学生信息
     *
     * @param grade    届别
     * @param schoolId 学校ID
     * @param studentName 学生姓名
     * @param studentNo 学号
     * @param className 班级
     * @return 结果
     */
    List<CheckerStudentRespVo> findUndistributedList(String grade, Long schoolId, String studentName,
                                                     String studentNo, String className);

    /**
     * 获取已分配的学生信息
     *
     * @param defenseTeacherId 答辩组成员记录ID
     * @return 结果
     */
    List<CheckerStudentRespVo> findDistributedList(Long defenseTeacherId);

    /**
     * 获取所有未分配学生数量
     *
     * @param grade    届别
     * @param schoolId 学校ID
     * @return 数量
     */
    long findUndistributedCount(String grade, Long schoolId);

    /**
     * 是否为评阅教师
     *
     * @param defenseTeacherId 答辩组教师记录ID
     * @return true/false
     */
    boolean isTeacherChecker(Long defenseTeacherId);

}

package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.GpDefenseTeacher;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.vo.teacher.defense.DefenseTeacherJoinReq;

import java.util.List;

public interface DefenseTeacherService extends CurdService<GpDefenseTeacher> {

    PageResult findPage(PageRequest pageRequest);

    /**
     * 拉入教师
     *
     * @param defenseTeacherJoinReq 请求
     * @return 处理结果
     */
    HttpResult joinTeacher(DefenseTeacherJoinReq defenseTeacherJoinReq);

    /**
     * 设置为答辩组组长
     *
     * @param id          记录ID
     * @param operateUser 操作人账号-->专业负责人 teacherId
     * @return 处理结果
     */
    HttpResult setHeadman(Long id, String operateUser);

    /**
     * 删除
     *
     * @param id 记录ID
     * @return 处理结果
     */
    HttpResult delete(Long id);

    /**
     * 根据教师ID获取答辩组教师信息
     *
     * @param teacherId 教师ID
     * @return 答辩组教师信息
     */
    GpDefenseTeacher findByTeacherId(Long teacherId);

    /**
     * 根据当前登录教师获取 归属答辩组的所有教师列表
     * @param teacherId 当前登录教师
     */
    List<GpDefenseTeacher> findMembersByOne(String grade, Long teacherId);

}

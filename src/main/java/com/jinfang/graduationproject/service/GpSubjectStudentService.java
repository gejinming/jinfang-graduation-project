package com.jinfang.graduationproject.service;

import java.util.List;

import com.jinfang.graduationproject.domain.GpSubjectStudent;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.vo.teacher.topic.SeeReceiveStudentReqVo;
import com.jinfang.graduationproject.vo.teacher.topic.SeeReceiveStudentRespVo;

/**
 * --------------------------- 学生选题关系表一对多） (GpSubjectStudentService)
 * --------------------------- 作者： lz 时间： 2020-08-14 23:07:27 说明：
 * ---------------------------
 */
public interface GpSubjectStudentService extends CurdService<GpSubjectStudent> {

    /**
     * 根据课题id查询已接收学生列表
     */
    List<SeeReceiveStudentRespVo> seeReceiveStudent(SeeReceiveStudentReqVo req);

    /**
     * 接受
     * 1、判断 当前课题限制学生人数 和已接收学生数是否达到 如果达到则 统计表gp_subject_statistics 状态更新成已完成 同时更新 gp_subject_student剩余学生拒绝
     * 2、接收成功/拒绝 更新接收数据gp_subject_statistics
     */
    HttpResult accept(GpSubjectStudent student);

    /**
     * 拒绝
     */
    HttpResult refuse(GpSubjectStudent student);

    /**
     * 根据学生编号查询选题结果
     */
    HttpResult choiceResultList(String studentNo);

    /**
     * 根据ID获取 学生ID
     *
     * @param id 记录ID
     * @return 学生id
     */
    Long getStudentId(Long id);

}

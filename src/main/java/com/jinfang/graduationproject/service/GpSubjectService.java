package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.GpSubject;
import com.jinfang.graduationproject.domain.GpSubjectStudent;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import com.jinfang.graduationproject.vo.teacher.leader.topic.ExamineReqVo;
import com.jinfang.graduationproject.vo.teacher.topic.GpSubjectSaveResqVo;

/**
 * --------------------------- 课题表 (GpSubjectService)
 * --------------------------- 作者： lz 时间： 2020-08-14 23:07:27 说明：
 * ---------------------------
 */
public interface GpSubjectService extends CurdService<GpSubject> {

    /**
     * 新增课题
     */
    HttpResult saveOrUpdateSubject(GpSubjectSaveResqVo resqVo, LoginUserMeta loginUserMeta);

    /**
     * 送审
     */
    HttpResult censorship(Long id);

    /**
     * 申请修改
     */
    HttpResult applyModify(Long id, Long teacherId);

    /**
     * 根据老师获取通过课题数据
     */
    HttpResult getSubBySubmitUserList(String submitUser);

    /**
     * 根据id获取明细数据
     *
     * @param id id
     */
    HttpResult details(Long id);

    /**
     * 选题-选择课题
     *
     * @param record 记录
     */
    HttpResult studentChoiceTopic(GpSubjectStudent record);

    /**
     * 获取专业负责人 查询审核列表
     */
    HttpResult findExaminePage(PageRequest pageRequest);

    /**
     * 专业负责人 开放修改权限
     *
     * @param id        课题ID
     * @param teacherId 教师ID
     */
    HttpResult modifyJurisdiction(Long id, String teacherId);

    /**
     * 专业负责人 审核
     *
     */
    HttpResult examine(ExamineReqVo reqVo);



}

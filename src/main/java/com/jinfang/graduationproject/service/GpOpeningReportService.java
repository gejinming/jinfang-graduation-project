package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.GpOpeningReport;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.vo.student.opening.WriteOpeningReqVo;
import com.jinfang.graduationproject.vo.teacher.opening.QueryOpeningResqVo;

/**
 * ---------------------------
 * 开题报告 (GpOpeningReportService)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-14 23:07:27
 * 说明：
 * ---------------------------
 */
public interface GpOpeningReportService extends CurdService<GpOpeningReport> {
    /**
     * 查看/检查
     */
    HttpResult examine(QueryOpeningResqVo resqVo);

    /**
     * 根据学生编号查询开题数据
     */
    HttpResult getByStudentNoOpeningList(PageRequest pageRequest);

    /**
     * 编写
     *
     * @param record 开题报告记录
     * @return 处理结果
     */
    HttpResult write(WriteOpeningReqVo record);

    /**
     * 提交
     *
     * @param id        报告ID
     * @param studentNo 操作工号
     */
    HttpResult add(Long id, String studentNo);

    /**
     * 根据课题ID获取开题报告信息
     *
     * @param subjectStudentId 课题学生ID
     * @return 开题报告信息
     */
    GpOpeningReport getBySubjectStudentId(Long subjectStudentId);
}

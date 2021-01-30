package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.GpWorkRecord;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.vo.record.WorkRecordAddReq;

import java.util.List;

public interface WorkRecordService {

    PageResult findPage(PageRequest pageRequest);

    /**
     * 增加
     *
     * @param workRecordAddReq 记录请求
     * @return 回复
     */
    HttpResult save(WorkRecordAddReq workRecordAddReq);

    /**
     * 根据课题ID和学生ID查询 列表记录
     *
     * @param subjectId 课题ID
     * @param studentId 学生ID
     * @return 列表
     */
    List<GpWorkRecord> findList(Long subjectId, Long studentId);
}

package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.vo.workinstruction.WorkInstructionAddReq;
import com.jinfang.graduationproject.vo.workinstruction.WorkInstructionDetailRes;

public interface WorkInstructionService {

    PageResult findPage(PageRequest pageRequest);

    /**
     * 增加
     *
     * @param workInstructionAddReq 记录请求
     * @return 相应数据
     */
    HttpResult save(WorkInstructionAddReq workInstructionAddReq);

    /**
     * 根据课题ID和学生ID查询 列表记录
     *
     * @param subjectId 课题ID
     * @param studentId 学生ID
     * @return 列表
     */
    WorkInstructionDetailRes findDetail(Long subjectId, Long studentId);
}

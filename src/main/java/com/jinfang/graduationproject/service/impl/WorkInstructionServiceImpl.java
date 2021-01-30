package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.domain.GpStudentLeaveRecord;
import com.jinfang.graduationproject.domain.GpTeacherAnswerQuestion;
import com.jinfang.graduationproject.domain.GpWorkInstructionProcess;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpStudentLeaveRecordMapper;
import com.jinfang.graduationproject.mapper.GpTeacherAnswerQuestionMapper;
import com.jinfang.graduationproject.mapper.GpWorkInstructionProcessMapper;
import com.jinfang.graduationproject.service.WorkInstructionService;
import com.jinfang.graduationproject.vo.workinstruction.WorkInstructionAddReq;
import com.jinfang.graduationproject.vo.workinstruction.WorkInstructionDetailRes;
import com.jinfang.graduationproject.vo.workinstruction.WorkInstructionPageRes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@AllArgsConstructor
public class WorkInstructionServiceImpl implements WorkInstructionService {

    private GpWorkInstructionProcessMapper gpWorkInstructionProcessMapper;
    private GpTeacherAnswerQuestionMapper gpTeacherAnswerQuestionMapper;
    private GpStudentLeaveRecordMapper gpStudentLeaveRecordMapper;

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        String status = pageRequest.getAttrValue("status");
        PageResult page = MybatisPageHelper.findPage(pageRequest, gpWorkInstructionProcessMapper, "findPage",
                (Long) pageRequest.getExtProps().get("teacherId"),
                pageRequest.getAttrValue("grade"), pageRequest.getAttrValue("subjectName"),
                status);

        if (page != null && page.getContent().size() > 0) {
            Integer statusInt = !StringUtils.isEmpty(status) ? Integer.parseInt(status) : null;


            for (Object data : page.getContent()) {
                WorkInstructionPageRes response = (WorkInstructionPageRes) data;
                if (statusInt != null) {
                    response.setStatus(statusInt);
                    continue;
                }

                WorkInstructionDetailRes detail = findDetail(response.getSubjectId(), response.getStudentId());

                if (!CollectionUtils.isEmpty(detail.getWorkInstructionProcesses())
                        || !CollectionUtils.isEmpty(detail.getTeacherAnswerQuestions())
                        || !CollectionUtils.isEmpty(detail.getStudentLeaveRecords())) {
                    response.setStatus(1);
                }
            }
        }

        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult save(WorkInstructionAddReq workInstructionAddReq) {
        if (workInstructionAddReq == null
                || (CollectionUtils.isEmpty(workInstructionAddReq.getWorkInstructionProcesses())
                && CollectionUtils.isEmpty(workInstructionAddReq.getTeacherAnswerQuestions()))) {
            return HttpResult.error("参数为空");
        }

        boolean isOk = saveGpWorkInstructionProcess(workInstructionAddReq);
        if (isOk) {
            isOk = saveTeacherAnswerQuestions(workInstructionAddReq);
        }

        if (isOk) {
            isOk = saveStudentLeaveRecord(workInstructionAddReq);
        }

        if (!isOk) {
            log.error("Failed to batch save data[{}]", workInstructionAddReq);
            throw new RuntimeException("操作失败");
        }

        return HttpResult.ok("操作成功");
    }

    private boolean saveGpWorkInstructionProcess(WorkInstructionAddReq workInstructionAddReq) {
        if (CollectionUtils.isEmpty(workInstructionAddReq.getWorkInstructionProcesses())) {
        	 gpWorkInstructionProcessMapper.deleteBySubjectIdAndStudentId(workInstructionAddReq.getSubjectId(),
                     workInstructionAddReq.getStudentId());
            return true;
        }

        for (GpWorkInstructionProcess workInstructionProcess : workInstructionAddReq.getWorkInstructionProcesses()) {
            workInstructionProcess.setSubjectId(workInstructionAddReq.getSubjectId());
            workInstructionProcess.setStudentId(workInstructionAddReq.getStudentId());
            workInstructionProcess.setOperateUser(workInstructionAddReq.getOperator());
        }

        gpWorkInstructionProcessMapper.deleteBySubjectIdAndStudentId(workInstructionAddReq.getSubjectId(),
                workInstructionAddReq.getStudentId());

        int rows = gpWorkInstructionProcessMapper.batchInsert(workInstructionAddReq.getWorkInstructionProcesses());
        return rows == workInstructionAddReq.getWorkInstructionProcesses().size();
    }

    private boolean saveTeacherAnswerQuestions(WorkInstructionAddReq workInstructionAddReq) {
        if (CollectionUtils.isEmpty(workInstructionAddReq.getTeacherAnswerQuestions())) {
        	gpTeacherAnswerQuestionMapper.deleteBySubjectIdAndStudentId(workInstructionAddReq.getSubjectId(),
                    workInstructionAddReq.getStudentId());
            return true;
        }

        for (GpTeacherAnswerQuestion teacherAnswerQuestion : workInstructionAddReq.getTeacherAnswerQuestions()) {
            teacherAnswerQuestion.setSubjectId(workInstructionAddReq.getSubjectId());
            teacherAnswerQuestion.setStudentId(workInstructionAddReq.getStudentId());
            teacherAnswerQuestion.setOperateUser(workInstructionAddReq.getOperator());
        }

        gpTeacherAnswerQuestionMapper.deleteBySubjectIdAndStudentId(workInstructionAddReq.getSubjectId(),
                workInstructionAddReq.getStudentId());

        int rows = gpTeacherAnswerQuestionMapper.batchInsert(workInstructionAddReq.getTeacherAnswerQuestions());
        return rows == workInstructionAddReq.getTeacherAnswerQuestions().size();
    }

    private boolean saveStudentLeaveRecord(WorkInstructionAddReq workInstructionAddReq) {
        if (CollectionUtils.isEmpty(workInstructionAddReq.getStudentLeaveRecords())) {
        	 gpStudentLeaveRecordMapper.deleteBySubjectIdAndStudentId(workInstructionAddReq.getSubjectId(),
                     workInstructionAddReq.getStudentId());
            return true;
        }

        for (GpStudentLeaveRecord studentLeaveRecord : workInstructionAddReq.getStudentLeaveRecords()) {
            studentLeaveRecord.setSubjectId(workInstructionAddReq.getSubjectId());
            studentLeaveRecord.setStudentId(workInstructionAddReq.getStudentId());
            studentLeaveRecord.setOperateUser(workInstructionAddReq.getOperator());
        }

        gpStudentLeaveRecordMapper.deleteBySubjectIdAndStudentId(workInstructionAddReq.getSubjectId(),
                workInstructionAddReq.getStudentId());

        int rows = gpStudentLeaveRecordMapper.batchInsert(workInstructionAddReq.getStudentLeaveRecords());
        return rows == workInstructionAddReq.getStudentLeaveRecords().size();
    }


    @Override
    public WorkInstructionDetailRes findDetail(Long subjectId, Long studentId) {
        return WorkInstructionDetailRes.builder()
                .workInstructionProcesses(gpWorkInstructionProcessMapper.selectBySubjectId(subjectId, studentId))
                .teacherAnswerQuestions(gpTeacherAnswerQuestionMapper.selectBySubjectId(subjectId, studentId))
                .studentLeaveRecords(gpStudentLeaveRecordMapper.selectBySubjectId(subjectId, studentId)).build();
    }

}

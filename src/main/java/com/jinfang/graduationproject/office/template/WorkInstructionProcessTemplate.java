package com.jinfang.graduationproject.office.template;

import com.deepoove.poi.config.Configure;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import com.jinfang.graduationproject.domain.CcTeacher;
import com.jinfang.graduationproject.domain.GpStudentLeaveRecord;
import com.jinfang.graduationproject.domain.GpTeacherAnswerQuestion;
import com.jinfang.graduationproject.domain.GpWorkInstructionProcess;
import com.jinfang.graduationproject.office.BaseOfficeTemplate;
import com.jinfang.graduationproject.office.FileName;
import com.jinfang.graduationproject.service.TeacherService;
import com.jinfang.graduationproject.service.WorkInstructionService;
import com.jinfang.graduationproject.vo.score.SubjectStudentCompleteVo;
import com.jinfang.graduationproject.vo.workinstruction.WorkInstructionDetailRes;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class WorkInstructionProcessTemplate extends BaseOfficeTemplate {

    private TeacherService teacherService;
    private WorkInstructionService workInstructionService;

    @Override
    protected Map<String, Object> transformParams(SubjectStudentCompleteVo completeVo) {
        Map<String, Object> params = new HashMap<>();

        params.put("studentName", completeVo.getStudentName());
        params.put("teacherName", completeVo.getTeacherName());
        params.put("instituteName", completeVo.getInstituteName());
        params.put("majorName", completeVo.getMajorName());
        params.put("className", completeVo.getClassName());

        WorkInstructionDetailRes workInstructionDetailRes = workInstructionService.findDetail(completeVo.getSubjectId(),
                completeVo.getStudentId());

        setProcesses(params, workInstructionDetailRes.getWorkInstructionProcesses());

        setTeacherAnswers(params, workInstructionDetailRes.getTeacherAnswerQuestions());

        setStudentLeaves(params, workInstructionDetailRes.getStudentLeaveRecords());


        return params;
    }

    /**
     * 设置计划进程表
     *
     * @param params 参数
     */
    private void setProcesses(Map<String, Object> params, List<GpWorkInstructionProcess> workInstructionProcesses) {
        List<Map<String, Object>> processes = new ArrayList<>();

        if (CollectionUtils.isEmpty(workInstructionProcesses)) {
            return;
        }

        workInstructionProcesses.forEach(wip -> {
            Map<String, Object> process = new HashMap<>();
            process.put("processTime", DateFormatUtils.format(wip.getProcessTime(), "yyyy年MM月dd日"));
            process.put("content", wip.getContent());
            process.put("result", wip.getResult());
            process.put("teacherName", getTeacherName(wip.getOperateUser()));

            processes.add(process);
        });

        params.put("processes", processes);
    }

    /**
     * 设置指导教师检查答疑记录
     *
     * @param params 参数
     */
    private void setTeacherAnswers(Map<String, Object> params, List<GpTeacherAnswerQuestion> teacherAnswerQuestions) {
        List<Map<String, Object>> answers = new ArrayList<>();
        if (CollectionUtils.isEmpty(teacherAnswerQuestions)) {
            return;
        }

        teacherAnswerQuestions.forEach(as -> {
            Map<String, Object> answer = new HashMap<>();
            answer.put("recordTime", DateFormatUtils.format(as.getRecordTime(), "yyyy年MM月dd日"));
            answer.put("content", as.getContent());
            answer.put("teacherName", getTeacherName(as.getOperateUser()));

            answers.add(answer);
        });

        params.put("answers", answers);
    }


    /**
     * 设置学生请假记录
     *
     * @param params 参数
     */
    private void setStudentLeaves(Map<String, Object> params, List<GpStudentLeaveRecord> studentLeaveRecords) {
        List<Map<String, Object>> leaves = new ArrayList<>();
        if (CollectionUtils.isEmpty(studentLeaveRecords)) {
            return;
        }

        studentLeaveRecords.forEach(l -> {
            Map<String, Object> leave = new HashMap<>();
            leave.put("recordTime", DateFormatUtils.format(l.getRecordTime(), "yyyy年MM月dd日"));
            leave.put("content", l.getContent());
            leave.put("teacherName", getTeacherName(l.getOperateUser()));

            leaves.add(leave);
        });

        params.put("leaves", leaves);
    }

    private String getTeacherName(String teacherId) {
        if (StringUtils.isEmpty(teacherId)) {
            return "";
        }

        CcTeacher teacher = teacherService.findById(Long.valueOf(teacherId));
        if (teacher == null) {
            return "";
        }

        return teacher.getName();
    }

    @Override
    protected FileName.Mapping fileMapping() {
        return FileName.Mapping.WORK_INSTRUCTION_PROCESS;
    }

    @Override
    protected Configure getConfigure() {
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();

        return Configure.newBuilder().bind("processes", policy)
                .bind("answers", policy).bind("leaves", policy).build();
    }

}

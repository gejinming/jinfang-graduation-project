package com.jinfang.graduationproject.vo.workinstruction;

import java.util.ArrayList;
import java.util.List;

import com.jinfang.graduationproject.domain.GpStudentLeaveRecord;
import com.jinfang.graduationproject.domain.GpTeacherAnswerQuestion;
import com.jinfang.graduationproject.domain.GpWorkInstructionProcess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkInstructionAddReq {

    /**
     * 课题id
     */
    private Long subjectId;

    /**
     * 学生id
     */
    private Long studentId;

    /**
     * 计划进程表
     */
    private List<GpWorkInstructionProcess> workInstructionProcesses = new ArrayList<>();

    /**
     * 指导教师检查答疑记录
     */
    private List<GpTeacherAnswerQuestion> teacherAnswerQuestions = new ArrayList<>();

    /**
     * 学生请假记录
     */
    private List<GpStudentLeaveRecord> studentLeaveRecords = new ArrayList<>();

    /**
     * 操作账号（前端忽略此值）
     */
    private String operator;

}

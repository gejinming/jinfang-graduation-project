package com.jinfang.graduationproject.vo.workinstruction;

import com.jinfang.graduationproject.domain.GpStudentLeaveRecord;
import com.jinfang.graduationproject.domain.GpTeacherAnswerQuestion;
import com.jinfang.graduationproject.domain.GpWorkInstructionProcess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkInstructionDetailRes {

    /**
     * 计划进程表
     */
    @Builder.Default
    private List<GpWorkInstructionProcess> workInstructionProcesses = new ArrayList<>();

    /**
     * 指导教师检查答疑记录
     */
    @Builder.Default
    private List<GpTeacherAnswerQuestion> teacherAnswerQuestions = new ArrayList<>();

    /**
     * 学生请假记录
     */
    @Builder.Default
    private List<GpStudentLeaveRecord> studentLeaveRecords = new ArrayList<>();

}

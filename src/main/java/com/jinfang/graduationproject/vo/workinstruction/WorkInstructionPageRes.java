package com.jinfang.graduationproject.vo.workinstruction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkInstructionPageRes {

    /**
     * 课题id
     */
    private Long subjectId;


    /**
     * 届别
      */
    private String grade;

    /**
     * 课题名称
     */
    private String subjectName;

    /**
     * 学生id
     */
    private Long studentId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 状态 0：未完成， 1：已完成
     */
    private int status = 0;

}

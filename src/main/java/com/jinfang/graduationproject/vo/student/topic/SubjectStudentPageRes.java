package com.jinfang.graduationproject.vo.student.topic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生选题分页列表内容【学生侧】
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectStudentPageRes {

    /**
     * 课题ID
     */
    private Long subjectId;

    /**
     * 课题名称
     */
    private String subjectName;

    /**
     * 指导教师姓名
     */
    private String teacherName;

    /**
     * 限选学生数
     */
    private int memberLimit;

    /**
     * 已接收学生数
     */
    private int acceptAmount;

    /**
     * 已选择学生数
     */
    private int waitingAmount;

    /**
     * 是否允许操作，默认允许操作
     */
    private boolean isAllowOperate = true;
}

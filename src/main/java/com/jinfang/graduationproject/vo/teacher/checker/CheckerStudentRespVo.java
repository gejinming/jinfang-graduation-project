
package com.jinfang.graduationproject.vo.teacher.checker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 评阅教师回执信息
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckerStudentRespVo implements Serializable {

    /**
     * 课题ID
     */
    private Long subjectId;

    /**
     * 课题名称
     */
    private String subjectName;

    /**
     * 学生ID
     */
    private Long studentId;

    /**
     * 学生学号
     */
    private String studentNo;

    /**
     * 教学班Id
     */
    private Long eduClassId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 教师ID
     */
    private String teacherId;

    /**
     * 教师姓名
     */
    private String teacherName;


}

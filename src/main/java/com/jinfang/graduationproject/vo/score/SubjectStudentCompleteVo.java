package com.jinfang.graduationproject.vo.score;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectStudentCompleteVo {

    private Long subjectStudentId;

    /**
     * 届别
     */
    private String grade;

    /**
     * 学校ID
     */
    private Long schoolId;

    /**
     * 指导教师ID
     */
    private Long teacherId;

    /**
     * 指导教师姓名
     */
    private String teacherName;

    /**
     * 教师职称
     */
    private Integer teacherJobTitle;

    /**
     * 学生ID
     */
    private Long studentId;

    /**
     * 学生工号
     */
    private Long studentNo;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学生性别
     */
    private int studentSex;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 专业名称
     */
    private String majorName;

    /**
     * 学院名称
     */
    private String instituteName;

    /**
     * 课题ID
     */
    private Long subjectId;

    /**
     * 课题名称
     */
    private String subjectName;

    /**
     * 课题类型 1：毕业设计，2：毕业论文
     */
    private int subjectType;

    /**
     * 课题性质
     */
    private int subjectNature;

    /**
     * 课题来源
     */
    private int subjectSource;

    /**
     * 得分
     */
    private Double score;
}
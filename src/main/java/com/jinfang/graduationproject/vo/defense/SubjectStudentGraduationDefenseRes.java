package com.jinfang.graduationproject.vo.defense;

import com.jinfang.graduationproject.domain.GpDefenseTeacher;
import com.jinfang.graduationproject.domain.GpGraduationDefense;
import com.jinfang.graduationproject.domain.GpGraduationDefenseScore;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 学生课题答辩记录联查 响应（毕业答辩)
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectStudentGraduationDefenseRes extends GpGraduationDefense {

    /**
     * 课题名称
     */
    private String subjectName;

    /**
     * 课题类型
     */
    private int subjectType;

    /**
     * 课题来源
     */
    private int subjectSource;

    /**
     * 课题性质
     */
    private int subjectNature;

    /**
     * 专业名称
     */
    private String majorName;

    /**
     * 学生ID
     */
    private Long studentId;

    /**
     * 学生工号
     */
    private String studentNo;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 年级
     */
    private String grade;

    /**
     * 指导教师姓名
     */
    private String teacherName;

    /**
     * 答辩时间
     */
    private Date defenseTime;

    /**
     * 答辩地点
     */
    private String defenseAddress;

    /**
     * 答辩组成员
     */
    private List<GpDefenseTeacher> defenseGroupMembers = new ArrayList<>();

    /**
     * 记录员姓名
     */
    private String recordTeacherName;

    /**
     * 毕业答辩评分
     */
    private List<GpGraduationDefenseScore> scoreList = new ArrayList<>();

}

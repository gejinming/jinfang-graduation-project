package com.jinfang.graduationproject.vo.defense;

import com.jinfang.graduationproject.domain.GpOpeningDefense;
import com.jinfang.graduationproject.domain.GpOpeningDefenseScore;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 学生课题答辩记录联查 响应（适用于开题答辩)
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectStudentOpeningDefenseRes extends GpOpeningDefense {

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
     * 课题指导教师姓名
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
     * 开题报告指导教师建议
     */
    private String openingReportSuggest;

    /**
     * 教师组评价（开题答辩）
     */
    private String teacherGroupComment;

    /**
     * 开题答辩评分
     */
    @Builder.Default
    private List<GpOpeningDefenseScore> scoreList = new ArrayList<>();

}

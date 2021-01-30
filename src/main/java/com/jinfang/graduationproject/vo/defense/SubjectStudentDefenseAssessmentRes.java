package com.jinfang.graduationproject.vo.defense;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 答辩评语表 响应
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectStudentDefenseAssessmentRes {

    /**
     * id为空时没有记录，此时状态应该为 未填写，有值则为 已填写
     */
    private Long id;

    /**
     * 课题Id
     */
    private Long subjectId;

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


}

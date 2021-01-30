package com.jinfang.graduationproject.vo.score;

import com.jinfang.graduationproject.domain.GpSubjectStudentScore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectStudentScorePageRes extends GpSubjectStudentScore {

    /**
     * 课题名称
     */
    private String subjectName;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 届别
     */
    private String grade;

    /**
     * 班级
     */
    private String className;
}

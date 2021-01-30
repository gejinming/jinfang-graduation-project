package com.jinfang.graduationproject.vo.teacher.guider;

import com.jinfang.graduationproject.domain.GpInspection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectStudentInspectionRes extends GpInspection implements Serializable {

    /**
     * 课题名称
     */
    private String subjectName;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学生班级
     */
    private String className;

    /**
     * 学院信息
     */
    private String instituteName;

    /**
     * 届别
     */
    private String grade;
}

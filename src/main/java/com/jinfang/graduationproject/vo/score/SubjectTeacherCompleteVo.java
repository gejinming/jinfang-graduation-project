package com.jinfang.graduationproject.vo.score;

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
public class SubjectTeacherCompleteVo {

    private Long schoolId;

    private Long teacherId;

    /**
     * 指导教师姓名
     */
    private String teacherName;

    /**
     * 专业名称
     */
    private String majorName;

    /**
     * 学院名称
     */
    private String instituteName;

    private List<SubjectStudentCompleteVo> subjectStudentCompleteVos;
}
package com.jinfang.graduationproject.office.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentSubjectRowVo {

    /**
     * 课题名称
     */
    private String subjectName;

    /**
     * 毕业设计选中 √
     */
    private String d;

    /**
     * 毕业论文选中 √
     */
    private String p;

    /**
     * 课题性质
     */
    private String nature;

    /**
     * 课题来源
     */
    private String source;

    /**
     * 学生姓名
     */
    private String studentName;
}

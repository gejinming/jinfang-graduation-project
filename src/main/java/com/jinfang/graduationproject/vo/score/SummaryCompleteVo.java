package com.jinfang.graduationproject.vo.score;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SummaryCompleteVo {

    private Long schoolId;

    /**
     * 专业名称
     */
    @Builder.Default
    private String majorName = "";

    /**
     * 学院名称
     */
    @Builder.Default
    private String instituteName = "";

    private List<SubjectStudentCompleteVo> subjectStudentCompleteVos;
}
package com.jinfang.graduationproject.vo.teacher.checker.assessment;

import java.io.Serializable;

import com.jinfang.graduationproject.domain.GpCheckAssessment;

import lombok.Data;

/**
 * @author lz
 * @Description: 根据状态 查询所有需要评学生数据
 * @date 2020年9月6日
 */
@Data
public class FindCheckAssessmentRespVo extends GpCheckAssessment implements Serializable {

    private static final long serialVersionUID = 4036381851127819601L;
    // 届别
    private String grade;
    // 课题名称
    private String subjectName;
    // 课题id
    private Long subjectId;
    // 学生名称
    private String studentName;
    // 班级
    private String className;
    // 专业
    private String majorName;
    // 院校
    private String collegeName;

    /**
     * 论文文件ID
     */
    private Long dissertationFileId;

    /**
     * 论文相似度文件ID
     */
    private Long dissertationSimilarityFileId;

}

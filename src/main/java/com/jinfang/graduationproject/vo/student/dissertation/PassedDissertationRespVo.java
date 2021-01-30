
package com.jinfang.graduationproject.vo.student.dissertation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 已通过论文
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassedDissertationRespVo implements Serializable {

    /**
     * 论文ID
     */
    private Long id;

    /**
     * 届别
     */
    private String grade;

    /**
     * 课题ID
     */
    private Long subjectId;
    /**
     * 毕业论文id
     */
    private Long fileId;
    /**
     * 毕业论文相似度id
     */
    private Long similarityFileId;

    /**
     * 课题名称
     */
    private String subjectName;


    /**
     * 学生ID
     */
    private Long studentId;

    /**
     * 学生编号
     */
    private String studentNo;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学生班级
     */
    private String className;
    

}

package com.jinfang.graduationproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ---------------------------
 * (CcCoursePeriode)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-30 17:46:43
 * 说明：
 * ---------------------------
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CcCoursePeriode {

    /**
     *
     */
    private Long id;
    /**
     * 届别
     */
    private String periodDate;
    /**
     * 课程id
     */
    private Long courseId;
    /**
     * 创建日期
     */
    private java.util.Date createDate;
    /**
     * 修改日期
     */
    private java.util.Date modifyDate;
    /**
     * 开启老师id
     */
    private Long teacherId;
    /**
     * 学校id
     */
    private Long schoolId;

    private Long classId;
    /**
     * 删除标识
     */
    private Integer isDel;

    /**
     * 专业名称
     */
    private String majorName;

    /**
     * 学院名称
     */
    private String instituteName;

}
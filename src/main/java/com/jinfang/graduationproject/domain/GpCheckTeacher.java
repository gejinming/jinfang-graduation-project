package com.jinfang.graduationproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ---------------------------
 * 评阅老师学生关系表 (GpCheckTeacher)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-27 00:18:11
 * 说明：
 * ---------------------------
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GpCheckTeacher {

    /**
     *
     */
    private Long id;
    /**
     * 答辩组教师ID
     */
    private Long defenseTeacherId;
    /**
     * 学生ID
     */
    private Long studentId;

    /**
     * 行政班ID
     */
    private Long eduClassId;
    /**
     * 操作账号
     */
    private String operateUser;
    /**
     * 创建时间
     */
    private java.util.Date createDate;
    /**
     * 最后修改时间
     */
    private java.util.Date modifyDate;
    /**
     * 是否删除 0 :未删除，1：已删除
     */
    private Integer isDel;

}
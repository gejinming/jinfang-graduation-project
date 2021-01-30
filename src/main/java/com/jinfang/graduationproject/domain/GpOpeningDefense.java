package com.jinfang.graduationproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------
 * 评阅老师学生关系表 (GpOpeningDefense)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-27 00:18:12
 * 说明：
 * ---------------------------
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GpOpeningDefense {

    /**
     *
     */
    private Long id;
    /**
     * 学生选题ID
     */
    private Long subjectStudentId;
    /**
     * 答辩时间
     */
    private java.util.Date defenseTime;
    /**
     * 答辩地址
     */
    private String defenseAddress;
    /**
     * 答辩组评语
     */
    private String teacherGroupComment;
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

    /**
     * 总得分
     */
    private Double totalScores;

    /**
     * 答辩组组长
     */
    private String headmanName;

    /**
     * 评分列表
     */
    private List<GpOpeningDefenseScore> scoreList = new ArrayList<>();

}
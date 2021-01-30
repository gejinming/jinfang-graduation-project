package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.CcScoreStuIndigrade;

/**
 * ---------------------------
 * 考核成绩分析法学生课程目标成绩 (CcScoreStuIndigradeService)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-07 21:30:49
 * 说明：
 * ---------------------------
 */
public interface CcScoreStuIndigradeService extends CurdService<CcScoreStuIndigrade> {

    /**
     * 将评分值添加到最终的 课程组成目标 分数记录中
     *
     * @param courseGradecomposeId 成绩组成ID
     * @param indicationId         课程目标ID
     * @param studentId            学生ID
     * @param score                评分
     * @return 处理结果
     */
    boolean add(Long courseGradecomposeId, Long indicationId, Long studentId, int score);

}

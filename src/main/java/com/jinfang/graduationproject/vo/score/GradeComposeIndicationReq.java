package com.jinfang.graduationproject.vo.score;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeComposeIndicationReq implements Serializable {

    private static final long serialVersionUID = -3841068859167708099L;

    /**
     * 课程组合ID
     */
    private Long courseGradecomposeId;

    /**
     * 课程目标ID
     */
    private Long id;

    /**
     * 课程目标
     */
    private String content;

    /**
     * 满分/最高分（每个课程目标的评分上限数）
     */
    private BigDecimal maxScore;

    /**
     * 序号
     */
    private int sort;
    //score
    private String score;

}

package com.jinfang.graduationproject.vo.score;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeComposeReq implements Serializable {

    /**
     * 课程组合ID
     */
    private Long id;

    /**
     * 组合成绩名称
     */
    private String name;

    /**
     * 该成绩对应的 评分角色
     */
    private String roles;
    
    //评分项集合
    private List<GradeComposeIndicationReq> indicationlist;

}

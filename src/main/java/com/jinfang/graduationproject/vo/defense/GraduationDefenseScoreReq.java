package com.jinfang.graduationproject.vo.defense;

import com.jinfang.graduationproject.domain.GpGraduationDefenseScore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 毕业答辩评分传参
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("毕业答辩评分请求")
public class GraduationDefenseScoreReq {

    @ApiModelProperty(value = "毕业答辩记录ID", required = true)
    private Long id;

    @ApiModelProperty(value = "多个课程目标评分记录", required = true)
    private List<GpGraduationDefenseScore> graduationDefenseScoreList;

}

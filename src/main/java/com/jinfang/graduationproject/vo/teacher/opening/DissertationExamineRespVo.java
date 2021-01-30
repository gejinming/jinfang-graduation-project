package com.jinfang.graduationproject.vo.teacher.opening;

import java.util.List;

import com.jinfang.graduationproject.domain.GpDissertation;
import com.jinfang.graduationproject.domain.GpDissertationScore;

import lombok.Data;

/**
 * 
 * @Description: 查看/检查
 * @author lz
 * @date 2020年9月9日
 *
 */
@Data
public class DissertationExamineRespVo extends GpDissertation {

	// 评分项
	private List<GpDissertationScore> scoreList;

}

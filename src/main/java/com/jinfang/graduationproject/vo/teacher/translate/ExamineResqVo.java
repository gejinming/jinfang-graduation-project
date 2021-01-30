package com.jinfang.graduationproject.vo.teacher.translate;

import java.io.Serializable;
import java.util.List;

import com.jinfang.graduationproject.domain.GpTranslation;
import com.jinfang.graduationproject.domain.GpTranslationScore;

import lombok.Data;

/**
 * 
 * @Description:查看/检查
 * @author lz
 * @date 2020年9月9日
 *
 */
@Data
public class ExamineResqVo extends GpTranslation implements Serializable {

	private static final long serialVersionUID = -3788771195125730627L;
	// 评分项
	private List<GpTranslationScore> translationScoreList;

}

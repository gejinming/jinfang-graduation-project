package com.jinfang.graduationproject.vo.teacher.translate;

import java.util.List;

import com.jinfang.graduationproject.domain.GpTranslation;
import com.jinfang.graduationproject.domain.GpTranslationScore;

import lombok.Data;

@Data
public class QueryTranslateRespVo extends GpTranslation{
	
	//学生姓名
	private String studentName;
	//年级
	private String className;
	//评分项
	private List<GpTranslationScore> scoreList;
	
	

}

package com.jinfang.graduationproject.vo.teacher.guider.dissertation;

import java.io.Serializable;
import java.util.List;

import com.jinfang.graduationproject.domain.GpDissertation;
import com.jinfang.graduationproject.domain.GpDissertationScore;

import lombok.Data;

@Data
public class FindPageByNameRespVo extends GpDissertation implements Serializable{

	private static final long serialVersionUID = 5803643688232359440L;
	//学生姓名
	private String studentName;
	//年级
	private String className;
	//评分项
	private List<GpDissertationScore> scoreList;
	
}

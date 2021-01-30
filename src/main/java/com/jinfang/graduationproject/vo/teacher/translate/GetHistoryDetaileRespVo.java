package com.jinfang.graduationproject.vo.teacher.translate;

import com.jinfang.graduationproject.domain.GpTranslationHistory;

import lombok.Data;

@Data
public class GetHistoryDetaileRespVo extends GpTranslationHistory {

	// 课题名称
	private String subjectName;
	// 学生姓名
	private String studentName;

}

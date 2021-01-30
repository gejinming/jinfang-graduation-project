package com.jinfang.graduationproject.vo.teacher.opening;

import java.util.List;

import com.jinfang.graduationproject.domain.GpOpeningReportHistory;
import com.jinfang.graduationproject.domain.GpOpeningReportLiteratureHistory;
import com.jinfang.graduationproject.domain.GpOpeningReportScoreHistory;

import lombok.Data;

@Data
public class GetHistoryDetaileRespVo extends GpOpeningReportHistory {

	// 课题名称
	private String subjectName;
	// 学生姓名
	private String studentName;
	//文献集合
	private List<GpOpeningReportLiteratureHistory> literatureList;
	//评分集合
	private List<GpOpeningReportScoreHistory> scoreTypeOptionList;
}

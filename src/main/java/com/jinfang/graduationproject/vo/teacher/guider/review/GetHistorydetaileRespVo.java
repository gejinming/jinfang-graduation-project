package com.jinfang.graduationproject.vo.teacher.guider.review;

import java.util.List;

import com.jinfang.graduationproject.domain.GpReviewHistory;
import com.jinfang.graduationproject.domain.GpReviewLiteratureHistory;
import com.jinfang.graduationproject.domain.GpReviewScoreHistory;

import lombok.Data;

/**
 * 根据id获取历史明细
 */
@Data
public class GetHistorydetaileRespVo extends GpReviewHistory{
	
	//课题名称
	private String subjectName;
	//学生姓名
	private String studentName;
	//文献综述集合
	private List<GpReviewLiteratureHistory> literatureList;
	//综合评分
	private List<GpReviewScoreHistory> scoreTypeOptionList;

	
}

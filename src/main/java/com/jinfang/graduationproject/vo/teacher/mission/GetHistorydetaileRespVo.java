package com.jinfang.graduationproject.vo.teacher.mission;

import java.util.List;

import com.jinfang.graduationproject.domain.GpMissionHistory;
import com.jinfang.graduationproject.domain.GpMissionLiteratureHistory;
import com.jinfang.graduationproject.domain.GpMissionPlanHistory;

import lombok.Data;

/**
 * 任务历史明细
 */
@Data
public class GetHistorydetaileRespVo extends GpMissionHistory{
	
	//课题名称
	private String subjectName;
	//学生姓名
	private String studentName;
	//计划集合
	private List<GpMissionPlanHistory> missionPlanList;
	//文献集合
	private List<GpMissionLiteratureHistory> literatureList;

}

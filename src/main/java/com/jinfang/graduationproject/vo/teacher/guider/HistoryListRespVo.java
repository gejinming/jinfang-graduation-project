package com.jinfang.graduationproject.vo.teacher.guider;

import java.io.Serializable;

import lombok.Data;

//历史列表
@Data
public class HistoryListRespVo implements Serializable {

	private static final long serialVersionUID = -1473043283617023692L;
	//记录id
	private Long id;
	// 课题名称
	private String subjectName;
	// 指导教师
	private String teacherName;
	// 提交日期
	private java.util.Date submitTime;

}

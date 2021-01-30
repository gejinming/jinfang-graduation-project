package com.jinfang.graduationproject.vo.teacher.mission;

import lombok.Data;

/**
 * 任务对象
 */
@Data
public class FindByMissionIdListRespVo {
	// 记录id
	private Long id;
	// 课题名称
	private String subjectName;
	// 指导教师
	private String teacherName;
	// 提交日期
	private java.util.Date createDate;
	// 接收时间
	private java.util.Date sendTime;
}

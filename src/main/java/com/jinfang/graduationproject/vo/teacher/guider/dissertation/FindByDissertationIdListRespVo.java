package com.jinfang.graduationproject.vo.teacher.guider.dissertation;

import lombok.Data;

@Data
public class FindByDissertationIdListRespVo {
	
	//记录id
	private Long id;
	//文件id
	private Long fileId;
	//相似度文件报告
	private Long similarityFileId;
	//课题名称
	private String subjectName;
	//指导教师
	private String teacherName;
	//学生名称
	private String studentName;
	//审核意见
	private String approveSuggest;
	//提交时间
	private java.util.Date submitTime;
}

package com.jinfang.graduationproject.vo.student.topic;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @Description: TODO(选题结果查询)
 * @author lz
 * @date 2020年9月5日
 *
 */
@Data
public class ChoiceResultListRespVo implements Serializable{
	
	private static final long serialVersionUID = 6009390500340113021L;
	//选题名称
	private String name;
	//选题教师
	private String teacherName;
	//选题状态
	private Long status;
}

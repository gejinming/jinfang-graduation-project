
    /**
    * @Description: TODO(用一句话描述该文件做什么)
    * @author lz
    * @date 2020年9月5日
    * @version V1.0  
    */
    
package com.jinfang.graduationproject.vo.teacher.leader.topic;

import java.io.Serializable;

import lombok.Data;

/**
    * @Description: TODO(专业负责人审核课题)
    * @author lz
    * @date 2020年9月5日
    *
    */

@Data
public class FindExaminePageReqVo implements Serializable{
	
	private static final long serialVersionUID = 4459722610763228032L;
	//届别
	private String grade;
	//教师
	private String teacherName;
	//课题名称
	private String name;
	//课题类型
	private String type;
	//课题来源
	private String source;
	//状态
	private String status;
}

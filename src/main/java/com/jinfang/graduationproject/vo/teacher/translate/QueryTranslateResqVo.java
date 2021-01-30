
package com.jinfang.graduationproject.vo.teacher.translate;

import java.io.Serializable;

import lombok.Data;

/**
 * @Description: TODO(外文翻译)
 * @author lz
 * @date 2020年8月22日
 *
 */
@Data
public class QueryTranslateResqVo implements Serializable{
	    
	private static final long serialVersionUID = -223853034719620727L;
	//届别
	private String grade;
	//操作账号
	private String teacherId;
	// 状态
	private String status;
	//课题名称
	private String subjectName;
	//学生状态 已接收
	private int studentStatus;
	//学校
	private Long schoolId;

}


/**
* @Description: TODO(用一句话描述该文件做什么)
* @author lz
* @date 2020年8月26日
* @version V1.0  
*/

package com.jinfang.graduationproject.vo.teacher.topic;

import java.io.Serializable;

import com.jinfang.graduationproject.domain.GpSubjectStatistics;

/**
 * @Description: TODO(学生选题管理(指导教师))
 * @author lz
 * @date 2020年8月26日
 *
 */

public class FindPageByNameRespVo extends GpSubjectStatistics implements Serializable {

	private static final long serialVersionUID = 1116349952093395345L;
	// 学生姓名 多个
	private String studentNames = "";

	public String getStudentNames() {
		return studentNames;
	}

	public void setStudentNames(String studentNames) {
		this.studentNames = studentNames;
	}

}

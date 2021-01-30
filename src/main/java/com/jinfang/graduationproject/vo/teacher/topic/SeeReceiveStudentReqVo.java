
/**
* @Description: TODO(用一句话描述该文件做什么)
* @author lz
* @date 2020年8月27日
* @version V1.0  
*/

package com.jinfang.graduationproject.vo.teacher.topic;

import java.io.Serializable;

/**
 * @Description: TODO(查询学生信息入参)
 * @author lz
 * @date 2020年8月27日
 *
 */

public class SeeReceiveStudentReqVo implements Serializable {

	private static final long serialVersionUID = 7864346047243076572L;
	/** 课题ID */
	private Long subjectId;
	/** 学生ID */
	private Long studentId;
	/** 状态 0: 待处理 1:已接收 2:已拒绝 */
	private Integer status;

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}

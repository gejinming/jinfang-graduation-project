
/**
* @Description: TODO(用一句话描述该文件做什么)
* @author lz
* @date 2020年8月27日
* @version V1.0  
*/

package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.GpGuideAssessment;
import com.jinfang.graduationproject.dto.http.HttpResult;

/**
 * 指导教师学生评语表
 * @author lz
 * @date 2020年8月27日
 *
 */

public interface GpGuideAssessmentService extends CurdService<GpGuideAssessment> {
	/**
	 * 
	 * @Title: add @Description: TODO(评语填写) @param @param record @param @return
	 *         参数 @return HttpResult 返回类型 @throws
	 */
	HttpResult add(GpGuideAssessment record);

	GpGuideAssessment findBySubjectIdAndStudentId(Long subjectId, Long studentId);
}

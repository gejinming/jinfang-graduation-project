package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.GpMission;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.vo.teacher.mission.QueryMissionRespVo;

/**
 * --------------------------- 任务书 (GpMissionService)
 * --------------------------- 作者： lz 时间： 2020-08-14 23:07:27 说明：
 * ---------------------------
 */
public interface GpMissionService extends CurdService<GpMission> {

	/**
	 * 新增修改
	 */
	HttpResult addAndUpdate(QueryMissionRespVo resqVo);

	/**
	 * 下发任务
	 */
	HttpResult issue(Long id);

	/**
	 * 
	 * @Title: getStudentTaskList @Description: TODO(根据学生id获取任务列表) @param @param
	 *         studentId @param @return 参数 @return HttpResult 返回类型 @throws
	 */
	HttpResult getByStudentNoList(PageRequest pageRequest);

	/**
	 * 
	 * @Title: receive @Description: TODO(接收任务) @param @param id @param @return
	 * 参数 @return HttpResult 返回类型 @throws
	 */
	HttpResult receive(Long id,String studentNo);

	GpMission findBySubjectIdAndStudentId(Long subjectId, Long studentId);
}

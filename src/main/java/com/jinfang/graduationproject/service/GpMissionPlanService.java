package com.jinfang.graduationproject.service;

import java.util.List;

import com.jinfang.graduationproject.domain.GpMissionPlan;

/**
 * --------------------------- 任务计划（一对多） (GpMissionPlanService)
 * --------------------------- 作者： lz 时间： 2020-08-14 23:07:27 说明：
 * ---------------------------
 */
public interface GpMissionPlanService extends CurdService<GpMissionPlan> {

	/**
	 * 批量保存任务数据
	 */
	void batchMissionPlan(List<GpMissionPlan> planList, Long messionId);

	/**
	 * 根据任务id查询
	 *
	 * @return
	 */
	List<GpMissionPlan> findByMessionId(Long messionId);

	/**
	 * 
	 * @Title: deleteByMessionId @Description: TODO(messionId删除) @param @param
	 * messionId 参数 @return void 返回类型 @throws
	 */
	void deleteByMessionId(Long messionId);
}

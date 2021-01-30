package com.jinfang.graduationproject.service;

import java.util.List;

import com.jinfang.graduationproject.domain.GpMissionLiterature;

/**
 * --------------------------- 任务文献（一对多） (GpMissionLiteratureService)
 * --------------------------- 作者： lz 时间： 2020-08-14 23:07:27 说明：
 * ---------------------------
 */
public interface GpMissionLiteratureService extends CurdService<GpMissionLiterature> {
	/**
	 * 批量保存文献数据
	 */
	void batchMissionLiterature(List<GpMissionLiterature> literatureList, Long messionId);

	/**
	 * 
	 * @Title: findByMessionId @Description: TODO(根据任务id查询文献列表) @param @param
	 * messionId @param @return 参数 @return List<GpMissionLiterature>
	 * 返回类型 @throws
	 */
	List<GpMissionLiterature> findByMessionId(Long messionId);
	
	 void deleteByMessionId(Long messionId);
}

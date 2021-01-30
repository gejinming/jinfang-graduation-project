package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.dto.http.HttpResult;

/**
 * 任务历史服务接口类
 */
public interface GpMissionHistoryService {
	
	/**
	 * 根据开题id获取集合
	 * 
	 * @param
	 * @return
	 */
	HttpResult findByMissionIdList(Long missionId);
	
	/**
	 * 
	 * 根据id获取历史明细
	 * @param
	 * @return
	 */
	HttpResult getHistoryDetail(Long id);
}

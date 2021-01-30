package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.dto.http.HttpResult;

/**
 * 开题报告历史服务接口类
 */
public interface GpOpeningReportHistoryService {

	/**
	 * 根据开题id获取集合
	 * 
	 * @param
	 * @return
	 */
	HttpResult getOpeningReportIdList(Long openingReportId);
	
	/**
	 * 
	 * 根据id获取历史明细
	 * @param
	 * @return
	 */
	HttpResult getHistoryDetail(Long id);
}

package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.dto.http.HttpResult;

/**
 * 外文翻译历史服务接口
 */
public interface GpTranslationHistoryService {

	/**
	 * 根据translationId获取集合
	 * 
	 * @param
	 * @return
	 */
	HttpResult getByTranslationIdList(Long translationId);
	
	/**
	 * 
	 * 根据id获取历史明细
	 * @param
	 * @return
	 */
	HttpResult getHistoryDetail(Long id);
}

package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.dto.http.HttpResult;

/**
 * 毕业论文历史接口服务
 */
public interface GpDissertationHistoryService {

	/**
	 * dissertationId查询集合
	 * 
	 * @param
	 * @return
	 */
	HttpResult findByDissertationIdList(Long dissertationId);
}

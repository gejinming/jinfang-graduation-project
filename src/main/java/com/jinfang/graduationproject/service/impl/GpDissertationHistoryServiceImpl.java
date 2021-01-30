package com.jinfang.graduationproject.service.impl;

import org.springframework.stereotype.Service;

import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.mapper.GpDissertationHistoryMapper;
import com.jinfang.graduationproject.service.GpDissertationHistoryService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 毕业论文历史接口服务实现类
 */
@Slf4j
@Service
@AllArgsConstructor
public class GpDissertationHistoryServiceImpl implements GpDissertationHistoryService {

	private GpDissertationHistoryMapper gpDissertationHistoryMapper;

	@Override
	public HttpResult findByDissertationIdList(Long dissertationId) {
		try {
			return HttpResult.ok(gpDissertationHistoryMapper.findByDissertationIdList(dissertationId));
		} catch (Exception e) {
			log.error("执行失败 , dissertationId : {}", dissertationId, e);
			return HttpResult.error(e.getMessage());
		}
	}

}

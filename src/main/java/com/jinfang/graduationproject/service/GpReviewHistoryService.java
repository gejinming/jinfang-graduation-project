package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.dto.http.HttpResult;

/**
 * 
 * @Description: 文献历史记录表
 * @author lz
 * @date 2020年9月18日
 *
 */
public interface GpReviewHistoryService {

	/**
	 * 
	 * @Title: getHistoryById @Description: 根据文献id查询列表数据 @param @param
	 *         reviewId @param @return 参数 @return HttpResult 返回类型 @throws
	 */
	HttpResult getReviewIdList(Long reviewId);

	/**
	 * 
	 * 根据id获取历史明细
	 * @param
	 * @return
	 */
	HttpResult getHistoryDetail(Long id);

}

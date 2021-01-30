package com.jinfang.graduationproject.service;

import java.util.List;

import com.jinfang.graduationproject.domain.GpReviewLiterature;

/**
 * ---------------------------
 * 文献综述文献关系表（一对多） (GpReviewLiteratureService)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-14 23:07:27
 * 说明：  
 * ---------------------------
 */
public interface GpReviewLiteratureService extends CurdService<GpReviewLiterature> {

    /**
     * 根据reviewId 查询数据
     * @param id
     * @return
     */    
    List<GpReviewLiterature> findByReviewIdList(Long reviewId);
    
	/**
	 * 批量保存
	 */
	void batchReviewLiterature(List<GpReviewLiterature> literatureList,Long reviewId);
}

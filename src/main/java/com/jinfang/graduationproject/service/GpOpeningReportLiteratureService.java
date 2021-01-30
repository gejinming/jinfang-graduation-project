package com.jinfang.graduationproject.service;

import java.util.List;

import com.jinfang.graduationproject.domain.GpOpeningReportLiterature;

/**
 * ---------------------------
 * 开题报告文献关系表（一对多） (GpOpeningReportLiteratureService)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-14 23:07:27
 * 说明：  
 * ---------------------------
 */
public interface GpOpeningReportLiteratureService extends CurdService<GpOpeningReportLiterature> {
	
	
    /**
     * 根据openingReportId 查询数据
     * @param id
     * @return
     */    
    List<GpOpeningReportLiterature> findByOpeningReportIdList(Long openingReportId);
    
	/**
	 * 批量保存
	 */
	void batchOpeningLiterature(List<GpOpeningReportLiterature> literatureList,Long openingReportId);
}

package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpOpeningReportHistory;
import com.jinfang.graduationproject.vo.teacher.guider.HistoryListRespVo;

/**
 * ---------------------------
 * 开题报告历史 (GpOpeningReportHistoryMapper)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-13 14:12:21
 * 说明：  
 * ---------------------------
 */
public interface GpOpeningReportHistoryMapper {

	/**
	 * 添加开题报告历史
	 * @param record
	 * @return
	 */
    int add(GpOpeningReportHistory record);

    /**
     * 删除开题报告历史
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改开题报告历史
     * @param record
     * @return
     */
    int update(GpOpeningReportHistory record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    GpOpeningReportHistory findById(Long id);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<GpOpeningReportHistory> findPage();
    
    /**
     * 根据id同步数据
     * @param record
     * @return
     */    
	void copyInsert(Long id);
	
	/**
	 * 根据开题报告id查询列表
	 * 
	 * @param
	 * @return
	 */
	List<HistoryListRespVo> getByOpeningReportIdList(Long openingReportId);
    
}
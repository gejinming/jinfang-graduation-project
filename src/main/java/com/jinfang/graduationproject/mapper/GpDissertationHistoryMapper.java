package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpDissertationHistory;
import com.jinfang.graduationproject.vo.teacher.guider.dissertation.FindByDissertationIdListRespVo;

/**
 * --------------------------- 毕业论文历史 (GpDissertationHistoryMapper)
 * --------------------------- 作者： lz 时间： 2020-09-13 14:12:21 说明：
 * ---------------------------
 */
public interface GpDissertationHistoryMapper {

	/**
	 * 添加毕业论文历史
	 * 
	 * @param record
	 * @return
	 */
	int add(GpDissertationHistory record);

	/**
	 * 删除毕业论文历史
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 修改毕业论文历史
	 * 
	 * @param record
	 * @return
	 */
	int update(GpDissertationHistory record);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	GpDissertationHistory findById(Long id);

	/**
	 * 基础分页查询
	 * 
	 * @param record
	 * @return
	 */
	List<GpDissertationHistory> findPage();

	/**
	 * 根据id同步数据
	 * 
	 * @param record
	 * @return
	 */
	void copyInsert(Long id);

	/**
	 * dissertationId查询集合
	 * 
	 * @param
	 * @return
	 */
	List<FindByDissertationIdListRespVo> findByDissertationIdList(Long dissertationId);
}
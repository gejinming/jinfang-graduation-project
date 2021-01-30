package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.domain.GpMissionPlan;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpMissionPlanMapper;
import com.jinfang.graduationproject.service.GpMissionPlanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * ---------------------------
 * 任务计划（一对多） (GpMissionPlanServiceImpl)         
 * ---------------------------
 * 作者： lz
 * 时间：  2020-08-14 23:07:27
 * 说明：  
 * ---------------------------
 */
@Service
@AllArgsConstructor
public class GpMissionPlanServiceImpl implements GpMissionPlanService {

	private GpMissionPlanMapper gpMissionPlanMapper;

	@Override
	public int save(GpMissionPlan record) {
		if(record.getId() == null || record.getId() == 0) {
			record.setCreateDate(new Date());
			return gpMissionPlanMapper.add(record);
		}
		record.setModifyDate(new Date());
		return gpMissionPlanMapper.update(record);
	}

	@Override
	public int delete(GpMissionPlan record) {
		return gpMissionPlanMapper.delete(record.getId());
	}

	@Override
	public int delete(List<GpMissionPlan> records) {
		for(GpMissionPlan record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public GpMissionPlan findById(Long id) {
		return gpMissionPlanMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, gpMissionPlanMapper);
	}

	@Override
	public void batchMissionPlan(List<GpMissionPlan> planList,Long messionId) {
		if(planList !=null && planList.size()>0){
			for(GpMissionPlan plan : planList){
				plan.setId(0L);
				plan.setMessionId(messionId);
				save(plan);
			}
		}
	}

	@Override
	public List<GpMissionPlan> findByMessionId(Long messionId) {
		return gpMissionPlanMapper.findByMessionId(messionId);
	}

	@Override
	public void deleteByMessionId(Long messionId) {
		gpMissionPlanMapper.deleteByMessionId(messionId);
	}
	
}

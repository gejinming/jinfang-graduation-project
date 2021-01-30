package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.domain.GpMissionLiterature;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpMissionLiteratureMapper;
import com.jinfang.graduationproject.service.GpMissionLiteratureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * ---------------------------
 * 任务文献（一对多） (GpMissionLiteratureServiceImpl)         
 * ---------------------------
 * 作者： lz
 * 时间：  2020-08-14 23:07:27
 * 说明：  
 * ---------------------------
 */
@Service
@AllArgsConstructor
public class GpMissionLiteratureServiceImpl implements GpMissionLiteratureService {

	private GpMissionLiteratureMapper gpMissionLiteratureMapper;

	@Override
	public int save(GpMissionLiterature record) {
		if(record.getId() == null || record.getId() == 0) {
			record.setCreateDate(new Date());
			return gpMissionLiteratureMapper.add(record);
		}
		record.setModifyDate(new Date());
		return gpMissionLiteratureMapper.update(record);
	}

	@Override
	public int delete(GpMissionLiterature record) {
		return gpMissionLiteratureMapper.delete(record.getId());
	}

	@Override
	public int delete(List<GpMissionLiterature> records) {
		for(GpMissionLiterature record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public GpMissionLiterature findById(Long id) {
		return gpMissionLiteratureMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, gpMissionLiteratureMapper);
	}

	@Override
	public void batchMissionLiterature(List<GpMissionLiterature> literatureList, Long messionId) {
		if(literatureList !=null && literatureList.size()>0){
			for(GpMissionLiterature record : literatureList){
				record.setId(0L);
				record.setMissionId(messionId);
				save(record);
			}
		}
		
	}

	@Override
	public List<GpMissionLiterature> findByMessionId(Long messionId) {
		return gpMissionLiteratureMapper.findByMessionId(messionId);
	}

	@Override
	public void deleteByMessionId(Long messionId) {
		gpMissionLiteratureMapper.deleteByMessionId(messionId);
	}
	
}

package com.jinfang.graduationproject.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jinfang.graduationproject.domain.GpOpeningReportLiterature;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpOpeningReportLiteratureMapper;
import com.jinfang.graduationproject.service.GpOpeningReportLiteratureService;

import lombok.AllArgsConstructor;

/**
 * ---------------------------
 * 开题报告文献关系表（一对多） (GpOpeningReportLiteratureServiceImpl)         
 * ---------------------------
 * 作者： lz
 * 时间：  2020-08-14 23:07:27
 * 说明：  
 * ---------------------------
 */
@Service
@AllArgsConstructor
public class GpOpeningReportLiteratureServiceImpl implements GpOpeningReportLiteratureService {

	private GpOpeningReportLiteratureMapper gpOpeningReportLiteratureMapper;

	@Override
	public int save(GpOpeningReportLiterature record) {
		if(record.getId() == null || record.getId() == 0) {
			record.setCreateDate(new Date());
			return gpOpeningReportLiteratureMapper.add(record);
		}
		record.setModifyDate(new Date());
		return gpOpeningReportLiteratureMapper.update(record);
	}

	@Override
	public int delete(GpOpeningReportLiterature record) {
		return gpOpeningReportLiteratureMapper.delete(record.getId());
	}

	@Override
	public int delete(List<GpOpeningReportLiterature> records) {
		for(GpOpeningReportLiterature record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public GpOpeningReportLiterature findById(Long id) {
		return gpOpeningReportLiteratureMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, gpOpeningReportLiteratureMapper);
	}

	@Override
	public List<GpOpeningReportLiterature> findByOpeningReportIdList(Long openingReportId) {
		return gpOpeningReportLiteratureMapper.findByOpeningReportIdList(openingReportId);
	}

	@Override
	public void batchOpeningLiterature(List<GpOpeningReportLiterature> literatureList, Long openingReportId) {
		if(literatureList !=null && literatureList.size()>0){
			for(GpOpeningReportLiterature literature : literatureList){
				literature.setOpeningReportId(openingReportId);
				save(literature);
			}
		}
		
	}
	
}

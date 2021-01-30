package com.jinfang.graduationproject.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jinfang.graduationproject.domain.GpReviewLiterature;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpReviewLiteratureMapper;
import com.jinfang.graduationproject.service.GpReviewLiteratureService;

import lombok.AllArgsConstructor;

/**
 * ---------------------------
 * 文献综述文献关系表（一对多） (GpReviewLiteratureServiceImpl)         
 * ---------------------------
 * 作者： lz
 * 时间：  2020-08-14 23:07:27
 * 说明：  
 * ---------------------------
 */
@Service
@AllArgsConstructor
public class GpReviewLiteratureServiceImpl implements GpReviewLiteratureService {

	private GpReviewLiteratureMapper gpReviewLiteratureMapper;

	@Override
	public int save(GpReviewLiterature record) {
		if(record.getId() == null || record.getId() == 0) {
			record.setCreateDate(new Date());
			return gpReviewLiteratureMapper.add(record);
		}
		record.setModifyDate(new Date());
		return gpReviewLiteratureMapper.update(record);
	}

	@Override
	public int delete(GpReviewLiterature record) {
		return gpReviewLiteratureMapper.delete(record.getId());
	}

	@Override
	public int delete(List<GpReviewLiterature> records) {
		for(GpReviewLiterature record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public GpReviewLiterature findById(Long id) {
		return gpReviewLiteratureMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, gpReviewLiteratureMapper);
	}

	@Override
	public List<GpReviewLiterature> findByReviewIdList(Long reviewId) {
		return gpReviewLiteratureMapper.findByReviewIdList(reviewId);
	}

	@Override
	public void batchReviewLiterature(List<GpReviewLiterature> literatureList, Long reviewId) {
		if(literatureList !=null && literatureList.size()>0){
			for(GpReviewLiterature literature : literatureList){
				literature.setReviewId(reviewId);
				save(literature);
			}
		}
		
	}
	
}

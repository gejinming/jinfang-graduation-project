package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.domain.GpDocMeta;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpDocMetaMapper;
import com.jinfang.graduationproject.service.DocMetaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ---------------------------
 * 文件表 (DocMetaServiceImpl)         
 * ---------------------------
 * 作者： lz
 * 时间：  2020-08-14 23:07:27
 * 说明：  
 * ---------------------------
 */
@Service
@AllArgsConstructor
public class DocMetaServiceImpl implements DocMetaService {

	private GpDocMetaMapper docMetaMapper;

	@Override
	public int save(GpDocMeta record) {
		if(record.getId() == null || record.getId() == 0) {
			return docMetaMapper.add(record);
		}
		return docMetaMapper.update(record);
	}

	@Override
	public int delete(GpDocMeta record) {
		return docMetaMapper.delete(record.getId());
	}

	@Override
	public int delete(List<GpDocMeta> records) {
		for(GpDocMeta record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public GpDocMeta findById(Long id) {
		return docMetaMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, docMetaMapper);
	}
	
}

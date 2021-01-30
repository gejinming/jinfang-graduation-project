package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.domain.GpSubjectLiterature;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpSubjectLiteratureMapper;
import com.jinfang.graduationproject.service.GpSubjectLiteratureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * ---------------------------
 * 课题文献关系一对多） (GpSubjectLiteratureServiceImpl)
 * ---------------------------
 * 作者： lz
 * 时间：  2020-08-14 23:07:27
 * 说明：
 * ---------------------------
 */
@Slf4j
@Service
@AllArgsConstructor
public class GpSubjectLiteratureServiceImpl implements GpSubjectLiteratureService {

    private GpSubjectLiteratureMapper gpSubjectLiteratureMapper;

    @Override
    public int save(GpSubjectLiterature record) {
        if (record.getId() == null || record.getId() == 0) {
            return gpSubjectLiteratureMapper.add(record);
        }
        return gpSubjectLiteratureMapper.update(record);
    }

    @Override
    public int delete(GpSubjectLiterature record) {
        return gpSubjectLiteratureMapper.delete(record.getId());
    }

    @Override
    public int delete(List<GpSubjectLiterature> records) {
        for (GpSubjectLiterature record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public GpSubjectLiterature findById(Long id) {
        return gpSubjectLiteratureMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, gpSubjectLiteratureMapper);
    }

    @Override
    public List<GpSubjectLiterature> getBySubjectIdList(Long subjectId) {
        return gpSubjectLiteratureMapper.getBySubjectIdList(subjectId);
    }

    @Override
    public int batchSave(List<GpSubjectLiterature> list, Long subjectId) {
        if (CollectionUtils.isEmpty(list)) {
            log.warn("Empty literature list by subjectID[{}]", subjectId);
            return 0;
        }
        for (GpSubjectLiterature subjectLiterature : list) {
            subjectLiterature.setSubjectId(subjectId);
            subjectLiterature.setCreateDate(new Date());
        }

        return gpSubjectLiteratureMapper.batchInsert(list);
    }

    @Override
    public int deleteBySubjectId(Long subjectId) {
        return gpSubjectLiteratureMapper.deleteBySubjectId(subjectId);
    }

}

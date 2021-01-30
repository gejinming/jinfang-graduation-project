package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.domain.GpInspection;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpInspectionMapper;
import com.jinfang.graduationproject.service.GpInspectionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ---------------------------
 * 中期检查表 (GpInspectionServiceImpl)
 * ---------------------------
 * 作者： lz
 * 时间：  2020-09-09 13:46:25
 * 说明：
 * ---------------------------
 */
@Slf4j
@Service
@AllArgsConstructor
public class GpInspectionServiceImpl implements GpInspectionService {

    private GpInspectionMapper gpInspectionMapper;

    @Override
    public int save(GpInspection record) {
        if (record.getId() == null || record.getId() == 0) {
            return gpInspectionMapper.add(record);
        }
        return gpInspectionMapper.update(record);
    }

    @Override
    public int delete(GpInspection record) {
        return gpInspectionMapper.delete(record.getId());
    }

    @Override
    public int delete(List<GpInspection> records) {
        for (GpInspection record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public GpInspection findById(Long id) {
        return gpInspectionMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, gpInspectionMapper, "findPage",
                (Long) pageRequest.getExtProps().get("schoolId"),
                pageRequest.getAttrValue("grade"),
                pageRequest.getAttrValue("subjectName"),
                pageRequest.getAttrValue("status"),
                (Long) pageRequest.getExtProps().get("teacherId"));
    }

    @Override
    public GpInspection findBySubjectStudentId(Long subjectStudentId) {
        return gpInspectionMapper.selectBySubjectStudentId(subjectStudentId);
    }
}

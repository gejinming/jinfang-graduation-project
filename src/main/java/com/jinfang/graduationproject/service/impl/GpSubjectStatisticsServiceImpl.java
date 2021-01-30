package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.constants.teacher.SubjectConstants;
import com.jinfang.graduationproject.domain.GpSubjectStatistics;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpSubjectLiteratureMapper;
import com.jinfang.graduationproject.mapper.GpSubjectStatisticsMapper;
import com.jinfang.graduationproject.mapper.GpSubjectStudentMapper;
import com.jinfang.graduationproject.service.GpSubjectStatisticsService;
import com.jinfang.graduationproject.util.ParameterUtil;
import com.jinfang.graduationproject.vo.student.topic.FindStudenttPageRespVo;
import com.jinfang.graduationproject.vo.teacher.topic.FindPageByNameRespVo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * --------------------------- 学生选题统计表（更新） (GpSubjectStatisticsServiceImpl)
 * --------------------------- 作者： lz 时间： 2020-08-14 23:07:27 说明：
 * ---------------------------
 */
@Service
@AllArgsConstructor
public class GpSubjectStatisticsServiceImpl implements GpSubjectStatisticsService {

    private GpSubjectStatisticsMapper gpSubjectStatisticsMapper;
    private GpSubjectLiteratureMapper subjectLiteratureMapper;
    private GpSubjectStudentMapper gpSubjectStudentMapper;

    @Override
    public int save(GpSubjectStatistics record) {
        if (record.getId() == null || record.getId() == 0) {
            return gpSubjectStatisticsMapper.add(record);
        }
        return gpSubjectStatisticsMapper.update(record);
    }

    @Override
    public int delete(GpSubjectStatistics record) {
        return gpSubjectStatisticsMapper.delete(record.getId());
    }

    @Override
    public int delete(List<GpSubjectStatistics> records) {
        for (GpSubjectStatistics record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public GpSubjectStatistics findById(Long id) {
        return gpSubjectStatisticsMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult page = MybatisPageHelper.findPage(pageRequest, gpSubjectStatisticsMapper,
                "findPageByName",
                ParameterUtil.getColumnFilterValue(pageRequest, "grade"),
                ParameterUtil.getColumnFilterValue(pageRequest, "subjectId"));

        // 根据课题id获取已接收学生列表
        if (page != null && page.getContent().size() > 0) {
            for (Object data : page.getContent()) {
                FindPageByNameRespVo findPageByNameRespVo = (FindPageByNameRespVo) data;

                List<String> studentNames = gpSubjectStudentMapper.findReceiveStudentNames(findPageByNameRespVo.getSubjectId());
                if (!CollectionUtils.isEmpty(studentNames)) {
                    findPageByNameRespVo.setStudentNames(StringUtils.join(studentNames, "，"));
                }
            }

        }
        return page;
    }

    @Override
    public GpSubjectStatistics findBySubjectId(Long subjectId) {
        return gpSubjectStatisticsMapper.findBySubjectId(subjectId);
    }

}

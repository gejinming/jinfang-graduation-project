package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.domain.GpScoreRole;
import com.jinfang.graduationproject.domain.GpSetting;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpScoreRoleMapper;
import com.jinfang.graduationproject.mapper.GpSettingMapper;
import com.jinfang.graduationproject.service.ScoreRoleService;
import com.jinfang.graduationproject.service.SettingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ScoreRoleServiceImpl implements ScoreRoleService {

    private GpScoreRoleMapper gpScoreRoleMapper;

    @Override
    public int save(GpScoreRole record) {
        // 添加前先清空原有记录
        gpScoreRoleMapper.deleteBygGradeComposeId(record);

        return gpScoreRoleMapper.add(record);
    }

    @Override
    public int delete(GpScoreRole record) {
        return 0;
    }

    @Override
    public int delete(List<GpScoreRole> records) {
        return 0;
    }

    @Override
    public GpScoreRole findById(Long id) {
        return gpScoreRoleMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, gpScoreRoleMapper, "findPage",
                pageRequest.getAttrValue("grade"), pageRequest.getExtProps().get("schoolId"));
    }


}

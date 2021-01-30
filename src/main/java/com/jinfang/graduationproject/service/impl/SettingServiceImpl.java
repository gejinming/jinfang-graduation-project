package com.jinfang.graduationproject.service.impl;

import java.util.Date;
import java.util.List;

import com.jinfang.graduationproject.util.DateUtil;
import org.springframework.stereotype.Service;

import com.jinfang.graduationproject.constants.teacher.SettingConstants;
import com.jinfang.graduationproject.domain.GpSetting;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpSettingMapper;
import com.jinfang.graduationproject.service.SettingService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class SettingServiceImpl implements SettingService {

    private GpSettingMapper gpSettingMapper;

    @Override
    public HttpResult add(GpSetting setting) {
        if (setting.getId() != null) {
            setting.setStatus(SettingConstants.Status.YES.getCode());
            setting.setModifyDate(new Date());
            return HttpResult.by(gpSettingMapper.update(setting) > 0);
        }
        return HttpResult.by(gpSettingMapper.add(setting) > 0);
    }

    @Override
    public int save(GpSetting record) {
        return gpSettingMapper.add(record);
    }

    @Override
    public int delete(GpSetting record) {
        return 0;
    }

    @Override
    public int delete(List<GpSetting> records) {
        return 0;
    }

    @Override
    public GpSetting findById(Long id) {
        return gpSettingMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, gpSettingMapper, "findPage",
                pageRequest.getExtProps().get("schoolId"));
    }

    @Override
    public GpSetting findBySchoolIdAndGrade(Long schoolId, String grade) {
        return gpSettingMapper.selectByGradeAndSchoolId(grade, schoolId);
    }

    @Override
    public HttpResult init(Long schoolId, String operator) {
        String grade = DateUtil.getCurrentYear() + "";

        GpSetting gpSetting = findBySchoolIdAndGrade(schoolId, grade);
        if (gpSetting != null) {
            return HttpResult.error("届别[" + grade + "]设置记录已存在，无需再次初始化");
        }

        gpSetting = new GpSetting();
        gpSetting.setGrade(DateUtil.getCurrentYear() + "");
        gpSetting.setOperateUser(operator);
        gpSetting.setSchoolId(schoolId);

        return add(gpSetting);

    }
}

package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.GpSetting;
import com.jinfang.graduationproject.dto.http.HttpResult;

public interface SettingService extends CurdService<GpSetting> {

    /**
     * 新增设置
     *
     * @param schoolId 学校ID
     * @param operator 操作人
     * @return 处理结果
     */
    HttpResult init(Long schoolId, String operator);

    /**
     * 新增设置
     *
     * @param setting 设置
     * @return 处理结果
     */
    HttpResult add(GpSetting setting);

    /**
     * 根据学校ID和届别获取参数
     *
     * @param schoolId 学校ID
     * @param grade    届别
     */
    GpSetting findBySchoolIdAndGrade(Long schoolId, String grade);

}

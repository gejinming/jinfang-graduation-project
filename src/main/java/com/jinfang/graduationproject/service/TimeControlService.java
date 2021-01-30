package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.GpTimeControl;
import com.jinfang.graduationproject.dto.http.HttpResult;

public interface TimeControlService extends CurdService<GpTimeControl> {

    /**
     * 开启
     *
     * @param id 记录ID
     * @return 处理结果
     */
    HttpResult open(Long id, String operateUser);

    /**
     * 关闭
     *
     * @param id 记录ID
     * @return 处理结果
     */
    HttpResult close(Long id, String operateUser);

    /**
     * 新增设置
     *
     * @param schoolId 学校ID
     * @param operator 操作人
     * @return 处理结果
     */
    HttpResult init(Long schoolId, String operator);

}

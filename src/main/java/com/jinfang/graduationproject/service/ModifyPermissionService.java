package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.constants.ModifyStatus;
import com.jinfang.graduationproject.domain.GpModifyPermission;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.vo.LoginUserMeta;

public interface ModifyPermissionService extends CurdService<GpModifyPermission> {

    /**
     * 开放/关闭
     *
     * @param grade 届别
     * @return 处理结果
     */
    HttpResult operate(String grade, ModifyStatus modifyStatus, LoginUserMeta loginUserMeta);

    /**
     * 是否允许修改
     * @param grade 届别
     * @param schoolId 学校I
     * @return true/false
     */
    boolean isAllowModify(String grade, Long schoolId);

}
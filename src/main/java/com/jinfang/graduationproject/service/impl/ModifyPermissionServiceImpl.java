package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.constants.ModifyStatus;
import com.jinfang.graduationproject.domain.GpModifyPermission;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpModifyPermissionMapper;
import com.jinfang.graduationproject.service.ModifyPermissionService;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ModifyPermissionServiceImpl implements ModifyPermissionService {

    private GpModifyPermissionMapper gpModifyPermissionMapper;

    @Override
    public HttpResult operate(String grade, ModifyStatus modifyStatus, LoginUserMeta loginUserMeta) {
        GpModifyPermission gpModifyPermission = gpModifyPermissionMapper.findByGradeAndSchoolId(grade, loginUserMeta.getSchoolId());

        boolean isModify = true;
        if (gpModifyPermission == null) {
            gpModifyPermission = new GpModifyPermission();
            isModify = false;
        }
        gpModifyPermission.setOperateTime(new Date());
        gpModifyPermission.setOperateUser(loginUserMeta.getTeacherIdText());
        gpModifyPermission.setStatus(modifyStatus.getCode());
        if (isModify) {
            gpModifyPermission.setModifyDate(new Date());
            return HttpResult.by(gpModifyPermissionMapper.update(gpModifyPermission) > 0);
        }
        gpModifyPermission.setSchoolId(loginUserMeta.getSchoolId());
        gpModifyPermission.setGrade(grade);
        return HttpResult.by(gpModifyPermissionMapper.add(gpModifyPermission) > 0);
    }

    @Override
    public int save(GpModifyPermission record) {
        return 0;
    }

    @Override
    public int delete(GpModifyPermission record) {
        return 0;
    }

    @Override
    public int delete(List<GpModifyPermission> records) {
        return 0;
    }

    @Override
    public GpModifyPermission findById(Long id) {
        return gpModifyPermissionMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, gpModifyPermissionMapper, "findPage",
                pageRequest.getAttrValue("grade"), pageRequest.getExtProps().get("schoolId"));
    }

    @Override
    public boolean isAllowModify(String grade, Long schoolId) {
        GpModifyPermission gpModifyPermission = gpModifyPermissionMapper.findByGradeAndSchoolId(grade, schoolId);
        if (gpModifyPermission == null) {
            return false;
        }

        if (gpModifyPermission.getStatus() == null || gpModifyPermission.getStatus() == ModifyStatus.CLOSE.getCode()) {
            return false;
        }

        return gpModifyPermission.getStatus() == ModifyStatus.OPEN.getCode();
    }
}

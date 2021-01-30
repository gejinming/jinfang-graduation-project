package com.jinfang.graduationproject.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jinfang.graduationproject.constants.MenuOpenStatus;
import com.jinfang.graduationproject.service.SysMenuService;
import com.jinfang.graduationproject.util.DateUtil;
import org.springframework.stereotype.Service;

import com.jinfang.graduationproject.constants.teacher.TimeControlConstants;
import com.jinfang.graduationproject.domain.GpTimeControl;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpTimeControlMapper;
import com.jinfang.graduationproject.service.TimeControlService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
@AllArgsConstructor
public class TimeControlServiceImpl implements TimeControlService {

    private GpTimeControlMapper gpTimeControlMapper;
    private SysMenuService sysMenuService;

    @Override
    public int save(GpTimeControl record) {
        return 0;
    }

    @Override
    public int delete(GpTimeControl record) {
        return 0;
    }

    @Override
    public int delete(List<GpTimeControl> records) {
        return 0;
    }

    @Override
    public GpTimeControl findById(Long id) {
        return gpTimeControlMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, gpTimeControlMapper, "findPage",
                pageRequest.getAttrValue("grade"), pageRequest.getExtProps().get("schoolId"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult open(Long id, String operateUser) {
        GpTimeControl gpTimeControl = gpTimeControlMapper.findById(id);
        if (gpTimeControl == null) {
            return HttpResult.error("未找到相关记录");
        }
        gpTimeControl.setStatus(TimeControlConstants.Status.OPEN.getCode());
        gpTimeControl.setOpenTime(new Date());
        gpTimeControl.setOpenUser(operateUser);

        int rows = gpTimeControlMapper.update(gpTimeControl);

        if (rows > 0) {
            int count = sysMenuService.updateByNodeCode(MenuOpenStatus.OPEN.getCode(), gpTimeControl.getSchoolId(),
                    gpTimeControl.getNodeCode());
            log.info("Menu and permissions update to open -> [{}]", count);
        }

        return HttpResult.by(rows > 0);
    }

    @Override
    public HttpResult close(Long id, String operateUser) {
        GpTimeControl gpTimeControl = gpTimeControlMapper.findById(id);
        if (gpTimeControl == null) {
            return HttpResult.error("未找到相关记录");
        }
        gpTimeControl.setStatus(TimeControlConstants.Status.CLOSE.getCode());
        gpTimeControl.setCloseTime(new Date());
        gpTimeControl.setOpenUser(operateUser);

        int rows = gpTimeControlMapper.update(gpTimeControl);
        if (rows > 0) {
            int count = sysMenuService.updateByNodeCode(MenuOpenStatus.FROZE.getCode(), gpTimeControl.getSchoolId(),
                    gpTimeControl.getNodeCode());
            log.info("Menu and permissions update to freeze -> [{}]", count);
        }

        return HttpResult.by(rows > 0);
    }

    @Override
    public HttpResult init(Long schoolId, String operator) {

        String grade = DateUtil.getCurrentYear() + "";

        List<GpTimeControl> list = gpTimeControlMapper.findPage(grade, schoolId);
        if (!CollectionUtils.isEmpty(list)) {
            return HttpResult.error("届别[" + grade + "]设置记录已存在，无需再次初始化");
        }

        list = new ArrayList<>();
        for (TimeControlConstants.NodeTemplate nodeTemplate : TimeControlConstants.NodeTemplate.values()) {
            GpTimeControl gpTimeControl = new GpTimeControl();
            gpTimeControl.setGrade(grade);
            gpTimeControl.setSchoolId(schoolId);
            gpTimeControl.setNodeName(nodeTemplate.getTitle());
            gpTimeControl.setNodeCode(nodeTemplate.getCode());
            gpTimeControl.setContent(nodeTemplate.getContent());
            gpTimeControl.setSort(nodeTemplate.getSort());

            list.add(gpTimeControl);
        }

        int rows = gpTimeControlMapper.batchInsert(list);
        if (rows == TimeControlConstants.NodeTemplate.values().length) {
            return HttpResult.ok("初始化成功");
        }

        return HttpResult.error("初始化失败");
    }
}

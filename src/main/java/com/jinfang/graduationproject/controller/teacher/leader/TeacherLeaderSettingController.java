package com.jinfang.graduationproject.controller.teacher.leader;


import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.domain.GpSetting;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.SettingService;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher/leader/setting")
@AllArgsConstructor
@Api(tags = "参数设置接口", description = "专业负责人")
public class TeacherLeaderSettingController extends BaseController {

    private SettingService settingService;

//    @PreAuthorize("hasAuthority('teacher:leader:setting:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        LoginUserMeta userMeta = getLoginUser();

        addSchoolId(pageRequest, userMeta);

        return HttpResult.ok(settingService.findPage(pageRequest));
    }

//    @PreAuthorize("hasAuthority('teacher:leader:setting:add')")
    @ApiOperation("设置")
    @PostMapping(value = "/add")
    public HttpResult add(@RequestBody GpSetting setting) {
        setting.setOperateUser(getLoginUser().getTeacherIdText());
        return settingService.add(setting);
    }

//    @PreAuthorize("hasAuthority('teacher:leader:setting:init')")
    @ApiOperation("一键初始化")
    @PostMapping(value = "/init")
    public HttpResult init() {
        LoginUserMeta userMeta = getLoginUser();
        return settingService.init(userMeta.getSchoolId(), userMeta.getTeacherIdText());
    }

}

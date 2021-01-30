package com.jinfang.graduationproject.controller.teacher.leader;


import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.TimeControlService;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher/leader/timeControl")
@AllArgsConstructor
@Api(tags = "时间控制接口", description = "专业负责人")
public class TeacherLeaderTimeControlController extends BaseController {

    private TimeControlService timeControlService;

//    @PreAuthorize("hasAuthority('teacher:leader:timeControl:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        addSchoolId(pageRequest, getLoginUser());

        return HttpResult.ok(timeControlService.findPage(pageRequest));
    }

//    @PreAuthorize("hasAuthority('teacher:leader:timeControl:open')")
    @ApiOperation("开启")
    @PostMapping(value = "/open/{id}")
    public HttpResult open(@PathVariable("id") Long id) {
        return HttpResult.ok(timeControlService.open(id, getLoginUser().getTeacherIdText()));
    }

    /**
     * 关闭
     */
//    @PreAuthorize("hasAuthority('teacher:leader:timeControl:close')")
    @ApiOperation("关闭")
    @PostMapping(value = "/close/{id}")
    public HttpResult close(@PathVariable("id") Long id) {
        return HttpResult.ok(timeControlService.close(id, getLoginUser().getTeacherIdText()));
    }

//    @PreAuthorize("hasAuthority('teacher:leader:timeControl:init')")
    @ApiOperation("一键初始化")
    @PostMapping(value = "/init")
    public HttpResult init() {
        LoginUserMeta userMeta = getLoginUser();
        return timeControlService.init(userMeta.getSchoolId(), userMeta.getTeacherIdText());
    }


}

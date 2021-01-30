package com.jinfang.graduationproject.controller.teacher.leader;


import com.jinfang.graduationproject.constants.ModifyStatus;
import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.ModifyPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher/leader/modifyPermission")
@AllArgsConstructor
@Api(tags = "开题权限修改接口", description = "专业负责人")
public class TeacherLeaderModifyPermissionController extends BaseController {

    private ModifyPermissionService modifyPermissionService;

    /**
     * 基础分页查询
     */
//    @PreAuthorize("hasAuthority('teacher:leader:modifyPermission:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        addSchoolId(pageRequest, getLoginUser());

        return HttpResult.ok(modifyPermissionService.findPage(pageRequest));
    }

//    @PreAuthorize("hasAuthority('teacher:leader:modifyPermission:open')")
    @ApiOperation("开启")
    @GetMapping(value = "/open")
    public HttpResult open(@RequestParam("grade") String grade) {
        return modifyPermissionService.operate(grade, ModifyStatus.OPEN, getLoginUser());
    }

//    @PreAuthorize("hasAuthority('teacher:leader:modifyPermission:close')")
    @ApiOperation("关闭")
    @GetMapping(value = "/close")
    public HttpResult close(@RequestParam("grade") String grade) {
        return modifyPermissionService.operate(grade, ModifyStatus.CLOSE, getLoginUser());
    }


}

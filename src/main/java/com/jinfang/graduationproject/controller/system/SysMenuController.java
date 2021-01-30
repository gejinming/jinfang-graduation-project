package com.jinfang.graduationproject.controller.system;

import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.domain.SysMenu;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单控制器
 */
@RestController
@RequestMapping("menu")
@AllArgsConstructor
@Api(tags = "系统菜单接口")
public class SysMenuController extends BaseController {

    private SysMenuService sysMenuService;

    //    @PreAuthorize("hasAuthority('sys:menu:add') AND hasAuthority('sys:menu:edit')")
    @ApiOperation(value = "新增", hidden = true)
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody SysMenu record) {
        return HttpResult.ok(sysMenuService.save(record));
    }

    //    @PreAuthorize("hasAuthority('sys:menu:delete')")
    @ApiOperation(value = "删除", hidden = true)
    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<SysMenu> records) {
        return HttpResult.ok(sysMenuService.delete(records));
    }

    //    @PreAuthorize("hasAuthority('sys:menu:findNavTree')")
    @ApiOperation(value = "根据当前登录人角色获取子菜单")
    @GetMapping(value = "/findNavTree")
    public HttpResult findNavTree() {
        return HttpResult.ok(sysMenuService.findTree(getLoginUser().getRoles(), 1,
                getLoginUser().getSchoolId()));
    }

    @ApiOperation(value = "根据当前登录人角色获取菜单")
//    @PreAuthorize("hasAuthority('sys:menu:findMenuTree')")
    @GetMapping(value = "/findMenuTree")
    public HttpResult findMenuTree() {
        return HttpResult.ok(sysMenuService.findTree(getLoginUser().getRoles(), 0,
                getLoginUser().getSchoolId()));
    }

    //    @PreAuthorize("hasAuthority('sys:menu:findPermissions')")
    @ApiOperation(value = "根据当前登录人角色获取权限")
    @GetMapping(value = "/findPermissions")
    public HttpResult findPermissions() {
    	return HttpResult.ok(sysMenuService.findMenusByRoleName(getLoginUser().getRoles(),
                getLoginUser().getSchoolId()));
       /* return HttpResult.ok(sysMenuService.findPermsByRoleName(getLoginUser().getRoles(),
                getLoginUser().getSchoolId()));*/
    }


}

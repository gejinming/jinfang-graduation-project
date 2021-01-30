package com.jinfang.graduationproject.controller.teacher.leader;

import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.domain.GpScoreRole;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.ScoreRoleService;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设置评分控制层 （专业负责人）
 */
@RestController
@RequestMapping("/teacher/leader/scoreRole")
@Api(tags = "设置评分接口", description = "专业负责人")
@AllArgsConstructor
public class TeacherLeaderScoreRoleController extends BaseController {

    private ScoreRoleService scoreRoleService;

    //	@PreAuthorize("hasAuthority('teacher:leader:scoreRole:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        LoginUserMeta userMeta = getLoginUser();

        addSchoolId(pageRequest, userMeta);

        return HttpResult.ok(scoreRoleService.findPage(pageRequest));
    }

    //	@PreAuthorize("hasAuthority('teacher:leader:scoreRole:set')")
    @ApiOperation("设置")
    @PostMapping(value = "/set")
    public HttpResult set(@RequestBody GpScoreRole scoreRole) {
        scoreRole.setOperateUser(getLoginUser().getTeacherIdText());

        scoreRole.setSchoolId(getLoginUser().getSchoolId());

        return HttpResult.ok(scoreRoleService.save(scoreRole) > 0);
    }

}

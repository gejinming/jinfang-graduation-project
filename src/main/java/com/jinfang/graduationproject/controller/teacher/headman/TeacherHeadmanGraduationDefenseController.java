package com.jinfang.graduationproject.controller.teacher.headman;


import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.GraduationDefenseService;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import com.jinfang.graduationproject.vo.defense.GraduationDefenseScoreReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 毕业答辩控制层 （答辩组组长）
 */
@RestController
@RequestMapping("/teacher/headman/graduationDefense")
@AllArgsConstructor
@Api(tags = "毕业答辩接口", description = "答辩组组长")
public class TeacherHeadmanGraduationDefenseController extends BaseController {

    private GraduationDefenseService graduationDefenseService;

//    @PreAuthorize("hasAuthority('teacher:headman:graduationDefense:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        LoginUserMeta userMeta = getLoginUser();

        addSchoolId(pageRequest, userMeta);

        addGrade(pageRequest, userMeta);

        return HttpResult.ok(graduationDefenseService.findPage(pageRequest));
    }

//    @PreAuthorize("hasAuthority('teacher:headman:graduationDefense:getDetail')")
    @ApiOperation("根据课题ID获取毕业答辩信息（编辑/查看弹窗使用）")
    @GetMapping(value = "/getDetail")
    public HttpResult getDetail(@RequestParam("graduationDefenseId") Long graduationDefenseId) {
        return HttpResult.ok(graduationDefenseService.findByGraduationDefenseId(graduationDefenseId));
    }

//    @PreAuthorize("hasAuthority('teacher:headman:graduationDefense:score')")
    @ApiOperation("评分")
    @PostMapping(value = "/score")
    public HttpResult score(@RequestBody GraduationDefenseScoreReq rraduationDefenseScoreReq) {
        return graduationDefenseService.score(rraduationDefenseScoreReq);
    }

}

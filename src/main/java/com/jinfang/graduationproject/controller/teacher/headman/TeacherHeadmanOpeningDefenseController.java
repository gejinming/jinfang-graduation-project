package com.jinfang.graduationproject.controller.teacher.headman;


import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.domain.GpOpeningDefense;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.OpeningDefenseService;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 开题答辩记录控制层 （答辩组组长）
 */
@RestController
@RequestMapping("/teacher/headman/openingDefense")
@AllArgsConstructor
@Api(tags = "开题答辩接口", description = "答辩组组长")
public class TeacherHeadmanOpeningDefenseController extends BaseController {

    private OpeningDefenseService openingDefenseService;

    @ApiOperation("分页查询列表")
//    @PreAuthorize("hasAuthority('teacher:headman:openingDefense:list')")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        LoginUserMeta userMeta = getLoginUser();

        addSchoolId(pageRequest, userMeta);

        addGrade(pageRequest, userMeta);

        return HttpResult.ok(openingDefenseService.findPage(pageRequest));
    }

//    @PreAuthorize("hasAuthority('teacher:headman:openingDefense:getInitialValues')")
    @ApiOperation("根据学生选题ID获取默认回填数据（添加弹窗使用）")
    @GetMapping(value = "/getInitialValues")
    public HttpResult getInputValues(@RequestParam("subjectStudentId") Long subjectStudentId) {
        return HttpResult.ok(openingDefenseService.findBySubjectStudentId(subjectStudentId));
    }

//    @PreAuthorize("hasAuthority('teacher:headman:openingDefense:getDetail')")
    @ApiOperation("根据课题ID获取开题报告评价意见（编辑/查看弹窗使用）")
    @GetMapping(value = "/getDetail")
    public HttpResult getDetail(@RequestParam("openingDefenseId") Long openingDefenseId) {
        return HttpResult.ok(openingDefenseService.findByOpeningDefenseId(openingDefenseId));
    }

//    @PreAuthorize("hasAuthority('teacher:headman:openingDefense:add')")
    @ApiOperation("添加记录（评分信息采用实体的list组装过来）")
    @PostMapping(value = "/add")
    public HttpResult add(@RequestBody GpOpeningDefense gpOpeningDefense) {
        gpOpeningDefense.setOperateUser(getLoginUser().getTeacherIdText());
        try {
            return HttpResult.ok(openingDefenseService.save(gpOpeningDefense) > 0);
        } catch (Exception e) {
            return HttpResult.error("添加失败");
        }
    }

}

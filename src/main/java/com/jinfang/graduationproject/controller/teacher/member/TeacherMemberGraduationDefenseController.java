package com.jinfang.graduationproject.controller.teacher.member;


import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.domain.GpGraduationDefense;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.GraduationDefenseService;
import com.jinfang.graduationproject.service.SettingService;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 毕业答辩控制层 （答辩组教师）
 */
@RestController
@RequestMapping("/teacher/member/graduationDefense")
@AllArgsConstructor
@Api(tags = "毕业答辩接口", description = "答辩组教师")
public class TeacherMemberGraduationDefenseController extends BaseController {

    private GraduationDefenseService graduationDefenseService;
    private SettingService settingService;

//    @PreAuthorize("hasAuthority('teacher:member:graduationDefense:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        LoginUserMeta userMeta = getLoginUser();

        addSchoolId(pageRequest, userMeta);

        addGrade(pageRequest, userMeta);

        return HttpResult.ok(graduationDefenseService.findPage(pageRequest));
    }

//    @PreAuthorize("hasAuthority('teacher:member:graduationDefense:findSetting')")
    @ApiOperation("获取答辩信息（时间和地点）")
    @GetMapping(value = "/findSetting")
    public HttpResult findSetting() {
        return HttpResult.ok(settingService.findBySchoolIdAndGrade(getLoginUser().getSchoolId(),
                getLoginUser().getGrade()));
    }

//    @PreAuthorize("hasAuthority('teacher:headman:graduationDefense:getInitialValues')")
    @ApiOperation("根据学生选题ID获取默认回填数据（添加弹窗使用）")
    @GetMapping(value = "/getInitialValues")
    public HttpResult getInputValues(@RequestParam("subjectStudentId") Long subjectStudentId) {
        return HttpResult.ok(graduationDefenseService.findBySubjectStudentId(subjectStudentId, getLoginUser()));
    }

//    @PreAuthorize("hasAuthority('teacher:headman:graduationDefense:getDetail')")
    @ApiOperation("根据课题ID获取毕业答辩信息（编辑/查看弹窗使用）")
    @GetMapping(value = "/getDetail")
    public HttpResult getDetail(@RequestParam("graduationDefenseId") Long graduationDefenseId) {
        return HttpResult.ok(graduationDefenseService.findByGraduationDefenseId(graduationDefenseId));
    }

//    @PreAuthorize("hasAuthority('teacher:member:graduationDefense:add')")
    @ApiOperation("添加记录（不需要组装评分，评分又组长单独操作）")
    @PostMapping(value = "/add")
    public HttpResult add(@RequestBody GpGraduationDefense gpGraduationDefense) {
        gpGraduationDefense.setRecordUser(getLoginUser().getTeacherIdText());
        try {
            return HttpResult.ok(graduationDefenseService.save(gpGraduationDefense) > 0);
        } catch (Exception e) {
            return HttpResult.error("添加失败");
        }
    }

}

package com.jinfang.graduationproject.controller.teacher.headman;


import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.CheckerTeacherService;
import com.jinfang.graduationproject.vo.teacher.checker.CheckerTeacherDispatchReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "分配审阅教师接口", description = "答辩组组长")
@RestController
@RequestMapping("/teacher/headman/checker")
@AllArgsConstructor
public class TeacherHeadmanCheckerTeacherController extends BaseController {

    private CheckerTeacherService checkerTeacherService;

    //    @PreAuthorize("hasAuthority('teacher:headman:checker:findUndistributedCount')")
    @ApiOperation("获取未分配学生总数量")
    @GetMapping(value = "/findUndistributedCount")
    public HttpResult findUndistributedCount() {
        return HttpResult.ok(checkerTeacherService.findUndistributedCount(getLoginUser().getGrade(),
                getLoginUser().getSchoolId()));
    }

    //    @PreAuthorize("hasAuthority('teacher:headman:checker:findUndistributedList')")
    @ApiOperation("获取未分配学生列表记录")
    @GetMapping(value = "/findUndistributedList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentName", value = "学生姓名（utf8编码）", dataType = "string"),
            @ApiImplicitParam(name = "teacherName", value = "学号", dataType = "string"),
            @ApiImplicitParam(name = "className", value = "班级（utf8编码）", dataType = "string")
    })
    public HttpResult findUndistributedList(@RequestParam(value = "studentName", defaultValue = "") String studentName,
                                            @RequestParam(value = "studentNo", defaultValue = "") String studentNo,
                                            @RequestParam(value = "className", defaultValue = "") String className) {
        return HttpResult.ok(checkerTeacherService.findUndistributedList(getLoginUser().getGrade(),
                getLoginUser().getSchoolId(), studentName, studentNo, className));
    }

    //    @PreAuthorize("hasAuthority('teacher:headman:checker:findDistributedList')")
    @ApiOperation("获取已分配学生列表记录")
    @GetMapping(value = "/findDistributedList")
    public HttpResult findDistributedList(@RequestParam("defenseTeacherId") Long defenseTeacherId) {
        return HttpResult.ok(checkerTeacherService.findDistributedList(defenseTeacherId));
    }

    //    @PreAuthorize("hasAuthority('teacher:headman:checker:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        addSchoolId(pageRequest, getLoginUser());

        return HttpResult.ok(checkerTeacherService.findPage(pageRequest));
    }

    //    @PreAuthorize("hasAuthority('teacher:headman:checker:distribute')")
    @ApiOperation("分配")
    @PostMapping(value = "/distribute")
    public HttpResult distribute(@RequestBody CheckerTeacherDispatchReq checkerTeacherDispatchReq) {
        checkerTeacherDispatchReq.setLoginUserMeta(getLoginUser());

        return checkerTeacherService.distribute(checkerTeacherDispatchReq);
    }

    //    @PreAuthorize("hasAuthority('teacher:headman:checker:remove')")
    @ApiOperation("删除")
    @PostMapping(value = "/remove")
    public HttpResult remove(@RequestBody CheckerTeacherDispatchReq checkerTeacherDispatchReq) {
        checkerTeacherDispatchReq.setLoginUserMeta(getLoginUser());
        return checkerTeacherService.remove(checkerTeacherDispatchReq);
    }

}

package com.jinfang.graduationproject.controller.teacher.leader;

import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.DefenseTeacherService;
import com.jinfang.graduationproject.service.TeacherService;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import com.jinfang.graduationproject.vo.teacher.defense.DefenseTeacherJoinReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher/leader/defenseTeacher")
@AllArgsConstructor
@Api(tags = "答辩组教师接口", description = "专业负责人")
public class TeacherLeaderDefenseTeacherController extends BaseController {

    private TeacherService teacherService;
    private DefenseTeacherService defenseTeacherService;

//    @PreAuthorize("hasAuthority('teacher:leader:defenseTeacher:findTeachers')")
    @ApiOperation("获取教师列表记录")
    @GetMapping(value = "/findTeachers")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "majorName", value = "专业名称", dataType = "string"),
            @ApiImplicitParam(name = "teacherName", value = "教师姓名", dataType = "string")})
    public HttpResult findTeachers(@RequestParam(value = "majorName", defaultValue = "") String majorName,
                                   @RequestParam(value = "teacherName", defaultValue = "") String teacherName) {

        LoginUserMeta userMeta = getLoginUser();

        return HttpResult.ok(teacherService.findList(userMeta.getGrade(), userMeta.getSchoolId(), majorName, teacherName));
    }

//    @PreAuthorize("hasAuthority('teacher:leader:defenseTeacher:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        addSchoolId(pageRequest, getLoginUser());

        return HttpResult.ok(defenseTeacherService.findPage(pageRequest));
    }

//    @PreAuthorize("hasAuthority('teacher:leader:defenseTeacher:join')")
    @ApiOperation("拉入教师")
    @PostMapping(value = "/join")
    public HttpResult join(@RequestBody DefenseTeacherJoinReq defenseTeacherJoinReq) {
        defenseTeacherJoinReq.setOperatorUser(getLoginUser().getTeacherIdText());
        return defenseTeacherService.joinTeacher(defenseTeacherJoinReq);
    }

//    @PreAuthorize("hasAuthority('teacher:leader:defenseTeacher:setHeadman')")
    @ApiOperation("设置为组长")
    @PostMapping(value = "/setHeadman/{id}")
    public HttpResult setHeadman(@PathVariable("id") Long id) {
        return defenseTeacherService.setHeadman(id, getLoginUser().getTeacherIdText());
    }
    
//  @PreAuthorize("hasAuthority('teacher:leader:defenseTeacher:setTeacher')")
	  @ApiOperation("设置答辩组教师")
	  @PostMapping(value = "/setTeacher")
	  public HttpResult setTeacher() {
	      return HttpResult.ok();
	  }


    @ApiOperation("删除")
//    @PreAuthorize("hasAuthority('teacher:leader:defenseTeacher:delete')")
    @PostMapping(value = "/delete/{id}")
    public HttpResult delete(@PathVariable("id") Long id) {
        return defenseTeacherService.delete(id);
    }

}

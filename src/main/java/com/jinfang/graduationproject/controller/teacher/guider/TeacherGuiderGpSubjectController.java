package com.jinfang.graduationproject.controller.teacher.guider;

import com.alibaba.druid.util.StringUtils;
import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.domain.GpSubject;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.GpSubjectService;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import com.jinfang.graduationproject.vo.teacher.topic.GpSubjectSaveResqVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * --------------------------- 课题表 (GpSubjectController)
 * --------------------------- 作者： lz 时间： 2020-08-15 17:01:54 说明：
 * ---------------------------
 */

@Api(tags = "课题接口", description = "指导教师")
@RestController
@RequestMapping("/teacher/guider/subject")
@AllArgsConstructor
public class TeacherGuiderGpSubjectController extends BaseController {

    private GpSubjectService gpSubjectService;

//    @PreAuthorize("hasAuthority('teacher:guider:subject:save')")
    @ApiOperation("提交课题（添加）")
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody GpSubjectSaveResqVo resqVo) {
        resqVo.setTeacherId(String.valueOf(getLoginUser().getTeacherId()));
        return gpSubjectService.saveOrUpdateSubject(resqVo, getLoginUser());
    }

//    @PreAuthorize("hasAuthority('teacher:guider:subject:delete')")
    @ApiOperation("删除")
    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<GpSubject> records) {
        return HttpResult.ok(gpSubjectService.delete(records));
    }
    
//  @PreAuthorize("hasAuthority('teacher:guider:subject:edit')")
	@ApiOperation("修改")
    @PostMapping(value = "/edit")
	public HttpResult edit() {
	    return HttpResult.ok();
	}
	
//  @PreAuthorize("hasAuthority('teacher:guider:subject:seeReason')")
	@ApiOperation("查看原因")
    @PostMapping(value = "/seeReason")
	public HttpResult seeReason() {
	    return HttpResult.ok();
	}
	
//  @PreAuthorize("hasAuthority('teacher:guider:subject:details')")
	@ApiOperation("查看")
    @PostMapping(value = "/details")
	public HttpResult details() {
	    return HttpResult.ok();
	}
    

//    @PreAuthorize("hasAuthority('teacher:guider:subject:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        LoginUserMeta userMeta = getLoginUser();

        addTeacherId(pageRequest, userMeta);

        addSchoolId(pageRequest, userMeta);

        return HttpResult.ok(gpSubjectService.findPage(pageRequest));
    }

//    @PreAuthorize("hasAuthority('teacher:guider:subject:findById')")
    @ApiOperation("根据ID查询课题信息")
    @GetMapping(value = "/findById")
    public HttpResult findById(@RequestParam Long id) {
        return HttpResult.ok(gpSubjectService.findById(id));
    }

//    @PreAuthorize("hasAuthority('teacher:guider:subject:censorship')")
    @ApiOperation("送审")
    @GetMapping(value = "/censorship")
    public HttpResult censorship(@RequestParam Long id) {
        return HttpResult.ok(gpSubjectService.censorship(id));
    }

//    @PreAuthorize("hasAuthority('teacher:guider:subject:applyModify')")
    @ApiOperation("提交申请修改权限")
    @GetMapping(value = "/applyModify")
    public HttpResult applyModify(@RequestParam String id) {
        if (StringUtils.isEmpty(id)) {
            return HttpResult.error("参数为空【id】");
        }
        return gpSubjectService.applyModify(Long.valueOf(id), getLoginUser().getTeacherId());
    }

    //	@GetMapping(value = "/getSubBySubmitUserList")
    public HttpResult getSubBySubmitUserList(@RequestParam String submitUser) {
        return gpSubjectService.getSubBySubmitUserList(String.valueOf(getLoginUser().getTeacherId()));
    }

}

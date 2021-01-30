package com.jinfang.graduationproject.controller.teacher.guider;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.GpMissionHistoryService;
import com.jinfang.graduationproject.service.GpMissionService;
import com.jinfang.graduationproject.vo.teacher.mission.QueryMissionRespVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@Api(tags = "任务书接口", description = "指导教师")
@RestController
@RequestMapping("/teacher/guider/mission")
@AllArgsConstructor
public class TeacherGuiderTeacherMissionController extends BaseController {

    private GpMissionService missionService;
    private GpMissionHistoryService gpMissionHistoryService;

//    @PreAuthorize("hasAuthority('teacher:guider:mission:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        addTeacherId(pageRequest, getLoginUser());
        return HttpResult.ok(missionService.findPage(pageRequest));
    }

//    @PreAuthorize("hasAuthority('teacher:guider:mission:addAndUpdate')")
    @ApiOperation("新增")
    @PostMapping(value = "/addAndUpdate")
    public HttpResult addAndUpdate(@RequestBody QueryMissionRespVo resqVo) {
        resqVo.setSendUser(getLoginUser().getTeacherId().toString());
        return missionService.addAndUpdate(resqVo);
    }
    
//  @PreAuthorize("hasAuthority('teacher:guider:mission:edit')")
  @ApiOperation("修改任务书")
  @PostMapping(value = "/edit")
  public HttpResult edit() {
      return HttpResult.ok();
  }

//    @PreAuthorize("hasAuthority('teacher:guider:mission:issue')")
    @ApiOperation("下发任务")
    @GetMapping(value = "/issue")
    public HttpResult issue(@RequestParam Long id) {
        return missionService.issue(id);
    }
    
//  @PreAuthorize("hasAuthority('teacher:guider:mission:details')")
	  @ApiOperation("查看任务书")
	  @GetMapping(value = "/details")
	  public HttpResult details() {
	      return HttpResult.ok();
	  }

//    @PreAuthorize("hasAuthority('teacher:guider:mission:findByMissionIdList')")
    @ApiOperation("查看历史记录")
    @GetMapping(value = "/findByMissionIdList")
    public HttpResult findByMissionIdList(@RequestParam Long id) {
        if (id == null) {
            return HttpResult.error("参数为空【id】");
        }
        return gpMissionHistoryService.findByMissionIdList(id);
    }

//    @PreAuthorize("hasAuthority('teacher:guider:mission:getHistoryDetail')")
    @ApiOperation("查看历史明细")
    @GetMapping(value = "/getHistoryDetail")
    public HttpResult getHistoryDetail(@RequestParam Long id) {
        if (id == null) {
            return HttpResult.error("参数为空【id】");
        }
        return gpMissionHistoryService.getHistoryDetail(id);
    }

}

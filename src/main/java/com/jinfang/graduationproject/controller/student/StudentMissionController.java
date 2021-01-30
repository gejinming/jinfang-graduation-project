package com.jinfang.graduationproject.controller.student;

import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.GpMissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/mission")
@AllArgsConstructor
@Api(tags = "任务书接口", description = "学生")
public class StudentMissionController extends BaseController {

	private GpMissionService missionService;

//	@PreAuthorize("hasAuthority('student:mission:list')")
	@ApiOperation("分页查询列表")
	@PostMapping(value = "/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		addCondition(pageRequest, "studentNo", getLoginUser().getStudentNo());
		return missionService.getByStudentNoList(pageRequest);
	}

	@ApiOperation("接收任务")
//	@PreAuthorize("hasAuthority('student:mission:receive')")
	@GetMapping(value = "/receive")
	public HttpResult receive(@RequestParam Long id) {
		return missionService.receive(id,getLoginUser().getStudentNo());
	}
	
	@ApiOperation("查看任务")
//	@PreAuthorize("hasAuthority('student:mission:details')")
	@GetMapping(value = "/details")
	public HttpResult details() {
		return HttpResult.ok();
	}

}

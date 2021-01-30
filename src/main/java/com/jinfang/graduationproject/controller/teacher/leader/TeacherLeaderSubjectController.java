
package com.jinfang.graduationproject.controller.teacher.leader;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.GpSubjectService;
import com.jinfang.graduationproject.vo.teacher.leader.topic.ExamineReqVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/teacher/leader/subject")
@AllArgsConstructor
@Api(tags = "课题操作接口", description = "专业负责人")
public class TeacherLeaderSubjectController extends BaseController{

	private GpSubjectService gpSubjectService;

//	@PreAuthorize("hasAuthority('teacher:leader:subject:list')")
	@ApiOperation("分页查询列表")
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return gpSubjectService.findExaminePage(pageRequest);
	}

//	@PreAuthorize("hasAuthority('teacher:leader:subject:modifyJurisdiction')")
	@ApiOperation("开放修改权限（通过）")
	@GetMapping(value = "/modifyJurisdiction")
	public HttpResult applyModify(@RequestParam String id) {
		if(StringUtils.isEmpty(id)){
			return HttpResult.error("参数为空【id】");
		}
		return gpSubjectService.modifyJurisdiction(Long.valueOf(id), String.valueOf(getLoginUser().getTeacherId()));
	}

//	@PreAuthorize("hasAuthority('teacher:leader:subject:examine')")
	@ApiOperation("审核课题")
	@PostMapping(value = "/examine")
	public HttpResult examine(@RequestBody ExamineReqVo reqVo) {
		reqVo.setApproveUser(String.valueOf(getLoginUser().getTeacherId()));
		return gpSubjectService.examine(reqVo);
	}
	
//	@PreAuthorize("hasAuthority('teacher:leader:subject:details')")
	 @ApiOperation("查看")
	@PostMapping(value="/details")
	public HttpResult details() {
		return HttpResult.ok();
	}
	
}

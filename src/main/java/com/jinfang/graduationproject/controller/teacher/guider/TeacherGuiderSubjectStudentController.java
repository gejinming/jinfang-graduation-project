package com.jinfang.graduationproject.controller.teacher.guider;

import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.domain.GpSubjectStudent;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.GpSubjectService;
import com.jinfang.graduationproject.service.GpSubjectStudentService;
import com.jinfang.graduationproject.vo.teacher.topic.SeeReceiveStudentReqVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "学生选题接口", description = "指导教师，一般用于列表查看，审核等操作")
@RestController
@RequestMapping("/teacher/guider/student")
@AllArgsConstructor
public class TeacherGuiderSubjectStudentController  extends BaseController{
	
	private GpSubjectService gpSubjectService;
	private GpSubjectStudentService subjectStudentService;

//	@PreAuthorize("hasAuthority('teacher:guider:student:list')")
	@ApiOperation("分页查询列表")
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		addTeacherId(pageRequest, getLoginUser());
		addSchoolId(pageRequest, getLoginUser());
		return HttpResult.ok(gpSubjectService.findPage(pageRequest));
	}
	
//	@PreAuthorize("hasAuthority('teacher:guider:student:seeReceiveStudent')")
	 @ApiOperation("根据课题id查询已接收学生明细")
	@PostMapping(value="/seeReceiveStudent")
	public HttpResult seeReceiveStudent(@RequestBody SeeReceiveStudentReqVo reqVo) {
		return HttpResult.ok(subjectStudentService.seeReceiveStudent(reqVo));
	}

//	@PreAuthorize("hasAuthority('teacher:guider:student:details')")
	 @ApiOperation("查看课题")
	@PostMapping(value="/details")
	public HttpResult details() {
		return HttpResult.ok();
	}
	 
//	@PreAuthorize("hasAuthority('teacher:guider:student:choiceStudent')")
	@ApiOperation("选择学生")
	@PostMapping(value="/choiceStudent")
	public HttpResult choiceStudent() {
		 return HttpResult.ok();
	}

//	@PreAuthorize("hasAuthority('teacher:guider:student:accept')")
	@ApiOperation("接受")
	@PostMapping(value="/accept")
	public HttpResult accept(@RequestBody GpSubjectStudent student) {
		student.setApproveUser(String.valueOf(getLoginUser().getTeacherId()));
		return subjectStudentService.accept(student);
	}

//	@PreAuthorize("hasAuthority('teacher:guider:student:refuse')")
	@ApiOperation("拒绝")
	@PostMapping(value="/refuse")
	public HttpResult refuse(@RequestBody GpSubjectStudent student) {
		student.setApproveUser(String.valueOf(getLoginUser().getTeacherId()));
		return subjectStudentService.refuse(student);
	}
	
	
	
	
}	

package com.jinfang.graduationproject.controller.teacher;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.service.GradecomposeService;
import com.jinfang.graduationproject.vo.LoginUserMeta;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/teacher/gradecompose")
@AllArgsConstructor
@Api(tags = "成绩组成查询接口", description = "评阅教师、答辩组组长..")
public class TeacherGradecomposeController extends BaseController {

	private GradecomposeService gradecomposeService;

	@ApiOperation("根据学校和届别查询所有的成绩组成列表")
	@PostMapping(value = "/findBySchoolIdAndPeriod")
	public HttpResult findBySchoolIdAndPeriod() {
		LoginUserMeta userMeta = getLoginUser();
		return HttpResult.ok(gradecomposeService.findBySchoolIdAndPeriod(userMeta.getGrade(),
				userMeta.getSchoolId(), userMeta.getRoles()));
	}

	@ApiOperation("根据成绩组成ID获取所有的课程目标列表")
	@GetMapping(value = "/findIndications")
	public HttpResult findIndications(@RequestParam("courseGradecomposeId") Long courseGradecomposeId ) {
		return HttpResult.ok(gradecomposeService.findIndications(courseGradecomposeId));
	}

}

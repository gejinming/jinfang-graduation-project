package com.jinfang.graduationproject.controller.teacher.checker;

import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.domain.GpCheckAssessment;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.GpCheckAssessmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher/checker/assessment")
@Api(tags = "评阅教师评语接口", description = "评阅教师")
@AllArgsConstructor
public class TeacherCheckerAssessmentController extends BaseController {

    private GpCheckAssessmentService checkAssessmentService;

    /**
     * 基础分页查询
     */
//	@PreAuthorize("hasAuthority('teacher:checker:assessment:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        addSchoolId(pageRequest, getLoginUser());

        addTeacherId(pageRequest, getLoginUser());

        return HttpResult.ok(checkAssessmentService.findCheckAssessmentList(pageRequest));
    }

    @ApiOperation("添加")
	@PreAuthorize("hasAuthority('teacher:checker:assessment:add')")
    @PostMapping(value = "/add")
    public HttpResult add(@RequestBody GpCheckAssessment assessment) {
        return HttpResult.ok(checkAssessmentService.add(assessment));
    }

//	@PreAuthorize("hasAuthority('teacher:checker:assessment:details')")
	 @ApiOperation("查看")
	@PostMapping(value="/details")
	public HttpResult details() {
		return HttpResult.ok();
	}
}

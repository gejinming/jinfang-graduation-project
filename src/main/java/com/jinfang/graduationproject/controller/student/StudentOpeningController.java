
package com.jinfang.graduationproject.controller.student;

import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.GpOpeningReportService;
import com.jinfang.graduationproject.vo.student.opening.WriteOpeningReqVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/opening")
@AllArgsConstructor
@Api(tags = "开题报告接口", description = "学生")
public class StudentOpeningController extends BaseController{
	
	private GpOpeningReportService openingReportService;

//	@PreAuthorize("hasAuthority('student:opening:list')")
	@ApiOperation("分页查询列表")
	@PostMapping(value = "/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		addCondition(pageRequest, "studentNo", getLoginUser().getStudentNo());
		return openingReportService.getByStudentNoOpeningList(pageRequest);
	}

	@ApiOperation("编写（暂存未提交）")
//	@PreAuthorize("hasAuthority('student:opening:write')")
	@PostMapping(value = "/write")
	public HttpResult write(@RequestBody WriteOpeningReqVo reocrd) {
		reocrd.setSubmitUser(getLoginUser().getStudentNo());
		return openingReportService.write(reocrd);
	}

	@ApiOperation("提交")
//	@PreAuthorize("hasAuthority('student:opening:add')")
	@GetMapping(value = "/add")
	public HttpResult add(@RequestParam Long id) {
		return openingReportService.add(id, getLoginUser().getStudentNo());
	}

}

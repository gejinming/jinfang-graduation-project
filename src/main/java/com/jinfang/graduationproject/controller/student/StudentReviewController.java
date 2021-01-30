
package com.jinfang.graduationproject.controller.student;

import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.GpReviewService;
import com.jinfang.graduationproject.vo.student.review.WriteReqVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/review")
@AllArgsConstructor
@Api(tags = "综合文献接口", description = "学生")
public class StudentReviewController extends BaseController {

	private GpReviewService reviewService;

//	@PreAuthorize("hasAuthority('student:review:list')")
	@ApiOperation("分页查询列表")
	@PostMapping(value = "/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		addCondition(pageRequest, "studentNo", getLoginUser().getStudentNo());
		return reviewService.getByStudentNoReviewList(pageRequest);
	}

	@ApiOperation("编写（暂存未提交）")
//	@PreAuthorize("hasAuthority('student:review:write')")
	@PostMapping(value = "/write")
	public HttpResult write(@RequestBody WriteReqVo reocrd) {
		reocrd.setSubmitUser(getLoginUser().getStudentNo());
		return reviewService.write(reocrd);
	}

	@ApiOperation("提交")
//	@PreAuthorize("hasAuthority('student:review:add')")
	@GetMapping(value = "/add")
	public HttpResult add(@RequestParam Long id) {
		return reviewService.add(id, getLoginUser().getStudentNo());
	}

}

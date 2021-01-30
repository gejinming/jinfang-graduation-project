
package com.jinfang.graduationproject.controller.teacher.guider;

import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.domain.GpGuideAssessment;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.GpGuideAssessmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "指导教师评语接口", description = "指导教师")
@RestController
@RequestMapping("/teacher/guider/comment")
@AllArgsConstructor
public class TeacherGuiderGuideAssessmentController extends BaseController {

    private GpGuideAssessmentService guideAssessmentService;

//    @PreAuthorize("hasAuthority('teacher:guider:comment:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        addTeacherId(pageRequest, getLoginUser());
        addSchoolId(pageRequest, getLoginUser());
        return HttpResult.ok(guideAssessmentService.findPage(pageRequest));
    }

//    @PreAuthorize("hasAuthority('teacher:guider:comment:add')")
    @ApiOperation("添加")
    @PostMapping(value = "/add")
    public HttpResult add(@RequestBody GpGuideAssessment resqVo) {
        return guideAssessmentService.add(resqVo);
    }
}

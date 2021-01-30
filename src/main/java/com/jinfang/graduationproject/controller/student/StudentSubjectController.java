
package com.jinfang.graduationproject.controller.student;

import com.alibaba.druid.util.StringUtils;
import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.domain.GpSubjectStudent;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.GpSubjectService;
import com.jinfang.graduationproject.service.GpSubjectStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/subject")
@AllArgsConstructor
@Api(tags = "课题接口", description = "学生")
public class StudentSubjectController extends BaseController {

    private GpSubjectService subjectService;
    private GpSubjectStudentService subjectStudentService;

//    @PreAuthorize("hasAuthority('student:subject:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        addCondition(pageRequest, "studentId", getLoginUser().getStudentId());

        return HttpResult.ok(subjectStudentService.findPage(pageRequest));
    }

//    @PreAuthorize("hasAuthority('student:subject:details')")
    @ApiOperation("根据课题id获取课题明细")
    @GetMapping(value = "/details")
    public HttpResult details(@RequestParam Long subjectId) {
        return subjectService.details(subjectId);
    }

//    @PreAuthorize("hasAuthority('student:subject:choiceResultList')")
    @ApiOperation("选题结果查询")
    @GetMapping(value = "/choiceResultList")
    public HttpResult choiceResultList() {
        return subjectStudentService.choiceResultList(getLoginUser().getStudentNo());
    }

//    @PreAuthorize("hasAuthority('student:subject:add')")
    @ApiOperation("选择课题")
    @GetMapping(value = "/add")
    public HttpResult add(@RequestParam String subjectId) {
        if (StringUtils.isEmpty(subjectId)) {
            return HttpResult.error("请求参数异常 subjectId 为空");
        }

        GpSubjectStudent record = new GpSubjectStudent();
        record.setSubjectId(Long.valueOf(subjectId));
        record.setStudentId(getLoginUser().getStudentId());
        record.setStudentNo(getLoginUser().getStudentNo());
        record.setChooseUser(getLoginUser().getStudentNo());
        return subjectService.studentChoiceTopic(record);
    }

}

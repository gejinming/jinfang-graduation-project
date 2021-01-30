package com.jinfang.graduationproject.controller.teacher.guider;


import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.WorkInstructionService;
import com.jinfang.graduationproject.vo.workinstruction.WorkInstructionAddReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "填写工作指导卡", description = "指导教师")
@RestController
@RequestMapping("/teacher/guider/workInstruction")
@AllArgsConstructor
public class TeacherGuiderWorkInstructionController extends BaseController {

    private WorkInstructionService workInstructionService;

//    @PreAuthorize("hasAuthority('teacher:guider:workInstruction:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        addTeacherId(pageRequest, getLoginUser());

        return HttpResult.ok(workInstructionService.findPage(pageRequest));
    }

//    @PreAuthorize("hasAuthority('teacher:guider:workInstruction:detail')")
    @ApiOperation("添加前调用")
    @GetMapping(value = "/detail")
    public HttpResult detail(@RequestParam("subjectId") long subjectId, @RequestParam("studentId") long studentId) {
        return HttpResult.ok(workInstructionService.findDetail(subjectId, studentId));
    }

//    @PreAuthorize("hasAuthority('teacher:guider:workInstruction:add')")
    @ApiOperation("添加")
    @PostMapping(value = "/add")
    public HttpResult add(@RequestBody WorkInstructionAddReq workInstructionAddReq) {
        workInstructionAddReq.setOperator(getLoginUser().getTeacherIdText());

        return workInstructionService.save(workInstructionAddReq);
    }

}

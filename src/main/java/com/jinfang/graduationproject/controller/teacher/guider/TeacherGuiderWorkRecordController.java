package com.jinfang.graduationproject.controller.teacher.guider;


import com.jinfang.graduationproject.constants.DissertationStep;
import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.WorkRecordService;
import com.jinfang.graduationproject.vo.record.WorkRecordAddReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "填写工作记录卡", description = "指导教师")
@RestController
@RequestMapping("/teacher/guider/workRecord")
@AllArgsConstructor
public class TeacherGuiderWorkRecordController extends BaseController {

    private WorkRecordService workRecordService;

//    @PreAuthorize("hasAuthority('teacher:guider:workRecord:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        addTeacherId(pageRequest, getLoginUser());

        return HttpResult.ok(workRecordService.findPage(pageRequest));
    }

//    @PreAuthorize("hasAuthority('teacher:guider:workRecord:detail')")
    @ApiOperation("添加前调用")
    @GetMapping(value = "/detail")
    public HttpResult detail(@RequestParam("subjectId") long subjectId, @RequestParam("studentId") long studentId) {
        Map<String, Object> response = new HashMap<>();
        response.put("steps", DissertationStep.allSteps());
        response.put("list", workRecordService.findList(subjectId, studentId));

        return HttpResult.ok(response);
    }

//    @PreAuthorize("hasAuthority('teacher:guider:workRecord:add')")
    @ApiOperation("添加")
    @PostMapping(value = "/add")
    public HttpResult add(@RequestBody WorkRecordAddReq workRecordAddReq) {
        workRecordAddReq.setOperator(getLoginUser().getTeacherIdText());

        return workRecordService.save(workRecordAddReq);
    }

}

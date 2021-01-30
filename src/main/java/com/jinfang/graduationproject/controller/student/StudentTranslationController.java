package com.jinfang.graduationproject.controller.student;

import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.domain.GpTranslation;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.GpTranslationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/translation")
@AllArgsConstructor
@Api(tags = "外文翻译接口", description = "学生")
public class StudentTranslationController extends BaseController {

    private GpTranslationService translationService;

//    @PreAuthorize("hasAuthority('student:translation:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        addCondition(pageRequest, "studentNo", getLoginUser().getStudentNo());
        return translationService.getByStudentNoTranslationList(pageRequest);
    }

    @ApiOperation("保存（暂存未提交）")
//    @PreAuthorize("hasAuthority('student:translation:write')")
    @PostMapping(value = "/write")
    public HttpResult write(@RequestBody GpTranslation reocrd) {
        reocrd.setSubmitUser(getLoginUser().getStudentNo());
        return translationService.write(reocrd);
    }

    @ApiOperation("提交")
//	@PreAuthorize("hasAuthority('student:translation:add')")
    @GetMapping(value = "/add")
    public HttpResult add(@RequestParam Long id) {
        return translationService.add(id, getLoginUser().getStudentNo());
    }
}

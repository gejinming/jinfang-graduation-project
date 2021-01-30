package com.jinfang.graduationproject.controller.teacher.guider;

import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.GpTranslationHistoryService;
import com.jinfang.graduationproject.service.GpTranslationService;
import com.jinfang.graduationproject.vo.teacher.translate.ExamineResqVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api(tags = "外文翻译接口", description = "指导教师")
@RestController
@RequestMapping("/teacher/guider/translation")
@AllArgsConstructor
public class TeacherGuiderTranslationController extends BaseController {

    private GpTranslationService translationService;
    private GpTranslationHistoryService gpTranslationHistoryService;

//    @PreAuthorize("hasAuthority('teacher:guider:translation:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        addTeacherId(pageRequest, getLoginUser());
        return HttpResult.ok(translationService.findPage(pageRequest));
    }

//    @PreAuthorize("hasAuthority('teacher:guider:translation:examine')")
    @ApiOperation("查看/检查")
    @PostMapping(value = "/examine")
    public HttpResult examine(@RequestBody ExamineResqVo resqVo) {
        resqVo.setApproveUser(String.valueOf(getLoginUser().getTeacherId()));
        return translationService.examine(resqVo);
    }

//    @PreAuthorize("hasAuthority('teacher:guider:translation:getByTranslationIdList')")
    @ApiOperation("查看历史记录")
    @GetMapping(value = "/getByTranslationIdList")
    public HttpResult getByTranslationIdList(@RequestParam Long id) {
        if (id == null) {
            return HttpResult.error("参数为空【id】");
        }
        return gpTranslationHistoryService.getByTranslationIdList(id);
    }

//    @PreAuthorize("hasAuthority('teacher:guider:translation:getHistoryDetail')")
    @ApiOperation("查看历史明细")
    @GetMapping(value = "/getHistoryDetail")
    public HttpResult getHistoryDetail(@RequestParam Long id) {
        if (id == null) {
            return HttpResult.error("参数为空【id】");
        }
        return gpTranslationHistoryService.getHistoryDetail(id);
    }
}

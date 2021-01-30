package com.jinfang.graduationproject.controller.teacher.guider;

import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.GpOpeningReportHistoryService;
import com.jinfang.graduationproject.service.GpOpeningReportService;
import com.jinfang.graduationproject.vo.teacher.opening.QueryOpeningResqVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "开题报告接口", description = "指导教师")
@RestController
@RequestMapping("/teacher/guider/inspect/opening")
@AllArgsConstructor
public class TeacherGuiderOpeningReportController extends BaseController {

    private GpOpeningReportService openingReportService;
    private GpOpeningReportHistoryService gpOpeningReportHistoryService;

//    @PreAuthorize("hasAuthority('teacher:guider:inspect:opening:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        addTeacherId(pageRequest, getLoginUser());
        return HttpResult.ok(openingReportService.findPage(pageRequest));
    }

//    @PreAuthorize("hasAuthority('teacher:guider:inspect:opening:examine')")
    @PostMapping(value = "/examine")
    @ApiOperation("查看/检查")
    public HttpResult examine(@RequestBody QueryOpeningResqVo resqVo) {
        resqVo.setApproveUser(String.valueOf(getLoginUser().getTeacherId()));
        return openingReportService.examine(resqVo);
    }

    @ApiOperation("查看历史记录")
//    @PreAuthorize("hasAuthority('teacher:guider:inspect:opening:getOpeningReportIdList')")
    @GetMapping(value = "/getOpeningReportIdList")
    public HttpResult getOpeningReportIdList(@RequestParam Long id) {
        if (id == null) {
            return HttpResult.error("参数为空【id】");
        }
        return gpOpeningReportHistoryService.getOpeningReportIdList(id);
    }

    @ApiOperation("查看历史明细")
    @GetMapping(value = "/getHistoryDetail")
//    @PreAuthorize("hasAuthority('teacher:guider:inspect:opening:getHistoryDetail')")
    public HttpResult getHistoryDetail(@RequestParam Long id) {
        if (id == null) {
            return HttpResult.error("参数为空【id】");
        }
        return gpOpeningReportHistoryService.getHistoryDetail(id);
    }
}

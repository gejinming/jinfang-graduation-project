package com.jinfang.graduationproject.controller.teacher.guider;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.GpReviewHistoryService;
import com.jinfang.graduationproject.service.GpReviewService;
import com.jinfang.graduationproject.vo.teacher.review.QueryAllResqVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@Api(tags = "文献综述接口", description = "指导教师")
@RestController
@RequestMapping("/teacher/guider/review")
@AllArgsConstructor
public class TeacherGuiderReviewController extends BaseController {

    private GpReviewService gpReviewService;
    private GpReviewHistoryService gpReviewHistoryService;

//    @PreAuthorize("hasAuthority('teacher:guider:review:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        addTeacherId(pageRequest, getLoginUser());
        return HttpResult.ok(gpReviewService.findPage(pageRequest));
    }

//    @PreAuthorize("hasAuthority('teacher:guider:review:examine')")
    @ApiOperation("查看/检查")
    @PostMapping(value = "/examine")
    public HttpResult examine(@RequestBody QueryAllResqVo resqVo) {
        resqVo.setApproveUser(String.valueOf(getLoginUser().getTeacherId()));
        return gpReviewService.examine(resqVo);
    }

    @ApiOperation("查看历史记录")
    @GetMapping(value = "/getReviewIdHisList")
//    @PreAuthorize("hasAuthority('teacher:guider:review:getReviewIdHisList')")
    public HttpResult getReviewIdHisList(@RequestParam Long id) {
        if (id == null) {
            return HttpResult.error("参数为空【id】");
        }
        return gpReviewHistoryService.getReviewIdList(id);
    }

    @ApiOperation("查看历史明细")
    @GetMapping(value = "/getHistoryDetail")
//    @PreAuthorize("hasAuthority('teacher:guider:review:getHistoryDetail')")
    public HttpResult getHistoryDetail(@RequestParam Long id) {
        if (id == null) {
            return HttpResult.error("参数为空【id】");
        }
        return gpReviewHistoryService.getHistoryDetail(id);
    }

}

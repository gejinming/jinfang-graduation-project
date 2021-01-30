package com.jinfang.graduationproject.controller.teacher.guider;

import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.GpDissertationHistoryService;
import com.jinfang.graduationproject.service.GpDissertationService;
import com.jinfang.graduationproject.vo.teacher.opening.DissertationExamineRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "毕业论文接口", description = "指导教师")
@RestController
@RequestMapping("/teacher/guider/inspect/dissertation")
@AllArgsConstructor
public class TeacherGuiderDissertationController extends BaseController {

    private GpDissertationService dissertationService;
    private GpDissertationHistoryService gpDissertationHistoryService;

    @ApiOperation("分页查询列表")
//    @PreAuthorize("hasAuthority('teacher:guider:inspect:dissertation:list')")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        addTeacherId(pageRequest, getLoginUser());
        return HttpResult.ok(dissertationService.findPage(pageRequest));
    }

    @ApiOperation("查看/检查")
//    @PreAuthorize("hasAuthority('teacher:guider:inspect:dissertation:examine')")
    @PostMapping(value = "/examine")
    public HttpResult examine(@RequestBody DissertationExamineRespVo resqVo) {
        resqVo.setApproveUser(String.valueOf(getLoginUser().getTeacherId()));
        return dissertationService.examine(resqVo);
    }

    @ApiOperation("查看历史记录")
    @GetMapping(value = "/findByDissertationIdList")
//    @PreAuthorize("hasAuthority('teacher:guider:inspect:dissertation:findByDissertationIdList')")
    public HttpResult findByDissertationIdList(@RequestParam Long id) {
        if (id == null) {
            return HttpResult.error("参数为空【id】");
        }
        return gpDissertationHistoryService.findByDissertationIdList(id);
    }
    
    @ApiOperation("查看历史记录-查看明细")
    @GetMapping(value = "/getHistoryDetail")
//    @PreAuthorize("hasAuthority('teacher:guider:inspect:dissertation:getHistoryDetail')")
    public HttpResult getHistoryDetail() {
        return HttpResult.ok();
    }
    
    @ApiOperation(value = "下载", httpMethod = "GET")
    @GetMapping(value = "/download")
//    @PreAuthorize("hasAuthority('teacher:guider:inspect:dissertation:download')")
    public HttpResult download() {
        return HttpResult.ok();
    }
    
    @ApiOperation("查看历史记录-下载论文相似度检查报告")
    @GetMapping(value = "/getHistoryDownload")
//    @PreAuthorize("hasAuthority('teacher:guider:inspect:dissertation:getHistoryDownload')")
    public HttpResult getHistoryDownload() {
        return HttpResult.ok();
    }
}

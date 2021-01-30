package com.jinfang.graduationproject.controller.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.domain.GpDissertation;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.GpDissertationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/student/dissertation")
@AllArgsConstructor
@Api(tags = "毕业论文接口", description = "学生")
public class DissertationController extends BaseController {

    private GpDissertationService dissertationService;

//    @PreAuthorize("hasAuthority('student:dissertation:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        addCondition(pageRequest, "studentNo", getLoginUser().getStudentNo());
        return dissertationService.getByStudentNoList(pageRequest);
    }

    @ApiOperation("提交")
//    @PreAuthorize("hasAuthority('student:dissertation:add')")
    @GetMapping(value = "/add")
    public HttpResult add(@RequestParam Long id) {
        return dissertationService.add(id, getLoginUser().getStudentNo());
    }

    @ApiOperation("上传毕业论文")
//    @PreAuthorize("hasAuthority('student:dissertation:save')")
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody GpDissertation record) {
        return HttpResult.ok(dissertationService.save(record));
    }
    
    @ApiOperation("上传相似度检查报告")
//  @PreAuthorize("hasAuthority('student:dissertation:saveSimilarity')")
  @PostMapping(value = "/saveSimilarity")
  public HttpResult saveSimilarity() {
      return HttpResult.ok();
  }
    
    @ApiOperation("下载")
    @GetMapping(value = "/download")
//    @PreAuthorize("hasAuthority('teacher:guider:inspect:dissertation:download')")
    public HttpResult download() {
        return HttpResult.ok();
    }
    
    @ApiOperation("下载论文和相似度检测报告")
    @GetMapping(value = "/historyDownload")
//    @PreAuthorize("hasAuthority('student:dissertation:historyDownload')")
    public HttpResult historyDownload() {
        return HttpResult.ok();
    }
}

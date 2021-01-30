package com.jinfang.graduationproject.controller.teacher.leader;


import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.BornDocumentService;
import com.jinfang.graduationproject.service.GpDissertationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 论文下载控制层 （专业负责人/指导教师/答辩组组长）
 */
@RestController
@RequestMapping("/teacher/leader/document")
@AllArgsConstructor
@Api(tags = "论文及资料下载接口", description = "专业负责人..")
public class TeacherLeaderDocumentController extends BaseController {

    private GpDissertationService gpDissertationService;
    private BornDocumentService bornDocumentService;

    //    @PreAuthorize("hasAuthority('teacher:leader:document:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        addSchoolId(pageRequest, getLoginUser());

        return HttpResult.ok(gpDissertationService.findPageInPassedByLeader(pageRequest));
    }

    @ApiOperation("查询已有文件届别名称")
//    @PreAuthorize("hasAuthority('teacher:leader:document:findGradeList')")
    @GetMapping(value = "/findGradeList")
    public HttpResult findGradeList() {
        return HttpResult.data(bornDocumentService.findGradeList());
    }

    //    @PreAuthorize("hasAuthority('teacher:leader:document:bornDoc')")
    @ApiOperation("生成文件")
    @PostMapping(value = "/bornDoc")
    public HttpResult bornDoc() {
        return HttpResult.by(bornDocumentService.bornDoc(getLoginUser()));
    }


    //    @PreAuthorize("hasAuthority('teacher:leader:document:download')")
    @ApiOperation(value = "下载", httpMethod = "GET")
    @GetMapping(value = "/download")
    public ResponseEntity<InputStreamResource> download(@RequestParam("subjectStudentId") Long subjectStudentId) {
        return bornDocumentService.download(subjectStudentId);
    }

    //    @PreAuthorize("hasAuthority('teacher:leader:document:downloadByLeader')")
    @ApiOperation(value = "综合下载（届别）", notes = "专业负责人专有", httpMethod = "GET")
    @GetMapping(value = "/downloadByLeader")
    public ResponseEntity<InputStreamResource> download(@RequestParam("grade") String grade) {
        return bornDocumentService.download(grade);
    }


}

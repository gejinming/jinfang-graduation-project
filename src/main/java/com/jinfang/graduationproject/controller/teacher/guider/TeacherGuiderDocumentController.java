package com.jinfang.graduationproject.controller.teacher.guider;


import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.BornDocumentService;
import com.jinfang.graduationproject.service.GpDissertationService;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 论文下载控制层
 */
@RestController
@RequestMapping("/teacher/guider/document")
@AllArgsConstructor
@Api(tags = "论文及资料下载接口", description = "指导教师..")
public class TeacherGuiderDocumentController extends BaseController {

    private GpDissertationService gpDissertationService;
    private BornDocumentService bornDocumentService;

    //    @PreAuthorize("hasAuthority('teacher:guider:document:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        LoginUserMeta loginUserMeta = getLoginUser();

        addSchoolId(pageRequest, loginUserMeta);

        addTeacherId(pageRequest, loginUserMeta);

        return HttpResult.ok(gpDissertationService.findPageInPassedByGuider(pageRequest));
    }

    //    @PreAuthorize("hasAuthority('teacher:guider:document:download')")
    @ApiOperation(value = "下载", httpMethod = "GET")
    @GetMapping(value = "/download")
    public ResponseEntity<InputStreamResource> download(@RequestParam("subjectStudentId") Long subjectStudentId) {
        return bornDocumentService.download(subjectStudentId);
    }

}

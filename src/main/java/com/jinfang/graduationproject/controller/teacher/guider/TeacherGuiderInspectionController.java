package com.jinfang.graduationproject.controller.teacher.guider;

import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.domain.GpInspection;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.GpInspectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "中期检查接口", description = "指导教师")
@RestController
@RequestMapping("/teacher/guider/inspection")
@AllArgsConstructor
public class TeacherGuiderInspectionController extends BaseController {

    private GpInspectionService gpInspectionService;

    @PostMapping(value = "/save")
    @ApiOperation("添加中期检查记录")
//    @PreAuthorize("hasAuthority('teacher:guider:inspection:save')")
    public HttpResult save(@RequestBody GpInspection record) {
        record.setOperateUser(getLoginUser().getTeacherIdText());

        return HttpResult.ok(gpInspectionService.save(record));
    }

//    @PreAuthorize("hasAuthority('teacher:guider:inspection:list')")
    @PostMapping(value = "/findPage")
    @ApiOperation("分页查询列表")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        addSchoolId(pageRequest, getLoginUser());
        addTeacherId(pageRequest, getLoginUser());
        return HttpResult.ok(gpInspectionService.findPage(pageRequest));
    }

}

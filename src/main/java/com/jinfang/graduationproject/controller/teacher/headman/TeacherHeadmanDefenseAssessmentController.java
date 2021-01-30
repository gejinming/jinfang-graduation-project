package com.jinfang.graduationproject.controller.teacher.headman;


import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.domain.GpDefenseAssessment;
import com.jinfang.graduationproject.domain.GpOpeningDefense;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.DefenseAssessmentService;
import com.jinfang.graduationproject.service.OpeningDefenseService;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher/headman/defenseAssessment")
@AllArgsConstructor
@Api(tags = "答辩评语表接口", description = "答辩组组长")
public class TeacherHeadmanDefenseAssessmentController extends BaseController {

    private DefenseAssessmentService defenseAssessmentService;

    @ApiOperation("分页查询列表")
//    @PreAuthorize("hasAuthority('teacher:headman:defenseAssessment:list')")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        LoginUserMeta userMeta = getLoginUser();

        addSchoolId(pageRequest, userMeta);

        addGrade(pageRequest, userMeta);

        return HttpResult.ok(defenseAssessmentService.findPage(pageRequest));
    }

//    @PreAuthorize("hasAuthority('teacher:headman:defenseAssessment:getDetail')")
    @ApiOperation("根据id查看详情")
    @GetMapping(value = "/getDetail/{id}")
    public HttpResult getDetail(@PathVariable("id") Long id) {
        return HttpResult.ok(defenseAssessmentService.findById(id));
    }

//    @PreAuthorize("hasAuthority('teacher:headman:defenseAssessment:addOrUpdate')")
    @ApiOperation("添加/修改记录")
    @PostMapping(value = "/addOrUpdate")
    public HttpResult add(@RequestBody GpDefenseAssessment gpDefenseAssessment) {
        gpDefenseAssessment.setOperateUser(getLoginUser().getTeacherIdText());
        try {
            return defenseAssessmentService.saveOrUpdate(gpDefenseAssessment);
        } catch (Exception e) {
            return HttpResult.error("操作失败");
        }
    }

}

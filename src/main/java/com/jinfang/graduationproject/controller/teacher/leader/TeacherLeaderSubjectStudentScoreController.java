package com.jinfang.graduationproject.controller.teacher.leader;


import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.domain.GpSubjectStudentScoreWeight;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.service.SubjectStudentScoreService;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher/leader/subjectStudentScore")
@AllArgsConstructor
@Api(tags = "最终成绩接口", description = "专业负责人")
public class TeacherLeaderSubjectStudentScoreController extends BaseController {

    private SubjectStudentScoreService subjectStudentScoreService;

//    @PreAuthorize("hasAuthority('teacher:leader:subjectStudentScore:list')")
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        LoginUserMeta userMeta = getLoginUser();

        addGrade(pageRequest, userMeta);

        addSchoolId(pageRequest, userMeta);


        return HttpResult.ok(subjectStudentScoreService.findPage(pageRequest));
    }

//    @PreAuthorize("hasAuthority('teacher:leader:subjectStudentScore:build')")
    @ApiOperation("生成最终成绩")
    @PostMapping(value = "/build")
    public HttpResult build(@RequestBody GpSubjectStudentScoreWeight gpSubjectStudentScoreWeight) {
        if (gpSubjectStudentScoreWeight == null) {
            return HttpResult.error("数据参数为空");
        }

        LoginUserMeta userMeta = getLoginUser();

        gpSubjectStudentScoreWeight.setOperateUser(getLoginUser().getTeacherIdText());
        gpSubjectStudentScoreWeight.setGrade(userMeta.getGrade());
        gpSubjectStudentScoreWeight.setSchoolId(userMeta.getSchoolId());

        return subjectStudentScoreService.save(gpSubjectStudentScoreWeight);
    }


}

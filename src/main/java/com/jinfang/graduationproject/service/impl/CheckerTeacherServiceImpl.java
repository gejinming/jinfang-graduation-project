package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.domain.CcCoursePeriode;
import com.jinfang.graduationproject.domain.GpCheckTeacher;
import com.jinfang.graduationproject.domain.GpDefenseTeacher;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.CcCoursePeriodeMapper;
import com.jinfang.graduationproject.mapper.GpCheckTeacherMapper;
import com.jinfang.graduationproject.mapper.GpDefenseTeacherMapper;
import com.jinfang.graduationproject.service.CheckerTeacherService;
import com.jinfang.graduationproject.util.DecodeUtil;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import com.jinfang.graduationproject.vo.teacher.checker.CheckerStudentRespVo;
import com.jinfang.graduationproject.vo.teacher.checker.CheckerTeacherDispatchReq;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CheckerTeacherServiceImpl implements CheckerTeacherService {

    private GpCheckTeacherMapper gpCheckTeacherMapper;
    private GpDefenseTeacherMapper gpDefenseTeacherMapper;
    private CcCoursePeriodeMapper ccCoursePeriodeMapper;

    @Override
    public int save(GpCheckTeacher record) {
        return 0;
    }

    @Override
    public int delete(GpCheckTeacher record) {
        return 0;
    }

    @Override
    public int delete(List<GpCheckTeacher> records) {
        return 0;
    }

    @Override
    public GpCheckTeacher findById(Long id) {
        return gpCheckTeacherMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult pageResult = MybatisPageHelper.findPage(pageRequest, gpDefenseTeacherMapper, "findPageInChecker",
                pageRequest.getExtProps().get("schoolId"));

        if (pageResult != null && pageResult.getContent().size() > 0) {
            for (Object data : pageResult.getContent()) {
                GpDefenseTeacher defenseTeacher = (GpDefenseTeacher) data;

                defenseTeacher.setStudentAmount(gpCheckTeacherMapper.findDistributedCount(defenseTeacher.getId()));
            }
        }
        return pageResult;
    }

    @Override
    public HttpResult distribute(CheckerTeacherDispatchReq checkerTeacherDispatchReq) {
        HttpResult checkResult = validate(checkerTeacherDispatchReq);
        if (!checkResult.isSuccess()) {
            return checkResult;
        }

        LoginUserMeta userMeta = checkerTeacherDispatchReq.getLoginUserMeta();

        CcCoursePeriode ccCoursePeriode = ccCoursePeriodeMapper.selectByGradeAndSchoolId(userMeta.getGrade(),
                userMeta.getSchoolId());

        if (ccCoursePeriode == null) {
            log.error("Invalid CoursePeriode[{}] by grade[{}] and schoolId[{}]", ccCoursePeriode, userMeta.getGrade(),
                    userMeta.getSchoolId());
            return HttpResult.error("毕业设计参数未设置");
        }

        String[] studentIds = checkerTeacherDispatchReq.getStudentIds().split(",");

        List<GpCheckTeacher> list = new ArrayList<>();
        for (String StudentId : studentIds) {
            GpCheckTeacher checkTeacher = new GpCheckTeacher();
            checkTeacher.setDefenseTeacherId(checkerTeacherDispatchReq.getDefenseTeacherId());
            checkTeacher.setStudentId(Long.valueOf(StudentId));
            checkTeacher.setEduClassId(ccCoursePeriode.getClassId());
            checkTeacher.setOperateUser(userMeta.getPrincipalId() + "");
            list.add(checkTeacher);
        }

        boolean isOk = gpCheckTeacherMapper.batchInsert(list) > 0;

        return HttpResult.ok(isOk);

    }

    private HttpResult validate(CheckerTeacherDispatchReq checkerTeacherDispatchReq) {
        if (checkerTeacherDispatchReq == null) {
            return HttpResult.error("empty request");
        }

        if (StringUtils.isEmpty(checkerTeacherDispatchReq.getStudentIds())) {
            log.error("Can't find any students to join -> {}", checkerTeacherDispatchReq);
            return HttpResult.error("no choose teachers");
        }

        return HttpResult.ok();
    }

    @Override
    public HttpResult remove(CheckerTeacherDispatchReq checkerTeacherDispatchReq) {
        HttpResult checkResult = validate(checkerTeacherDispatchReq);
        if (!checkResult.isSuccess()) {
            return checkResult;
        }

        String[] studentIds = checkerTeacherDispatchReq.getStudentIds().split(",");

        int count = 0;
        for (String studentId : studentIds) {
            count = count + gpCheckTeacherMapper.deleteByTeacherIdAndStudentId(checkerTeacherDispatchReq.getDefenseTeacherId(),
                    Long.valueOf(studentId));
        }

        return HttpResult.ok(count == studentIds.length);

    }

    @Override
    public List<CheckerStudentRespVo> findUndistributedList(String grade, Long schoolId, String studentName,
                                                            String studentNo, String className) {
        studentName = DecodeUtil.decode(studentName);
        className = DecodeUtil.decode(className);

        return gpCheckTeacherMapper.findUndistributedList(grade, schoolId, studentName,
                studentNo, className);
    }

    @Override
    public List<CheckerStudentRespVo> findDistributedList(Long defenseTeacherId) {
        return gpCheckTeacherMapper.findDistributedList(defenseTeacherId);
    }

    @Override
    public long findUndistributedCount(String grade, Long schoolId) {
        return gpCheckTeacherMapper.findUndistributedCount(grade, schoolId);
    }

    @Override
    public boolean isTeacherChecker(Long defenseTeacherId) {
        return gpCheckTeacherMapper.findCountByDefenseTeacherId(defenseTeacherId) > 0;
    }
}

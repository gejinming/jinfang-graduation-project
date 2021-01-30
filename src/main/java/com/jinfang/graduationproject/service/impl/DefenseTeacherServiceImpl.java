package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.constants.teacher.DefenseTeacherType;
import com.jinfang.graduationproject.domain.CcTeacher;
import com.jinfang.graduationproject.domain.GpDefenseTeacher;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpDefenseTeacherMapper;
import com.jinfang.graduationproject.service.DefenseTeacherService;
import com.jinfang.graduationproject.util.DateUtil;
import com.jinfang.graduationproject.vo.teacher.defense.DefenseTeacherJoinReq;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class DefenseTeacherServiceImpl implements DefenseTeacherService {

    private GpDefenseTeacherMapper gpDefenseTeacherMapper;

    @Override
    public int save(GpDefenseTeacher record) {
        return 0;
    }

    @Override
    public int delete(GpDefenseTeacher record) {
        return 0;
    }

    @Override
    public int delete(List<GpDefenseTeacher> records) {
        return 0;
    }

    @Override
    public GpDefenseTeacher findById(Long id) {
        return gpDefenseTeacherMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, gpDefenseTeacherMapper, "findPage",
                pageRequest.getAttrValue("grade"), pageRequest.getAttrValue("groupName"),
                pageRequest.getAttrValue("teacherName"), pageRequest.getExtProps().get("schoolId"));

    }

    @Override
    public HttpResult joinTeacher(DefenseTeacherJoinReq defenseTeacherJoinReq) {
        if (defenseTeacherJoinReq == null) {
            return HttpResult.error("empty request");
        }

        if (CollectionUtils.isEmpty(defenseTeacherJoinReq.getTeachers())) {
            log.error("Can't find any teachers to join -> {}", defenseTeacherJoinReq);
            return HttpResult.error("no choose teachers");
        }

        List<GpDefenseTeacher> list = new ArrayList<>();
        for (CcTeacher ccTeacher : defenseTeacherJoinReq.getTeachers()) {
            GpDefenseTeacher defenseTeacher = new GpDefenseTeacher();
            defenseTeacher.setGroupName(defenseTeacherJoinReq.getDefenseGroupName());
            defenseTeacher.setTeacherId(ccTeacher.getId());
            defenseTeacher.setType(DefenseTeacherType.MEMBER.getCode());
            defenseTeacher.setGrade(DateUtil.getCurrentYear() + "");
            defenseTeacher.setMajorId(ccTeacher.getMajorId());
            defenseTeacher.setOperateUser(defenseTeacherJoinReq.getOperatorUser());
            list.add(defenseTeacher);
        }

        boolean isOk = gpDefenseTeacherMapper.batchInsert(list) > 0;

        return HttpResult.ok(isOk);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult setHeadman(Long id, String operateUser) {
        GpDefenseTeacher defenseTeacher = gpDefenseTeacherMapper.findById(id);
        if (defenseTeacher == null) {
            log.error("Can't find any data by id[{}]", id);
            return HttpResult.error("no data found");
        }

        defenseTeacher.setOperateUser(operateUser);
        defenseTeacher.setType(DefenseTeacherType.HEADMAN.getCode());
        defenseTeacher.setIsDel(0);

        int rows = gpDefenseTeacherMapper.update(defenseTeacher);
        if (rows > 0) {
            // 将原有组长信息替换
            rows = gpDefenseTeacherMapper.updateNotHeadman(defenseTeacher.getGrade(), defenseTeacher.getGroupName(),
                    id);
        }

        return HttpResult.ok(rows > 0);
    }

    @Override
    public HttpResult delete(Long id) {
        return HttpResult.ok(gpDefenseTeacherMapper.delete(id) > 0);
    }

    @Override
    public GpDefenseTeacher findByTeacherId(Long teacherId) {
        return gpDefenseTeacherMapper.findByTeacherId(teacherId);
    }

    @Override
    public List<GpDefenseTeacher> findMembersByOne(String grade, Long teacherId) {
        return gpDefenseTeacherMapper.findMembers(grade, teacherId);
    }
}

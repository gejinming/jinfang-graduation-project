package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.constants.SystemRole;
import com.jinfang.graduationproject.domain.CcTeacher;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.CcTeacherMapper;
import com.jinfang.graduationproject.service.TeacherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private CcTeacherMapper ccTeacherMapper;

    @Override
    public List<CcTeacher> findList(String grade, Long schoolId, String majorName, String teacherName) {
        return ccTeacherMapper.findList(grade, schoolId, majorName, teacherName);
    }

    @Override
    public CcTeacher findExtInfoById(Long teacherId) {
        return ccTeacherMapper.findExtById(teacherId);
    }

    @Override
    public int save(CcTeacher record) {
        return 0;
    }

    @Override
    public int delete(CcTeacher record) {
        return 0;
    }

    @Override
    public int delete(List<CcTeacher> records) {
        return 0;
    }

    @Override
    public CcTeacher findById(Long id) {
        return ccTeacherMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return null;
    }

    @Override
    public CcTeacher getTeacherInfo(Long teacherId) {
        CcTeacher teacher = findById(teacherId);
        if (teacher == null) {
            log.error("Can't find andy teacher records by teacherId[{}]", teacherId);
            return null;
        }

        return teacher;
    }

    @Override
    public boolean isSchoolLeader(Long teacherId) {
        long count = ccTeacherMapper.selectCountByRoleName(teacherId, SystemRole.LEADER.getRoleName());
        return count > 0;
    }

    @Override
    public CcTeacher getSchoolLeaderTeacher(Long schoolId) {
        return ccTeacherMapper.selectLeadTeacher(schoolId, SystemRole.LEADER.getRoleName());
    }
}

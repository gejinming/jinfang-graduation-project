package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.domain.CcStudent;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.CcStudentMapper;
import com.jinfang.graduationproject.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private CcStudentMapper ccStudentMapper;

    @Override
    public CcStudent findInfoById(Long id) {
        return ccStudentMapper.findInfoById(id);
    }

    @Override
    public List<CcStudent> findBySchoolAndMajor(Long schoolId, Long majorId) {
        return null;
    }

    @Override
    public int save(CcStudent record) {
        return 0;
    }

    @Override
    public int delete(CcStudent record) {
        return 0;
    }

    @Override
    public int delete(List<CcStudent> records) {
        return 0;
    }

    @Override
    public CcStudent findById(Long id) {
        return ccStudentMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return null;
    }

    @Override
    public boolean isGraduationProjectMember(Long studentId) {
        return ccStudentMapper.selectByStudentIdInEduClass(studentId) > 0;
    }
}

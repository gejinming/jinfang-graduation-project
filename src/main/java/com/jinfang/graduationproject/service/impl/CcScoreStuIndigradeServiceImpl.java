package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.domain.CcScoreStuIndigrade;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.CcScoreStuIndigradeMapper;
import com.jinfang.graduationproject.service.CcScoreStuIndigradeService;
import com.jinfang.graduationproject.service.IdGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CcScoreStuIndigradeServiceImpl implements CcScoreStuIndigradeService {

    private IdGenerator idGenerator;
    private CcScoreStuIndigradeMapper ccScoreStuIndigradeMapper;

    @Override
    public int save(CcScoreStuIndigrade record) {
        if (record.getId() == null || record.getId() == 0) {
            return ccScoreStuIndigradeMapper.add(record);
        }
        return ccScoreStuIndigradeMapper.update(record);
    }

    @Override
    public int delete(CcScoreStuIndigrade record) {
        return ccScoreStuIndigradeMapper.delete(record.getId());
    }

    @Override
    public int delete(List<CcScoreStuIndigrade> records) {
        for (CcScoreStuIndigrade record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public CcScoreStuIndigrade findById(Long id) {
        return ccScoreStuIndigradeMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, ccScoreStuIndigradeMapper);
    }

    @Override
    public boolean add(Long courseGradecomposeId, Long indicationId, Long studentId, int score) {
        Long gradecomposeIndicationId = ccScoreStuIndigradeMapper.selectCourseGradecomposeIndicationId(courseGradecomposeId, indicationId);
        if (gradecomposeIndicationId == null) {
            log.error("Can't find gradecomposeIndicationId by courseGradecomposeId:{} and indicationId:{}",
                    courseGradecomposeId, indicationId);
        }

        CcScoreStuIndigrade ccScoreStuIndigrade = new CcScoreStuIndigrade();
        ccScoreStuIndigrade.setId(idGenerator.getNextValue());
        ccScoreStuIndigrade.setGradecomposeIndicationId(gradecomposeIndicationId);
        ccScoreStuIndigrade.setStudentId(studentId);
        ccScoreStuIndigrade.setGrade((double) score);
        ccScoreStuIndigrade.setCreateDate(new Date());

        return ccScoreStuIndigradeMapper.add(ccScoreStuIndigrade) > 0;
    }
}

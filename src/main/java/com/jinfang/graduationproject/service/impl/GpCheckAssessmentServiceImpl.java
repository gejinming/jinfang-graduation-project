package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.constants.teacher.AssessmentConstants;
import com.jinfang.graduationproject.constants.teacher.SubjectStudentConstants;
import com.jinfang.graduationproject.domain.GpCheckAssessment;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpCheckAssessmentMapper;
import com.jinfang.graduationproject.service.GpCheckAssessmentService;
import com.jinfang.graduationproject.util.ParameterUtil;
import com.jinfang.graduationproject.vo.teacher.checker.assessment.FindCheckAssessmentRespVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class GpCheckAssessmentServiceImpl implements GpCheckAssessmentService {

    private GpCheckAssessmentMapper checkAssessmentMapper;

    @Override
    public PageResult findCheckAssessmentList(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, checkAssessmentMapper, "selectCheckAssessmentList",
                pageRequest.getExtProps().get("schoolId"), pageRequest.getExtProps().get("teacherId"),
                pageRequest.getAttrValue("grade"), pageRequest.getAttrValue("name"),
                pageRequest.getAttrValue("status"));
    }

    @Override
    public int add(GpCheckAssessment assessment) {
        assessment.setCreateDate(new Date());
        assessment.setStatus(AssessmentConstants.Status.YES_FILL_IN.getCode());
        return checkAssessmentMapper.add(assessment);
    }

    @Override
    public FindCheckAssessmentRespVo findBySubjectIdAndStudentId(Long subjectId, Long studentId) {
        return checkAssessmentMapper.selectBySubjectIdAndStudentId(subjectId, studentId);
    }
}

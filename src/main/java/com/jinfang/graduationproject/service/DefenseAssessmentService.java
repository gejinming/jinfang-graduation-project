package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.GpDefenseAssessment;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;

public interface DefenseAssessmentService {

    PageResult findPage(PageRequest pageRequest);

    GpDefenseAssessment findById(Long id);

    HttpResult saveOrUpdate(GpDefenseAssessment gpDefenseAssessment);

    GpDefenseAssessment findBySubjectIdAndStudentId(Long subjectId, Long studentId);

}

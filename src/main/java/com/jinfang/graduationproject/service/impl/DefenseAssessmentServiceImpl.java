package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.domain.CcTeacher;
import com.jinfang.graduationproject.domain.GpDefenseAssessment;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpDefenseAssessmentMapper;
import com.jinfang.graduationproject.service.DefenseAssessmentService;
import com.jinfang.graduationproject.service.TeacherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DefenseAssessmentServiceImpl implements DefenseAssessmentService {

    private GpDefenseAssessmentMapper defenseAssessmentMapper;
    private TeacherService teacherService;

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, defenseAssessmentMapper, "findPage",
                pageRequest.getAttrValue("studentName"),
                pageRequest.getAttrValue("majorName"),
                pageRequest.getAttrValue("subjectName"),
                pageRequest.getAttrValue("subjectType"),
                pageRequest.getAttrValue("subjectSource"),
                pageRequest.getExtProps().get("grade"),
                (Long) pageRequest.getExtProps().get("schoolId"));
    }

    @Override
    public GpDefenseAssessment findById(Long id) {
        return defenseAssessmentMapper.findById(id);
    }

    @Override
    public HttpResult saveOrUpdate(GpDefenseAssessment gpDefenseAssessment) {
        if (gpDefenseAssessment.getId() == null) {
            return HttpResult.ok(defenseAssessmentMapper.add(gpDefenseAssessment));
        }


        return HttpResult.ok(defenseAssessmentMapper.update(gpDefenseAssessment));
    }

    @Override
    public GpDefenseAssessment findBySubjectIdAndStudentId(Long subjectId, Long studentId) {
        GpDefenseAssessment defenseAssessment = defenseAssessmentMapper.selectBySubjectIdAndStudentId(subjectId, studentId);
        if (defenseAssessment == null) {
            return null;
        }

        if (StringUtils.isNotEmpty(defenseAssessment.getOperateUser())) {
            try {
                CcTeacher ccTeacher = teacherService.findById(Long.valueOf(defenseAssessment.getOperateUser()));
                if (ccTeacher != null) {
                    defenseAssessment.setHeadmanName(ccTeacher.getName());
                }
            } catch (NumberFormatException e) {
                log.warn("Find teacher failed, teacherId:{}", defenseAssessment.getOperateUser(), e);
            }
        }

        return defenseAssessment;
    }
}

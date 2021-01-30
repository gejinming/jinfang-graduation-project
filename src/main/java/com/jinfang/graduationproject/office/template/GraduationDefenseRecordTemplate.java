package com.jinfang.graduationproject.office.template;

import com.jinfang.graduationproject.constants.teacher.DefenseTeacherType;
import com.jinfang.graduationproject.domain.GpDefenseTeacher;
import com.jinfang.graduationproject.domain.GpGraduationDefense;
import com.jinfang.graduationproject.domain.GpSetting;
import com.jinfang.graduationproject.mapper.GpGraduationDefenseMapper;
import com.jinfang.graduationproject.office.BaseOfficeTemplate;
import com.jinfang.graduationproject.office.FileName;
import com.jinfang.graduationproject.service.DefenseTeacherService;
import com.jinfang.graduationproject.vo.defense.SubjectStudentGraduationDefenseRes;
import com.jinfang.graduationproject.vo.score.SubjectStudentCompleteVo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class GraduationDefenseRecordTemplate extends BaseOfficeTemplate {

    private GpGraduationDefenseMapper gpGraduationDefenseMapper;
    private DefenseTeacherService defenseTeacherService;

    @Override
    protected Map<String, Object> transformParams(SubjectStudentCompleteVo completeVo) {
        Map<String, Object> params = new HashMap<>();

        params.put("subjectName", completeVo.getSubjectName());
        params.put("gender", completeVo.getStudentSex() == 0 ? "男" : "女");
        params.put("majorName", completeVo.getMajorName());
        params.put("className", completeVo.getClassName());
        params.put("studentName", completeVo.getStudentName());
        params.put("teacherName", completeVo.getTeacherName());


        GpGraduationDefense graduationDefense = gpGraduationDefenseMapper.selectDefenseBySubjectStudentId(completeVo.getSubjectStudentId());
        if (graduationDefense != null) {
            Long recordTeacherId = Long.valueOf(graduationDefense.getRecordUser());

            params.put("studentContent", graduationDefense.getStudentContent());
            params.put("teacherContent", graduationDefense.getTeacherContent());


            List<GpDefenseTeacher> defenseTeachers = defenseTeacherService.findMembersByOne(completeVo.getGrade(),
                    recordTeacherId);
            // 设置答辩组成员信息
            for (int i = 0; i < defenseTeachers.size(); i++) {
                GpDefenseTeacher defenseTeacher = defenseTeachers.get(i);

                params.put("dt" + (i + 1), defenseTeacher.getTeacherName());

                if(DefenseTeacherType.HEADMAN.getCode() == defenseTeacher.getType()) {
                    params.put("defenseHeadman", defenseTeacher.getTeacherName());
                }

                if(recordTeacherId.equals(defenseTeacher.getTeacherId())) {
                    params.put("recordTeacherName", defenseTeacher.getTeacherName());
                }
            }

        }

        GpSetting setting = getSetting(completeVo);
        if (setting != null && setting.getOpeningDefenseTime() != null) {
            params.put("defenseDate", DateFormatUtils.format(setting.getOpeningDefenseTime(), "yyyy年MM月dd日 "));
            params.put("defenseAddress", setting.getOpeningDefenseAddress());
        }

        return params;
    }


    @Override
    protected FileName.Mapping fileMapping() {
        return FileName.Mapping.GRADUATION_DEFENSE_RECORD;
    }

}

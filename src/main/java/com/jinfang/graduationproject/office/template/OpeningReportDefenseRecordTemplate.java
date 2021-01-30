package com.jinfang.graduationproject.office.template;

import com.jinfang.graduationproject.domain.GpOpeningDefense;
import com.jinfang.graduationproject.domain.GpOpeningReport;
import com.jinfang.graduationproject.domain.GpSetting;
import com.jinfang.graduationproject.office.BaseOfficeTemplate;
import com.jinfang.graduationproject.office.FileName;
import com.jinfang.graduationproject.service.GpOpeningReportService;
import com.jinfang.graduationproject.service.OpeningDefenseService;
import com.jinfang.graduationproject.vo.defense.SubjectStudentOpeningDefenseRes;
import com.jinfang.graduationproject.vo.score.SubjectStudentCompleteVo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class OpeningReportDefenseRecordTemplate extends BaseOfficeTemplate {

    private OpeningDefenseService openingDefenseService;
    private GpOpeningReportService gpOpeningReportService;

    @Override
    protected Map<String, Object> transformParams(SubjectStudentCompleteVo completeVo) {
        Map<String, Object> params = new HashMap<>();

        params.put("instituteName", completeVo.getInstituteName());
        params.put("className", completeVo.getClassName());
        params.put("studentNo", completeVo.getStudentNo());

        params.put("studentName", completeVo.getStudentName());
        params.put("gender", completeVo.getStudentSex() == 0 ? "男" : "女");
        params.put("grade", completeVo.getGrade());
        params.put("majorName", completeVo.getMajorName());
        params.put("teacherName", completeVo.getTeacherName());
        params.put("subjectName", completeVo.getSubjectName());

        params.put("result", "通过");

        GpSetting setting = getSetting(completeVo);
        if(setting != null && setting.getOpeningDefenseTime() != null) {
            params.put("openingDefenseDate", DateFormatUtils.format(setting.getOpeningDefenseTime(), "yyyy年MM月dd日 "));
            params.put("openingDefenseAddress", setting.getOpeningDefenseAddress());
        }

        GpOpeningReport openingReport = gpOpeningReportService.getBySubjectStudentId(completeVo.getSubjectStudentId());
        if(openingReport != null) {
            params.put("approveSuggest", openingReport.getApproveSuggest());
            params.put("openingReportDate", DateFormatUtils.format(openingReport.getApproveTime(), "yyyy年MM月dd日"));
        }

        GpOpeningDefense openingDefense = openingDefenseService.findDefenseBySubjectStudentId(completeVo.getSubjectStudentId());
        if(openingDefense != null) {
            params.put("defenseHeadman", openingDefense.getHeadmanName());
        }

        return params;
    }


    @Override
    protected FileName.Mapping fileMapping() {
        return FileName.Mapping.OPENING_REPORT_DEFENSE_RECORD;
    }

}

package com.jinfang.graduationproject.office.template;

import com.jinfang.graduationproject.constants.DissertationStep;
import com.jinfang.graduationproject.domain.GpWorkRecord;
import com.jinfang.graduationproject.office.BaseOfficeTemplate;
import com.jinfang.graduationproject.office.FileName;
import com.jinfang.graduationproject.service.WorkRecordService;
import com.jinfang.graduationproject.vo.score.SubjectStudentCompleteVo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class WorkRecordTemplate extends BaseOfficeTemplate {

    private WorkRecordService workRecordService;

    @Override
    protected Map<String, Object> transformParams(SubjectStudentCompleteVo completeVo) {
        Map<String, Object> params = new HashMap<>();

        params.put("instituteName", completeVo.getInstituteName());
        params.put("majorName", completeVo.getMajorName());
        params.put("className", completeVo.getClassName());
        params.put("studentName", completeVo.getStudentName());

        List<GpWorkRecord> workRecords = workRecordService.findList(completeVo.getSubjectId(), completeVo.getStudentId());
        workRecords.forEach(record -> {
            if (DissertationStep.SUBJECT.getCode() == record.getStep()) {
                params.put("subjectTime", DateFormatUtils.format(record.getStepTime(), "yyyy-MM-dd"));
                params.put("subjectContent", record.getContent());
            } else if (DissertationStep.OPENING_REPORT.getCode() == record.getStep()) {
                params.put("openingReportTime", DateFormatUtils.format(record.getStepTime(), "yyyy-MM-dd"));
                params.put("openingReportContent", record.getContent());
            } else if (DissertationStep.COLLECT_DATA.getCode() == record.getStep()) {
                params.put("collectTime", DateFormatUtils.format(record.getStepTime(), "yyyy-MM-dd"));
                params.put("collectContent", record.getContent());
            } else if (DissertationStep.FIRST_DRAFT.getCode() == record.getStep()) {
                params.put("firstVersionTime", DateFormatUtils.format(record.getStepTime(), "yyyy-MM-dd"));
                params.put("firstVersionContent", record.getContent());
            } else if (DissertationStep.SECOND_DRAFT.getCode() == record.getStep()) {
                params.put("secondVersionTime", DateFormatUtils.format(record.getStepTime(), "yyyy-MM-dd"));
                params.put("secondVersionContent", record.getContent());
            } else if (DissertationStep.FINAL_DRAFT.getCode() == record.getStep()) {
                params.put("finalVersionTime", DateFormatUtils.format(record.getStepTime(), "yyyy-MM-dd"));
                params.put("finalVersionContent", record.getContent());
            } else if (DissertationStep.DEFENSE.getCode() == record.getStep()) {
                params.put("defenseTime", DateFormatUtils.format(record.getStepTime(), "yyyy-MM-dd"));
                params.put("defenseContent", record.getContent());
            }

        });

        return params;
    }

    @Override
    protected FileName.Mapping fileMapping() {
        return FileName.Mapping.WORD_RECORD;
    }

}

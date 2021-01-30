package com.jinfang.graduationproject.office.template;

import com.deepoove.poi.data.NumbericRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.jinfang.graduationproject.domain.GpOpeningReport;
import com.jinfang.graduationproject.domain.GpOpeningReportLiterature;
import com.jinfang.graduationproject.domain.GpSetting;
import com.jinfang.graduationproject.office.BaseOfficeTemplate;
import com.jinfang.graduationproject.office.FileName;
import com.jinfang.graduationproject.service.GpOpeningReportLiteratureService;
import com.jinfang.graduationproject.service.GpOpeningReportService;
import com.jinfang.graduationproject.vo.score.SubjectStudentCompleteVo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class OpeningReportTemplate extends BaseOfficeTemplate {

    private GpOpeningReportService gpOpeningReportService;
    private GpOpeningReportLiteratureService gpOpeningReportLiteratureService;

    @Override
    protected Map<String, Object> transformParams(SubjectStudentCompleteVo completeVo) {
        Map<String, Object> params = new HashMap<>();
        params.put("grade", completeVo.getGrade());
        params.put("subjectName", completeVo.getSubjectName());
        params.put("instituteName", completeVo.getInstituteName());
        params.put("majorName", completeVo.getMajorName());
        params.put("className", completeVo.getClassName());
        params.put("studentNo", completeVo.getStudentNo());
        params.put("studentName", completeVo.getStudentName());
        params.put("teacherName", completeVo.getTeacherName());

        GpSetting setting = getSetting(completeVo);
        if(setting != null && setting.getOpeningReportTime() != null) {
            params.put("openingReportTime", DateFormatUtils.format(setting.getOpeningReportTime(), "yyyy-MM-dd"));
        }

        GpOpeningReport openingReport = gpOpeningReportService.getBySubjectStudentId(completeVo.getSubjectStudentId());
        if(openingReport != null) {
            params.put("background", openingReport.getBackground());
            params.put("content", openingReport.getContent());
            params.put("method", openingReport.getMethod());
            params.put("plan", openingReport.getPlan());

            params.put("approveSuggest", openingReport.getApproveSuggest());

            setOpeningReportLiterature(params, openingReport.getId());
        }

        return params;
    }

    /**
     * 设置文献清单数据
     * @param params 参数
     * @param openingReportId 开题报告Id
     */
    private void setOpeningReportLiterature(Map<String, Object> params, Long openingReportId) {
        List<GpOpeningReportLiterature> openingReportLiteratures = gpOpeningReportLiteratureService.findByOpeningReportIdList(openingReportId);

        List<TextRenderData> textRenderDatas = new ArrayList<>();
        openingReportLiteratures.forEach(ml -> textRenderDatas.add(new TextRenderData(ml.getDisplay())));
        NumbericRenderData literatureList = new NumbericRenderData(NumbericRenderData.FMT_DECIMAL, textRenderDatas);

        params.put("literatureList", literatureList);
    }

    @Override
    protected FileName.Mapping fileMapping() {
        return FileName.Mapping.OPENING_REPORT;
    }

}

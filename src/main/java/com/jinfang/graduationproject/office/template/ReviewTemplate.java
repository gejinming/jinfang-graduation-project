package com.jinfang.graduationproject.office.template;

import com.deepoove.poi.data.NumbericRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.jinfang.graduationproject.domain.GpReview;
import com.jinfang.graduationproject.domain.GpReviewLiterature;
import com.jinfang.graduationproject.domain.GpSetting;
import com.jinfang.graduationproject.office.BaseOfficeTemplate;
import com.jinfang.graduationproject.office.FileName;
import com.jinfang.graduationproject.service.GpReviewLiteratureService;
import com.jinfang.graduationproject.service.GpReviewService;
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
public class ReviewTemplate extends BaseOfficeTemplate {

    private GpReviewService gpReviewService;
    private GpReviewLiteratureService gpReviewLiteratureService;

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
        if(setting != null && setting.getOverallFinishTime() != null) {
            params.put("finishTime", DateFormatUtils.format(setting.getViewFinishTime(), "yyyy-MM-dd"));
            params.put("finishTime1", DateFormatUtils.format(setting.getViewFinishTime(), "yyyy年MM月dd日"));
        }

        GpReview gpReview = gpReviewService.findBySubjectIdAndStudentId(completeVo.getSubjectId(), completeVo.getStudentId());
        if(gpReview != null) {
            params.put("content", gpReview.getContent());

            params.put("approveSuggest", gpReview.getApproveSuggest());

            setReviewLiterature(params, gpReview.getId());
        }

        return params;
    }

    /**
     * 设置文献清单数据
     * @param params 参数
     * @param reviewId 文献ID
     */
    private void setReviewLiterature(Map<String, Object> params, Long reviewId) {
        List<GpReviewLiterature> reviewLiteratures = gpReviewLiteratureService.findByReviewIdList(reviewId);

        List<TextRenderData> textRenderDatas = new ArrayList<>();
        reviewLiteratures.forEach(ml -> textRenderDatas.add(new TextRenderData(ml.getDisplay())));
        NumbericRenderData literatureList = new NumbericRenderData(NumbericRenderData.FMT_DECIMAL, textRenderDatas);

        params.put("literatureList", literatureList);
    }

    @Override
    protected FileName.Mapping fileMapping() {
        return FileName.Mapping.REVIEW;
    }

}

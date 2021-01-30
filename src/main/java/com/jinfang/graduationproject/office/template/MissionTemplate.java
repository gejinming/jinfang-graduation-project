package com.jinfang.graduationproject.office.template;

import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.NumbericRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import com.jinfang.graduationproject.domain.*;
import com.jinfang.graduationproject.office.BaseOfficeTemplate;
import com.jinfang.graduationproject.office.FileName;
import com.jinfang.graduationproject.service.GpMissionLiteratureService;
import com.jinfang.graduationproject.service.GpMissionPlanService;
import com.jinfang.graduationproject.service.GpMissionService;
import com.jinfang.graduationproject.service.TeacherService;
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
public class MissionTemplate extends BaseOfficeTemplate {

    private static final String TEMPLATE_ROW_DEFINE = "plans";

    private TeacherService teacherService;
    private GpMissionService gpMissionService;
    private GpMissionLiteratureService gpMissionLiteratureService;
    private GpMissionPlanService gpMissionPlanService;

    @Override
    protected Map<String, Object> transformParams(SubjectStudentCompleteVo completeVo) {
        Map<String, Object> params = new HashMap<>();
        params.put("subjectName", completeVo.getSubjectName());
        params.put("instituteName", completeVo.getInstituteName());
        params.put("majorName", completeVo.getMajorName());
        params.put("className", completeVo.getClassName());
        params.put("studentNo", completeVo.getStudentNo());
        params.put("studentName", completeVo.getStudentName());
        params.put("teacherName", completeVo.getTeacherName());

        // 获取 学校专业负责人 名称
        CcTeacher leadTeacher = teacherService.getSchoolLeaderTeacher(completeVo.getSchoolId());
        if(leadTeacher != null) {
            params.put("leadTeacherName", leadTeacher.getName());
        }

        GpSetting setting = getSetting(completeVo);
        if (setting != null && setting.getMissionDispathTime() != null) {
            params.put("missionDispathTime", DateFormatUtils.format(setting.getMissionDispathTime(), "yyyy-MM-dd"));
            params.put("missionDispachTime", DateFormatUtils.format(setting.getMissionDispathTime(), "yyyy年MM月dd日"));
        }

        GpMission gpMission = gpMissionService.findBySubjectIdAndStudentId(completeVo.getSubjectId(), completeVo.getStudentId());
        if (gpMission != null) {
            params.put("target", gpMission.getTarget());
            params.put("content", gpMission.getContent());

            setMissionLiterature(params, gpMission.getId());

            setMissionPlan(params, gpMission.getId());
        }


        return params;
    }

    /**
     * 设置任务文献清单数据
     * @param params 参数
     * @param missionId 任务书ID
     */
    private void setMissionLiterature(Map<String, Object> params, Long missionId) {
        List<GpMissionLiterature> missionLiteratures = gpMissionLiteratureService.findByMessionId(missionId);

        List<TextRenderData> textRenderDatas = new ArrayList<>();
        missionLiteratures.forEach(ml -> textRenderDatas.add(new TextRenderData(ml.getDisplay())));
        NumbericRenderData literatureList = new NumbericRenderData(NumbericRenderData.FMT_DECIMAL, textRenderDatas);

        params.put("literatureList", literatureList);
    }

    /**
     * 设置任务计划
     * @param params 参数
     * @param missionId 任务书ID
     */
    private void setMissionPlan(Map<String, Object> params, Long missionId) {
        List<GpMissionPlan> missionPlans = gpMissionPlanService.findByMessionId(missionId);
        List<Map<String, Object>> plans = new ArrayList<>();

        missionPlans.forEach(mp -> {
            Map<String, Object> plan = new HashMap<>();
            plan.put("startTime", DateFormatUtils.format(mp.getStartTime(), "yyyy年MM月dd日"));
            plan.put("stopTime", DateFormatUtils.format(mp.getStopTime(), "yyyy年MM月dd日"));
            plan.put("content", mp.getContent());
            plans.add(plan);
        });

        params.put(TEMPLATE_ROW_DEFINE, plans);
    }

    @Override
    protected FileName.Mapping fileMapping() {
        return FileName.Mapping.MISSION;
    }

    @Override
    protected Configure getConfigure() {
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();

        return Configure.newBuilder().bind(TEMPLATE_ROW_DEFINE, policy).build();
    }
}

package com.jinfang.graduationproject.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinfang.graduationproject.constants.teacher.MissionConstants;
import com.jinfang.graduationproject.constants.teacher.SubjectStudentConstants.ApproveStatus;
import com.jinfang.graduationproject.domain.GpMission;
import com.jinfang.graduationproject.domain.GpMissionLiterature;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpMissionHistoryMapper;
import com.jinfang.graduationproject.mapper.GpMissionLiteratureHistoryMapper;
import com.jinfang.graduationproject.mapper.GpMissionMapper;
import com.jinfang.graduationproject.mapper.GpMissionPlanHistoryMapper;
import com.jinfang.graduationproject.mapper.GpSubjectLiteratureMapper;
import com.jinfang.graduationproject.service.GpMissionLiteratureService;
import com.jinfang.graduationproject.service.GpMissionPlanService;
import com.jinfang.graduationproject.service.GpMissionService;
import com.jinfang.graduationproject.util.ParameterUtil;
import com.jinfang.graduationproject.vo.student.task.GetByStuidentNoListRespVo;
import com.jinfang.graduationproject.vo.teacher.mission.QueryMissionRespVo;
import com.jinfang.graduationproject.vo.teacher.mission.QueryMissionResqVo;

import lombok.AllArgsConstructor;

/**
 * --------------------------- 任务书 (GpMissionServiceImpl)
 * --------------------------- 作者： lz 时间： 2020-08-14 23:07:27 说明：
 * ---------------------------
 */
@Slf4j
@Service
@AllArgsConstructor
public class GpMissionServiceImpl implements GpMissionService {

    private GpMissionMapper gpMissionMapper;
    private GpMissionPlanService missionPlanService;
    private GpMissionLiteratureService missionLiteratureService;
    private GpSubjectLiteratureMapper gpSubjectLiteratureMapper;
    private GpMissionHistoryMapper gpMissionHistoryMapper;
    private GpMissionPlanHistoryMapper gpMissionPlanHistoryMapper;
    private GpMissionLiteratureHistoryMapper gpMissionLiteratureHistoryMapper;

    @Override
    public int save(GpMission record) {
        if (record.getId() == null || record.getId() == 0) {
            record.setCreateDate(new Date());
            record.setSendTime(new Date());
            gpMissionMapper.add(record);
            return record.getId().intValue();
        }
        return gpMissionMapper.update(record);
    }

    @Override
    public int delete(GpMission record) {
        return gpMissionMapper.delete(record.getId());
    }

    @Override
    public int delete(List<GpMission> records) {
        for (GpMission record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public GpMission findById(Long id) {
        return gpMissionMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult page = MybatisPageHelper.findPage(pageRequest, gpMissionMapper, "findPageByName",
                setGpMission(pageRequest));
        if (page != null && page.getContent().size() > 0) {
            List<QueryMissionRespVo> resList = new ArrayList<QueryMissionRespVo>();
            for (Object data : page.getContent()) {
                QueryMissionRespVo dto = new QueryMissionRespVo();
                BeanUtils.copyProperties(data, dto);
                //判断如果没有保存过文献 则查询课题文献数据
                List<GpMissionLiterature> literatureList = missionLiteratureService.findByMessionId(dto.getId());
                if (literatureList != null && literatureList.size() > 0) {
                    //任务文献
                    dto.setLiteratureList(literatureList);
                } else {
                    //课题文献
                    dto.setSubjectLiteratureList(gpSubjectLiteratureMapper.getBySubjectIdList(dto.getSubjectId()));
                }
                dto.setPlanList(missionPlanService.findByMessionId(dto.getId()));
                resList.add(dto);
            }
            page.setContent(resList);
        }
        return page;
    }

    /**
     * 设置参数
     */
    private QueryMissionResqVo setGpMission(PageRequest pageRequest) {
        QueryMissionResqVo resq = new QueryMissionResqVo();
        String grade = ParameterUtil.getColumnFilterValue(pageRequest, "grade");
        Long teacherId = (Long) pageRequest.getExtProps().get("teacherId");
        String subjectName = ParameterUtil.getColumnFilterValue(pageRequest, "subjectName");
        String status = ParameterUtil.getColumnFilterValue(pageRequest, "status");
        resq.setGrade(grade);
        resq.setTeacherId(String.valueOf(teacherId));
        resq.setSubjectName(subjectName);
        resq.setStatus(status);
        resq.setStudentStatus(ApproveStatus.ACCEPT.getCode());
        return resq;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult addAndUpdate(QueryMissionRespVo resqVo) {
        try {
            if (resqVo == null) {
                return HttpResult.error("参数异常");
            }
            int id = save(resqVo);
            if (id > 0) {
                if (resqVo.getId() != null && resqVo.getId() > 0) {
                    // 删除任务
                    missionPlanService.deleteByMessionId(resqVo.getId());
                    // 删除文献
                    missionLiteratureService.deleteByMessionId(resqVo.getId());
                    id = resqVo.getId().intValue();
                }
                // 保存任务数据
                missionPlanService.batchMissionPlan(resqVo.getPlanList(), Long.valueOf(id));
                // 保存文献数据
                missionLiteratureService.batchMissionLiterature(resqVo.getLiteratureList(), Long.valueOf(id));
                return HttpResult.ok("成功");
            }

            return HttpResult.error("失败");
        } catch (Exception e) {
            log.error("Failed to addAndUpdate resqVo[{}]", JSON.toJSONString(resqVo), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult issue(Long id) {
        try {
            if (id == null || id == 0) {
                return HttpResult.error("参数异常");
            }
            GpMission mission = findById(id);
            if (mission == null) {
                return HttpResult.error("数据异常，当前任务不存在");
            }

            mission.setStatus(MissionConstants.SendStatus.SENT.getCode());
            int rows = save(mission);
            if (rows > 0) {
                //存储历史表
                rows = gpMissionHistoryMapper.copyInsert(id);
                if (rows <= 0) {
                    throw new RuntimeException("Inserting missionHistory by missionId[" + id + "] failed");
                }

                rows = gpMissionPlanHistoryMapper.copyInsert(id);
                if (rows <= 0) {
                    throw new RuntimeException("Inserting missionPlanHistory by missionId[" + id + "] failed");
                }

                rows = gpMissionLiteratureHistoryMapper.copyInsert(id);
                if (rows <= 0) {
                    throw new RuntimeException("Inserting missionLiteratureHistory by missionId[" + id + "] failed");
                }

                return HttpResult.ok("任务下发成功");
            }

            return HttpResult.error("任务下发失败");
        } catch (Exception e) {
            log.error("Mission[{}] dispatch failed", id, e);
            throw e;
        }
    }

    @Override
    public HttpResult getByStudentNoList(PageRequest pageRequest) {
        try {
            if (pageRequest.getExtProps().get("studentNo") == null) {
                return HttpResult.error("参数为空【studentNo】");
            }
            String[] ids = new String[2];
            ids[0] = String.valueOf(MissionConstants.SendStatus.SENT.getCode());
            ids[1] = String.valueOf(MissionConstants.SendStatus.RECEIVED.getCode());
            String studentNo = (String) pageRequest.getExtProps().get("studentNo");
            PageResult page = MybatisPageHelper.findPage(pageRequest, gpMissionMapper, "getByStuidentNoListAll",
                    ids, studentNo);
            if (page != null && page.getContent().size() > 0) {
                for (Object obj : page.getContent()) {
                    GetByStuidentNoListRespVo vo = (GetByStuidentNoListRespVo) obj;
                    vo.setPlanList(missionPlanService.findByMessionId(vo.getId()));
                    vo.setLieratureList(missionLiteratureService.findByMessionId(vo.getId()));
                }
            }
            return HttpResult.ok(page);
        } catch (Exception e) {
            return HttpResult.error("异常" + e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult receive(Long id, String studentNo) {
        try {
            if (id == null || id == 0) {
                return HttpResult.error("参数为空【id】");
            }
            GpMission mission = findById(id);
            if (mission == null) {
                return HttpResult.error("数据异常，当前任务不存在");
            }
            GpMission mi = new GpMission();
            mi.setStatus(MissionConstants.SendStatus.RECEIVED.getCode());
            mi.setReceiveTime(new Date());
            mi.setReceiveUser(studentNo);
            mi.setId(id);
            int i = gpMissionMapper.update(mi);
            if (i > 0) {
                return HttpResult.ok("接收成功");
            }
            return HttpResult.error("接收失败");
        } catch (Exception e) {
            return HttpResult.error("异常" + e.getMessage());
        }
    }

    @Override
    public GpMission findBySubjectIdAndStudentId(Long subjectId, Long studentId) {
        return gpMissionMapper.selectBySubjectIdAndStudentId(subjectId, studentId);
    }
}

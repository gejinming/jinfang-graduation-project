package com.jinfang.graduationproject.service.impl;

import com.alibaba.fastjson.JSON;
import com.jinfang.graduationproject.constants.FileBizType;
import com.jinfang.graduationproject.constants.teacher.DissertationConstants;
import com.jinfang.graduationproject.constants.teacher.SubjectStudentConstants.ApproveStatus;
import com.jinfang.graduationproject.domain.GpDissertation;
import com.jinfang.graduationproject.domain.GpDissertationScore;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpDissertationHistoryMapper;
import com.jinfang.graduationproject.mapper.GpDissertationMapper;
import com.jinfang.graduationproject.mapper.GpDissertationScoreHistoryMapper;
import com.jinfang.graduationproject.mapper.GpDissertationScoreMapper;
import com.jinfang.graduationproject.service.GpDissertationService;
import com.jinfang.graduationproject.util.ParameterUtil;
import com.jinfang.graduationproject.vo.student.dissertation.GetByStudentNoDissertationRespVo;
import com.jinfang.graduationproject.vo.teacher.guider.dissertation.FindPageByNameRespVo;
import com.jinfang.graduationproject.vo.teacher.opening.DissertationExamineRespVo;
import com.jinfang.graduationproject.vo.teacher.translate.QueryTranslateResqVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * ---------------------------
 * 毕业论文 (GpDissertationServiceImpl)
 * ---------------------------
 * 作者： lz
 * 时间：  2020-08-14 23:07:27
 * 说明：
 * ---------------------------
 */
@Slf4j
@Service
@AllArgsConstructor
public class GpDissertationServiceImpl implements GpDissertationService {

    private GpDissertationMapper gpDissertationMapper;
    private GpDissertationScoreMapper dissertationScoreMapper;
    private GpDissertationHistoryMapper gpDissertationHistoryMapper;
    private GpDissertationScoreHistoryMapper gpDissertationScoreHistoryMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(GpDissertation record) {
        if (record.getId() == null || record.getId() == 0) {
            record.setSubmitTime(new Date());
            record.setCreateDate(new Date());
            return gpDissertationMapper.add(record);
        }
        record.setModifyDate(new Date());
        gpDissertationMapper.update(record);
        return record.getId().intValue();
    }

    @Override
    public int delete(GpDissertation record) {
        return gpDissertationMapper.delete(record.getId());
    }

    @Override
    public int delete(List<GpDissertation> records) {
        for (GpDissertation record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public GpDissertation findById(Long id) {
        return gpDissertationMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult page = MybatisPageHelper.findPage(pageRequest, gpDissertationMapper, "findPageByName",
                setDissertation(pageRequest));
        if (page != null && page.getContent().size() > 0) {
            for (Object data : page.getContent()) {
                FindPageByNameRespVo dto = (FindPageByNameRespVo) data;
                dto.setScoreList(dissertationScoreMapper.findByDissertationIdList(dto.getId()));
            }
        }
        return page;
    }

    /**
     * 设置参数
     */
    private QueryTranslateResqVo setDissertation(PageRequest pageRequest) {
        QueryTranslateResqVo resq = new QueryTranslateResqVo();
        String grade = ParameterUtil.getColumnFilterValue(pageRequest, "grade");
        Long teacherId = (Long) pageRequest.getExtProps().get("teacherId");
        String subjectName = ParameterUtil.getColumnFilterValue(pageRequest, "subjectName");
        String status = ParameterUtil.getColumnFilterValue(pageRequest, "status");
        resq.setGrade(grade);
        resq.setTeacherId(String.valueOf(teacherId));
        resq.setSubjectName(subjectName);
        resq.setStatus(status);
        return resq;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult examine(DissertationExamineRespVo record) {
        if (record == null) {
            return HttpResult.error("参数异常！");
        }
        if (record.getId() == null || record.getId() == 0) {
            return HttpResult.error("参数异常！【id】为空");
        }
        int id = save(record);
        if (id > 0) {
            if (record.getScoreList() != null && record.getScoreList().size() > 0) {
                //先刪除在同步
                dissertationScoreMapper.delete((long) id);
                setScoreList(record, (long) id);
                dissertationScoreMapper.addBatch(record.getScoreList());
                gpDissertationScoreHistoryMapper.copyInsert((long) id);
            }
            gpDissertationHistoryMapper.copyInsert(Long.valueOf(id));
            return HttpResult.ok("成功");
        }
        return HttpResult.error("失败！");
    }

    //设置评分关联id
    private void setScoreList(DissertationExamineRespVo resqVo, Long id) {
        for (GpDissertationScore score : resqVo.getScoreList()) {
            score.setDissertationId(id);
        }
    }

    @Override
    public HttpResult getByStudentNoList(PageRequest pageRequest) {
        try {
            if (pageRequest.getExtProps().get("studentNo") == null) {
                return HttpResult.error("参数为空【studentNo】");
            }
            PageResult page = MybatisPageHelper.findPage(pageRequest, gpDissertationMapper, "getByStudentNoList",
                    ApproveStatus.ACCEPT.getCode(), (String) pageRequest.getExtProps().get("studentNo"));
            return HttpResult.ok(page);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error("异常" + e.getMessage());
        }
    }

    @Override
    public HttpResult add(Long id, String studentNo) {
        try {
            GpDissertation record = findById(id);
            if (record == null) {
                return HttpResult.error("数据异常！id不存在！");
            }
            GpDissertation info = new GpDissertation();
            info.setStatus(DissertationConstants.Status.WAITING.getCode());
            info.setId(id);
            info.setApproveSuggest("");
            info.setSubmitTime(new Date());
            info.setSubmitUser(studentNo);
            int i = gpDissertationMapper.update(info);
            if (i > 0) {
                return HttpResult.ok();
            }
            return HttpResult.error();
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error("异常" + e.getMessage());
        }
    }

    @Override
    public PageResult findPageInPassedByGuider(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, gpDissertationMapper, "findPageInPassedByGuider",
                pageRequest.getExtProps().get("schoolId"),
                pageRequest.getExtProps().get("teacherId"),
                pageRequest.getAttrValue("grade"),
                pageRequest.getAttrValue("subjectName"),
                pageRequest.getAttrValue("studentName"));
    }

    @Override
    public PageResult findPageInPassedByLeader(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, gpDissertationMapper, "findPageInPassedByLeader",
                pageRequest.getExtProps().get("schoolId"),
                pageRequest.getAttrValue("grade"),
                pageRequest.getAttrValue("subjectName"),
                pageRequest.getAttrValue("studentName"));
    }

    @Override
    public PageResult findPageInPassedByHeadman(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, gpDissertationMapper, "findPageInPassedByHeadman",
                pageRequest.getExtProps().get("schoolId"),
                pageRequest.getExtProps().get("teacherId"),
                pageRequest.getAttrValue("grade"),
                pageRequest.getAttrValue("subjectName"),
                pageRequest.getAttrValue("studentName"));
    }

    @Override
    public String findByFileId(Long fileId, FileBizType fileBizType) {
        if (fileId == null || fileBizType == null) {
            return null;
        }

        GetByStudentNoDissertationRespVo dissertationRespVo = null;
        if (FileBizType.DISSERTATION.getCode().equals(fileBizType.getCode())) {
            dissertationRespVo = gpDissertationMapper.selectByFileId(fileId);
        } else if (FileBizType.DISSERTATION_SIMILAR.getCode().equals(fileBizType.getCode())) {
            dissertationRespVo = gpDissertationMapper.selectBySimilarityFileId(fileId);
        }

        if (dissertationRespVo == null) {
            log.warn("Can not find by any dissertations by fileId[{}] and fileBizType[{}]",
                    fileId, fileBizType);
            return null;
        }


        return buildFileName(dissertationRespVo, fileBizType);
    }

    /**
     * 生成文件名称
     *
     * @param dissertationRespVo 论文数据
     * @param fileBizType        业务类型
     * @return 名称
     */
    private String buildFileName(GetByStudentNoDissertationRespVo dissertationRespVo, FileBizType fileBizType) {
        if (dissertationRespVo == null) {
            return null;
        }

        try {
            return dissertationRespVo.getSubjectName() + "_" + dissertationRespVo.getStudentName()
                    + "_" + fileBizType.getDesc();
        } catch (Exception e) {
            log.error("Build filename failed, dissertationRespVo:{}, fileBizType:{}",
                    JSON.toJSONString(dissertationRespVo), fileBizType, e);
            return null;
        }
    }

    @Override
    public GpDissertation findBySubjectStudentId(Long subjectId, Long studentId) {
        return gpDissertationMapper.selectBySubjectStudentId(subjectId, studentId);
    }
}

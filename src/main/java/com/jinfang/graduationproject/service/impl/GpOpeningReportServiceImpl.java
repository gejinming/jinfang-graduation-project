package com.jinfang.graduationproject.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinfang.graduationproject.constants.teacher.OpeningReportConstants;
import com.jinfang.graduationproject.constants.teacher.SubjectStudentConstants.ApproveStatus;
import com.jinfang.graduationproject.domain.GpOpeningReport;
import com.jinfang.graduationproject.domain.GpOpeningReportLiterature;
import com.jinfang.graduationproject.domain.GpOpeningReportScore;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpOpeningReportHistoryMapper;
import com.jinfang.graduationproject.mapper.GpOpeningReportLiteratureHistoryMapper;
import com.jinfang.graduationproject.mapper.GpOpeningReportLiteratureMapper;
import com.jinfang.graduationproject.mapper.GpOpeningReportMapper;
import com.jinfang.graduationproject.mapper.GpOpeningReportScoreHistoryMapper;
import com.jinfang.graduationproject.mapper.GpOpeningReportScoreMapper;
import com.jinfang.graduationproject.mapper.GpSubjectLiteratureMapper;
import com.jinfang.graduationproject.service.GpOpeningReportLiteratureService;
import com.jinfang.graduationproject.service.GpOpeningReportService;
import com.jinfang.graduationproject.util.ParameterUtil;
import com.jinfang.graduationproject.vo.student.opening.GetByStudentNoOpeningListRespVo;
import com.jinfang.graduationproject.vo.student.opening.WriteOpeningReqVo;
import com.jinfang.graduationproject.vo.teacher.mission.QueryMissionResqVo;
import com.jinfang.graduationproject.vo.teacher.opening.QueryOpeningResqVo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * --------------------------- 开题报告 (GpOpeningReportServiceImpl)
 * --------------------------- 作者： lz 时间： 2020-08-14 23:07:27 说明：
 * ---------------------------
 */
@Slf4j
@Service
@AllArgsConstructor
public class GpOpeningReportServiceImpl implements GpOpeningReportService {

    private GpOpeningReportMapper gpOpeningReportMapper;
    private GpOpeningReportLiteratureService openingReportLiteratureService;
    private GpOpeningReportLiteratureMapper openingReportLiteratureMapper;
    private GpOpeningReportScoreMapper openingReportScoreMapper;
    private GpSubjectLiteratureMapper subjectLiteratureMapper;
    private GpOpeningReportHistoryMapper gpOpeningReportHistoryMapper;
    private GpOpeningReportLiteratureHistoryMapper gpOpeningReportLiteratureHistoryMapper;
    private GpOpeningReportScoreHistoryMapper gpOpeningReportScoreHistoryMapper;

    @Override
    public int save(GpOpeningReport record) {
        if (record.getId() == null || record.getId() == 0) {
            record.setCreateDate(new Date());
            gpOpeningReportMapper.add(record);
            return record.getId().intValue();
        }
        record.setModifyDate(new Date());
        gpOpeningReportMapper.update(record);
        return record.getId().intValue();
    }

    @Override
    public int delete(GpOpeningReport record) {
        return gpOpeningReportMapper.delete(record.getId());
    }

    @Override
    public int delete(List<GpOpeningReport> records) {
        for (GpOpeningReport record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public GpOpeningReport findById(Long id) {
        return gpOpeningReportMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult page = MybatisPageHelper.findPage(pageRequest, gpOpeningReportMapper, "findPageByName",
                setOpeningReport(pageRequest));
        if (page != null && page.getContent().size() > 0) {
            for (Object data : page.getContent()) {
                QueryOpeningResqVo dto = (QueryOpeningResqVo) data;
                dto.setLiteratureList(openingReportLiteratureService.findByOpeningReportIdList(dto.getId()));
                dto.setScoreList(openingReportScoreMapper.findByOpeningReportIdList(dto.getId()));

            }
        }
        return page;
    }

    /**
     * 设置参数
     */
    private QueryMissionResqVo setOpeningReport(PageRequest pageRequest) {
        QueryMissionResqVo resq = new QueryMissionResqVo();
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
    public HttpResult examine(QueryOpeningResqVo resqVo) {
        try {
            if (resqVo == null) {
                return HttpResult.error("参数异常！");
            }
            if (resqVo.getScoreList() == null) {
                return HttpResult.error("参数异常！");
            }
            GpOpeningReport re = findById(resqVo.getId());
            if (re == null) {
                return HttpResult.error("数据异常id为空！");
            }
            resqVo.setApproveTime(new Date());
            int id = save(resqVo);
            if (id > 0) {
            	if(resqVo.getScoreList() !=null && resqVo.getScoreList().size()>0){
            		   setScoreList(resqVo, resqVo.getId());
            		   openingReportScoreMapper.delete((long)id);
                       openingReportScoreMapper.addBatch(resqVo.getScoreList());
                       gpOpeningReportScoreHistoryMapper.copyInsert((long)id);
            	}
            	gpOpeningReportLiteratureHistoryMapper.copyInsert((long)id);
            	gpOpeningReportHistoryMapper.copyInsert(Long.valueOf(id));
                return HttpResult.ok("提交成功！");
            }
            return HttpResult.error("提交失败！");
        } catch (Exception e) {
            log.error("Failed to examine by QueryOpeningResqVo[{}]", resqVo, e);
            return HttpResult.error("查看/检查异常，请稍后重试" + e.getMessage());
        }
    }

    //设置评分关联id
    private void setScoreList(QueryOpeningResqVo resqVo, Long id) {
        for (GpOpeningReportScore score : resqVo.getScoreList()) {
            score.setOpeningReportId(id);
        }
    }

    @Override
    public HttpResult getByStudentNoOpeningList(PageRequest pageRequest) {
        try {
            if (pageRequest.getExtProps().get("studentNo") == null) {
                return HttpResult.error("参数为空【studentNo】");
            }
            PageResult page = MybatisPageHelper.findPage(pageRequest, gpOpeningReportMapper,
                    "getByStudentNoOpeningList", ApproveStatus.ACCEPT.getCode(),
                    pageRequest.getExtProps().get("studentNo"));
            if (page != null && page.getContent().size() > 0) {
                for (Object obj : page.getContent()) {
                    GetByStudentNoOpeningListRespVo vo = (GetByStudentNoOpeningListRespVo) obj;
                    if (vo.getId() != null && vo.getId() > 0) {
                    	 List<GpOpeningReportLiterature> openingReportLiteratureList = openingReportLiteratureService.findByOpeningReportIdList(vo.getId());
                    	 if(openingReportLiteratureList !=null && openingReportLiteratureList.size()>0){
                    		 vo.setLiteratureList(openingReportLiteratureList);
                    	 }else{
                    		 vo.setSubjectLiteratureList(subjectLiteratureMapper.getBySubjectIdList(vo.getSubjectId()));
                    	 }
                    }else{
                    	 vo.setSubjectLiteratureList(subjectLiteratureMapper.getBySubjectIdList(vo.getSubjectId()));
                    }
                }
            }
            return HttpResult.ok(page);
        } catch (Exception e) {
            log.error("Failed to getByStudentNoOpeningList by pageRequest[{}]", pageRequest, e);
            return HttpResult.error("异常" + e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult write(WriteOpeningReqVo record) {
        try {
            int id = save(record);
            if (id > 0) {
                openingReportLiteratureMapper.deleteByOpeningReportId((long) id);

                setGpOpeningReportLiterature(record.getLiteratureList(), (long) id);

                openingReportLiteratureMapper.addBatch(record.getLiteratureList());
                return HttpResult.ok("提交成功！");
            }
            return HttpResult.error("提交失败！");
        } catch (Exception e) {
            log.error("Failed to write by record[{}]", record, e);
            return HttpResult.error("异常" + e.getMessage());
        }
    }

    /**
     * 设置文献外键id
     *
     * @param literatureList 文献列表
     * @param id             开题报告ID
     */
    private void setGpOpeningReportLiterature(List<GpOpeningReportLiterature> literatureList,
                                              Long id) {
        if (literatureList != null && literatureList.size() > 0) {
            for (GpOpeningReportLiterature record : literatureList) {
                record.setOpeningReportId(id);
            }
        }
    }

    @Override
    public HttpResult add(Long id, String studentNo) {
        try {
            GpOpeningReport record = findById(id);
            if (record == null) {
                return HttpResult.error("数据异常！id不存在！");
            }
            GpOpeningReport info = new GpOpeningReport();
            info.setStatus(OpeningReportConstants.Status.WAITING.getCode());
            info.setId(id);
            info.setApproveSuggest("");
            info.setSubmitUser(studentNo);
            info.setSubmitTime(new Date());
            int i = gpOpeningReportMapper.update(info);
            if (i > 0) {
                return HttpResult.ok();
            }
            return HttpResult.error();
        } catch (Exception e) {
            log.error("Failed to add by id[{}]", id, e);
            return HttpResult.error("异常" + e.getMessage());
        }
    }

    @Override
    public GpOpeningReport getBySubjectStudentId(Long subjectStudentId) {
        return gpOpeningReportMapper.selectBySubjectStudentId(subjectStudentId);
    }
}

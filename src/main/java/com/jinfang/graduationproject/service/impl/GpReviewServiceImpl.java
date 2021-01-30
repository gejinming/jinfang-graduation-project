package com.jinfang.graduationproject.service.impl;

import com.alibaba.fastjson.JSON;
import com.jinfang.graduationproject.constants.teacher.ReviewConstants;
import com.jinfang.graduationproject.constants.teacher.SubjectStudentConstants.ApproveStatus;
import com.jinfang.graduationproject.domain.*;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.*;
import com.jinfang.graduationproject.service.GpReviewLiteratureService;
import com.jinfang.graduationproject.service.GpReviewService;
import com.jinfang.graduationproject.util.ParameterUtil;
import com.jinfang.graduationproject.vo.student.review.GetByStudentNoReviewListRespVo;
import com.jinfang.graduationproject.vo.student.review.WriteReqVo;
import com.jinfang.graduationproject.vo.teacher.mission.QueryMissionResqVo;
import com.jinfang.graduationproject.vo.teacher.review.QueryAllResqVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * --------------------------- 文献综述 (GpReviewServiceImpl)
 * --------------------------- 作者： lz 时间： 2020-08-14 23:07:27 说明：
 * ---------------------------
 */
@Slf4j
@Service
@AllArgsConstructor
public class GpReviewServiceImpl implements GpReviewService {

    private GpReviewMapper gpReviewMapper;
    private GpReviewHistoryMapper gpReviewHistoryMapper;
    private GpReviewLiteratureMapper reviewLiteratureMapper;
    private GpReviewLiteratureHistoryMapper gpReviewLiteratureHistoryMapper;
    private GpReviewLiteratureService reviewLiteratureService;
    private GpReviewScoreMapper reviewScoreMapper;
    private GpReviewScoreHistoryMapper gpReviewScoreHistoryMapper;
    private GpSubjectLiteratureMapper subjectLiteratureMapper;


    @Override
    public int save(GpReview record) {
        if (record.getId() == null || record.getId() == 0) {
            record.setSubmitTime(new Date());
            record.setCreateDate(new Date());
            gpReviewMapper.add(record);
            return record.getId().intValue();
        }
        record.setApproveTime(new Date());
        gpReviewMapper.update(record);
        return record.getId().intValue();
    }

    @Override
    public int delete(GpReview record) {
        return gpReviewMapper.delete(record.getId());
    }

    @Override
    public int delete(List<GpReview> records) {
        for (GpReview record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public GpReview findById(Long id) {
        return gpReviewMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult page = MybatisPageHelper.findPage(pageRequest, gpReviewMapper, "findPageByName",
                setReview(pageRequest));
        if (page != null && page.getContent().size() > 0) {
            for (Object obj : page.getContent()) {
                QueryAllResqVo dto = (QueryAllResqVo) obj;
                dto.setLiteratureList(reviewLiteratureService.findByReviewIdList(dto.getId()));
                dto.setScoreList(reviewScoreMapper.findByViewIdList(dto.getId()));
            }
        }
        return page;
    }

    /**
     * 设置参数
     */
    private QueryMissionResqVo setReview(PageRequest pageRequest) {
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
    public HttpResult examine(QueryAllResqVo resqVo) {
        try {
            if (resqVo == null) {
                return HttpResult.error("参数异常！");
            }

            resqVo.setApproveTime(new Date());
            int id = save(resqVo);
            if (id > 0) {
                //批量保存评分
                if (resqVo.getScoreList() != null && resqVo.getScoreList().size() > 0) {
                    setScoreList(resqVo, resqVo.getId());
                    reviewScoreMapper.delete(resqVo.getId());
                    reviewScoreMapper.addBatch(resqVo.getScoreList());
                }
                // 保存历史
                copyAttrAndSaveHistory(resqVo);

                return HttpResult.ok("提交成功！");
            }
            return HttpResult.error("提交失败！");
        } catch (Exception e) {
            log.error("执行失败 , resqVo : {}", resqVo, e);
            throw e;
        }
    }

    //设置评分关联id
    private void setScoreList(QueryAllResqVo resqVo, Long id) {
        for (GpReviewScore score : resqVo.getScoreList()) {
            score.setViewId(id);
        }
    }

    @Override
    public HttpResult getByStudentNoReviewList(PageRequest pageRequest) {
        try {
            if (pageRequest.getExtProps().get("studentNo") == null) {
                return HttpResult.error("参数为空【studentNo】");
            }
            PageResult page = MybatisPageHelper.findPage(pageRequest, gpReviewMapper, "getByStudentNoReviewList",
                    ApproveStatus.ACCEPT.getCode(), pageRequest.getExtProps().get("studentNo"));
            if (page != null && page.getContent().size() > 0) {
                for (Object obj : page.getContent()) {
                    GetByStudentNoReviewListRespVo vo = (GetByStudentNoReviewListRespVo) obj;
                    if (vo.getId() != null && vo.getId() > 0) {
                        //如果自己文献未提交 查询课题文献数据列表
                        List<GpReviewLiterature> reviewLiteratureList = reviewLiteratureService.findByReviewIdList(vo.getId());
                        if (reviewLiteratureList != null && reviewLiteratureList.size() > 0) {
                            vo.setLiteratureList(reviewLiteratureList);
                        } else {
                            vo.setSubjectLiteratureList(subjectLiteratureMapper.getBySubjectIdList(vo.getSubjectId()));
                        }
                    } else {
                        vo.setSubjectLiteratureList(subjectLiteratureMapper.getBySubjectIdList(vo.getSubjectId()));
                    }
                }
            }
            return HttpResult.ok(page);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error("异常" + e.getMessage());
        }
    }

    @Override
    public HttpResult write(WriteReqVo reocrd) {
        try {
            int id = save(reocrd);
            if (id > 0) {
                reviewLiteratureMapper.deleteByReviewId(Long.valueOf(id));
                reviewLiteratureMapper.addBatch(setLiteratureList(reocrd.getLiteratureList(), Long.valueOf(id)));
                return HttpResult.ok("提交成功！");
            }
            return HttpResult.error("提交失败！");
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error("异常" + e.getMessage());
        }
    }

    private List<GpReviewLiterature> setLiteratureList(List<GpReviewLiterature> literatureList,
                                                       Long id) {
        if (literatureList != null && literatureList.size() > 0) {
            for (GpReviewLiterature record : literatureList) {
                record.setReviewId(id);
            }
        }
        return literatureList;
    }

    @Override
    public HttpResult add(Long id, String studentNo) {
        try {
            GpReview record = findById(id);
            if (record == null) {
                return HttpResult.error("数据异常！id不存在！");
            }
            GpReview info = new GpReview();
            info.setStatus(ReviewConstants.Status.WAITING.getCode());
            info.setApproveSuggest("");
            info.setId(id);
            info.setSubmitTime(new Date());
            info.setSubmitUser(studentNo);


            int i = gpReviewMapper.update(info);
            if (i > 0) {
                //
                return HttpResult.ok();
            }
            return HttpResult.error();
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error("异常" + e.getMessage());
        }
    }

    /**
     * 添加相关历史记录
     */
    private void copyAttrAndSaveHistory(QueryAllResqVo resqVo) {
        if (resqVo == null || resqVo.getStatus() == null ||
                resqVo.getStatus() == ReviewConstants.Status.TEMP.getCode()
                || resqVo.getStatus() == ReviewConstants.Status.WAITING.getCode()) {
            return;
        }

        // 去数据库中拿到该课题选题ID的所有属性数据
        GpReview source = gpReviewMapper.findById(resqVo.getId());
        if (source == null) {
            throw new RuntimeException("数据[" + resqVo.getId() + "]未找到相关记录");
        }

        source.setStatus(resqVo.getStatus());
        source.setApproveTime(new Date());

        // 因为考虑是 当前线程事务，修改的状态和审批时间还没生效，顾需要从新设置下值
        if (resqVo.getStatus() == ReviewConstants.Status.PASS.getCode()) {
            source.setApproveSuggest(resqVo.getApproveSuggest());


        }

        Long reviewHistoryId = addHistoryRecord(source);

        addLiteratureHistoryListRecord(resqVo.getLiteratureList(), reviewHistoryId);

        addScoreHistoryListRecord(resqVo.getScoreList(), reviewHistoryId);

    }

    private Long addHistoryRecord(GpReview gpReview) {
        GpReviewHistory gpReviewHistory = new GpReviewHistory();
        BeanUtils.copyProperties(gpReview, gpReviewHistory, "id");
        gpReviewHistory.setReviewId(gpReview.getId());

        int rows = gpReviewHistoryMapper.add(gpReviewHistory);
        if (rows <= 0) {
            throw new RuntimeException("插入数据[" + JSON.toJSONString(gpReview) + "]失败");
        }

        return gpReviewHistory.getId();
    }

    private void addLiteratureHistoryListRecord(List<GpReviewLiterature> literatureList, Long reviewHistoryId) {
        if(CollectionUtils.isEmpty(literatureList)) {
            return;
        }

        List<GpReviewLiteratureHistory> list = new ArrayList<>();
        for(GpReviewLiterature gpReviewLiterature : literatureList) {
            GpReviewLiteratureHistory gpReviewLiteratureHistory = new GpReviewLiteratureHistory();
            BeanUtils.copyProperties(gpReviewLiterature, gpReviewLiteratureHistory, "id");
            gpReviewLiteratureHistory.setReviewHistoryId(reviewHistoryId);
            list.add(gpReviewLiteratureHistory);
        }

        gpReviewLiteratureHistoryMapper.batchInsert(list);
    }

    private void addScoreHistoryListRecord(List<GpReviewScore> scoreList, Long reviewHistoryId) {
        if(CollectionUtils.isEmpty(scoreList)) {
            return;
        }

        List<GpReviewScoreHistory> list = new ArrayList<>();
        for(GpReviewScore gpReviewScore : scoreList) {
            GpReviewScoreHistory gpReviewScoreHistory = new GpReviewScoreHistory();
            BeanUtils.copyProperties(gpReviewScore, gpReviewScoreHistory, "id");
            gpReviewScoreHistory.setReviewHistoryId(reviewHistoryId);
            list.add(gpReviewScoreHistory);
        }

        gpReviewScoreHistoryMapper.batchInsert(list);
    }

    @Override
    public GpReview findBySubjectIdAndStudentId(Long subjectId, Long studentId) {
        return gpReviewMapper.selectBySubjectIdAndStudentId(subjectId, studentId);
    }
}

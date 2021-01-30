package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.domain.GpWorkRecord;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpWorkRecordMapper;
import com.jinfang.graduationproject.service.WorkRecordService;
import com.jinfang.graduationproject.vo.record.WorkRecordAddReq;
import com.jinfang.graduationproject.vo.record.WorkRecordPageRes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class WorkRecordServiceImpl implements WorkRecordService {

    private GpWorkRecordMapper gpWorkRecordMapper;

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult page = MybatisPageHelper.findPage(pageRequest, gpWorkRecordMapper, "findPage",
        		(Long)pageRequest.getExtProps().get("teacherId"),
                pageRequest.getAttrValue("grade"), 
                pageRequest.getAttrValue("subjectName"),
                pageRequest.getAttrValue("status"));

        if (page != null && page.getContent().size() > 0) {
            for (Object data : page.getContent()) {
                WorkRecordPageRes response = (WorkRecordPageRes) data;
                response.setStatus(CollectionUtils.isEmpty(findList(response.getSubjectId(), response.getStudentId()))
                        ? 0 : 1);
            }
        }

        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult save(WorkRecordAddReq workRecordAddReq) {
        if (workRecordAddReq == null || CollectionUtils.isEmpty(workRecordAddReq.getWorkRecords())) {
            return HttpResult.error("参数为空");
        }

        for (GpWorkRecord workRecord : workRecordAddReq.getWorkRecords()) {
            workRecord.setSubjectId(workRecordAddReq.getSubjectId());
            workRecord.setStudentId(workRecordAddReq.getStudentId());
            workRecord.setOperateUser(workRecordAddReq.getOperator());
        }

        gpWorkRecordMapper.deleteBySubjectIdAndStudentId(workRecordAddReq.getSubjectId(), workRecordAddReq.getStudentId());
        int rows = gpWorkRecordMapper.batchInsert(workRecordAddReq.getWorkRecords());
        if (rows > 0) {
            return HttpResult.ok("添加成功");
        }

        return HttpResult.error("添加失败");
    }

    @Override
    public List<GpWorkRecord> findList(Long subjectId, Long studentId) {
        return gpWorkRecordMapper.selectBySubjectId(subjectId, studentId);
    }
}

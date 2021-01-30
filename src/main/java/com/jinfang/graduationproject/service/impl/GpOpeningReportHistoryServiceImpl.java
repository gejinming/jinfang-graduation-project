package com.jinfang.graduationproject.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jinfang.graduationproject.domain.CcStudent;
import com.jinfang.graduationproject.domain.GpOpeningReportHistory;
import com.jinfang.graduationproject.domain.GpSubject;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.mapper.CcStudentMapper;
import com.jinfang.graduationproject.mapper.GpOpeningReportHistoryMapper;
import com.jinfang.graduationproject.mapper.GpOpeningReportLiteratureHistoryMapper;
import com.jinfang.graduationproject.mapper.GpOpeningReportScoreHistoryMapper;
import com.jinfang.graduationproject.mapper.GpSubjectMapper;
import com.jinfang.graduationproject.service.GpOpeningReportHistoryService;
import com.jinfang.graduationproject.vo.teacher.opening.GetHistoryDetaileRespVo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 开题报告接口服务实现类
 */

@Slf4j
@Service
@AllArgsConstructor
public class GpOpeningReportHistoryServiceImpl implements GpOpeningReportHistoryService {

	private GpOpeningReportHistoryMapper gpOpeningReportHistoryMapper;
	private GpSubjectMapper gpSubjectMapper;
	private CcStudentMapper ccStudentMapper;
	private GpOpeningReportLiteratureHistoryMapper gpOpeningReportLiteratureHistoryMapper;
	private GpOpeningReportScoreHistoryMapper gpOpeningReportScoreHistoryMapper;
	
	@Override
	public HttpResult getOpeningReportIdList(Long openingReportId) {
		try {
			return HttpResult.ok(gpOpeningReportHistoryMapper.getByOpeningReportIdList(openingReportId));
		} catch (Exception e) {
			log.error("执行失败 , openingReportId : {}", openingReportId, e);
			return HttpResult.error(e.getMessage());
		}
	}

	@Override
	public HttpResult getHistoryDetail(Long id) {
		try {
			GpOpeningReportHistory gpOpeningReportHistory = gpOpeningReportHistoryMapper.findById(id);
			if(gpOpeningReportHistory==null){
				return HttpResult.error("未查询到当前记录！");
			}
			GetHistoryDetaileRespVo respVo = new GetHistoryDetaileRespVo();
			BeanUtils.copyProperties(gpOpeningReportHistory, respVo);
			GpSubject subject = gpSubjectMapper.findById(gpOpeningReportHistory.getSubjectId());
			if (subject != null) {
				respVo.setSubjectName(subject.getName());
			}
			CcStudent stu= ccStudentMapper.findById(gpOpeningReportHistory.getStudentId());
			if(stu !=null){
				respVo.setStudentName(stu.getName());
			}
			//综合评分
			respVo.setScoreTypeOptionList(gpOpeningReportScoreHistoryMapper.findByOpeningReportIdList(gpOpeningReportHistory.getOpeningReportId()));
			//文献集合
			respVo.setLiteratureList(gpOpeningReportLiteratureHistoryMapper.findByOpeningReportHistoryId(gpOpeningReportHistory.getOpeningReportId()));
			return HttpResult.ok(respVo);
		} catch (Exception e) {
			log.error("执行失败 , id : {}", id, e);
			return HttpResult.error(e.getMessage());
		}
	}

}

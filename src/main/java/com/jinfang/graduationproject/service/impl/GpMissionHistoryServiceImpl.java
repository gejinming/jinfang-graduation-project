package com.jinfang.graduationproject.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jinfang.graduationproject.domain.CcStudent;
import com.jinfang.graduationproject.domain.GpMissionHistory;
import com.jinfang.graduationproject.domain.GpSubject;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.mapper.CcStudentMapper;
import com.jinfang.graduationproject.mapper.GpMissionHistoryMapper;
import com.jinfang.graduationproject.mapper.GpMissionLiteratureHistoryMapper;
import com.jinfang.graduationproject.mapper.GpMissionPlanHistoryMapper;
import com.jinfang.graduationproject.mapper.GpSubjectMapper;
import com.jinfang.graduationproject.service.GpMissionHistoryService;
import com.jinfang.graduationproject.vo.teacher.mission.GetHistorydetaileRespVo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 任务历史服务接口实现类
 */
@Slf4j
@Service
@AllArgsConstructor
public class GpMissionHistoryServiceImpl implements GpMissionHistoryService {

	private GpMissionHistoryMapper gpMissionHistoryMapper;
	private GpSubjectMapper gpSubjectMapper;
	private GpMissionLiteratureHistoryMapper gpMissionLiteratureHistoryMapper;
	private GpMissionPlanHistoryMapper gpMissionPlanHistoryMapper;
	private CcStudentMapper ccStudentMapper;

	@Override
	public HttpResult findByMissionIdList(Long missionId) {
		try {
			return HttpResult.ok(gpMissionHistoryMapper.findByMissionIdList(missionId));
		} catch (Exception e) {
			log.error("执行失败 , missionId : {}", missionId, e);
			return HttpResult.error(e.getMessage());
		}
	}

	@Override
	public HttpResult getHistoryDetail(Long id) {
		try {
			GpMissionHistory history = gpMissionHistoryMapper.findById(id);
			if (history == null) {
				return HttpResult.error("未查询到当前记录！");
			}
			GetHistorydetaileRespVo respVo = new GetHistorydetaileRespVo();
			BeanUtils.copyProperties(history, respVo);
			GpSubject subject =gpSubjectMapper.findById(history.getSubjectId());
			if(subject !=null){
				respVo.setSubjectName(subject.getName());
			}
			CcStudent student = ccStudentMapper.findById(history.getStudentId());
			if(student !=null){
				respVo.setStudentName(student.getName());
			}
			respVo.setLiteratureList(
					gpMissionLiteratureHistoryMapper.getByMissionHistoryIdList(history.getMissionId()));
			respVo.setMissionPlanList(gpMissionPlanHistoryMapper.findByMessionHistoryIdList(history.getMissionId()));
			return HttpResult.ok(respVo);
		} catch (Exception e) {
			log.error("执行失败 , id : {}", id, e);
			return HttpResult.error(e.getMessage());
		}
	}

}

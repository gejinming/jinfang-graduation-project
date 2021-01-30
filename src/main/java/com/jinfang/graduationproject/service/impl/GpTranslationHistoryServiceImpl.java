package com.jinfang.graduationproject.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jinfang.graduationproject.domain.CcStudent;
import com.jinfang.graduationproject.domain.GpSubject;
import com.jinfang.graduationproject.domain.GpTranslationHistory;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.mapper.CcStudentMapper;
import com.jinfang.graduationproject.mapper.GpSubjectMapper;
import com.jinfang.graduationproject.mapper.GpTranslationHistoryMapper;
import com.jinfang.graduationproject.service.GpTranslationHistoryService;
import com.jinfang.graduationproject.vo.teacher.translate.GetHistoryDetaileRespVo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 外文翻译接口实现类
 */
@Slf4j
@Service
@AllArgsConstructor
public class GpTranslationHistoryServiceImpl implements GpTranslationHistoryService {

	private GpTranslationHistoryMapper gpTranslationHistoryMapper;
	private GpSubjectMapper gpSubjectMapper;
	private CcStudentMapper ccStudentMapper;

	@Override
	public HttpResult getByTranslationIdList(Long translationId) {
		try {
			return HttpResult.ok(gpTranslationHistoryMapper.getByTranslationIdList(translationId));
		} catch (Exception e) {
			log.error("执行失败 , translationId : {}", translationId, e);
			return HttpResult.error(e.getMessage());
		}
	}

	@Override
	public HttpResult getHistoryDetail(Long id) {
		try {
			GpTranslationHistory gpTranslationHistory = gpTranslationHistoryMapper.findById(id);
			if(gpTranslationHistory==null){
				return HttpResult.error("未查询到当前记录！");
			}
			GetHistoryDetaileRespVo respVo = new GetHistoryDetaileRespVo();
			BeanUtils.copyProperties(gpTranslationHistory, respVo);
			GpSubject subject = gpSubjectMapper.findById(gpTranslationHistory.getSubjectId());
			if (subject != null) {
				respVo.setSubjectName(subject.getName());
			}
			CcStudent stu= ccStudentMapper.findById(gpTranslationHistory.getStudentId());
			if(stu !=null){
				respVo.setStudentName(stu.getName());
			}
			return HttpResult.ok(respVo);
		} catch (Exception e) {
			log.error("执行失败 , openingReportId : {}", id, e);
			return HttpResult.error(e.getMessage());
		}
	}

}

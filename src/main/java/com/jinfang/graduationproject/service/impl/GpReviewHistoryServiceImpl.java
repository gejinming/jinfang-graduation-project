package com.jinfang.graduationproject.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jinfang.graduationproject.domain.CcStudent;
import com.jinfang.graduationproject.domain.GpReviewHistory;
import com.jinfang.graduationproject.domain.GpSubject;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.mapper.CcStudentMapper;
import com.jinfang.graduationproject.mapper.GpReviewHistoryMapper;
import com.jinfang.graduationproject.mapper.GpReviewLiteratureHistoryMapper;
import com.jinfang.graduationproject.mapper.GpReviewScoreHistoryMapper;
import com.jinfang.graduationproject.mapper.GpSubjectMapper;
import com.jinfang.graduationproject.service.GpReviewHistoryService;
import com.jinfang.graduationproject.vo.teacher.guider.review.GetHistorydetaileRespVo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class GpReviewHistoryServiceImpl implements GpReviewHistoryService {

	private GpReviewHistoryMapper gpReviewHistoryMapper;
	private GpSubjectMapper gpSubjectMapper;
	private GpReviewLiteratureHistoryMapper gpReviewLiteratureHistoryMapper;
	private CcStudentMapper ccStudentMapper;
	private GpReviewScoreHistoryMapper gpReviewScoreHistoryMapper;

	@Override
	public HttpResult getReviewIdList(Long reviewId) {
		try {
			return HttpResult.ok(gpReviewHistoryMapper.getReviewIdList(reviewId));
		} catch (Exception e) {
			log.error("执行失败 , reviewId : {}", reviewId, e);
			return HttpResult.error(e.getMessage());
		}
	}

	@Override
	public HttpResult getHistoryDetail(Long id) {
		try {
			GpReviewHistory history = gpReviewHistoryMapper.findById(id);
			if (history == null) {
				return HttpResult.error();
			}
			GetHistorydetaileRespVo respVo = new GetHistorydetaileRespVo();
			BeanUtils.copyProperties(history, respVo);
			GpSubject subject = gpSubjectMapper.findById(history.getSubjectId());
			if (subject != null) {
				respVo.setSubjectName(subject.getName());
			}
			CcStudent stu= ccStudentMapper.findById(respVo.getStudentId());
			if(stu !=null){
				respVo.setStudentName(stu.getName());
			}
			respVo.setScoreTypeOptionList(gpReviewScoreHistoryMapper.findByReviewHistoryIdList(history.getId()));
			respVo.setLiteratureList(gpReviewLiteratureHistoryMapper.findByReviewHistoryId(history.getId()));
			return HttpResult.ok(respVo);
		} catch (Exception e) {
			log.error("执行失败 , id : {}", id, e);
			return HttpResult.error(e.getMessage());
		}

	}

}

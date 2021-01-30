package com.jinfang.graduationproject.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinfang.graduationproject.constants.teacher.SubjectStudentConstants.ApproveStatus;
import com.jinfang.graduationproject.constants.teacher.TranslationConstants;
import com.jinfang.graduationproject.domain.GpTranslation;
import com.jinfang.graduationproject.domain.GpTranslationScore;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpTranslationHistoryMapper;
import com.jinfang.graduationproject.mapper.GpTranslationMapper;
import com.jinfang.graduationproject.mapper.GpTranslationScoreHistoryMapper;
import com.jinfang.graduationproject.mapper.GpTranslationScoreMapper;
import com.jinfang.graduationproject.service.GpTranslationService;
import com.jinfang.graduationproject.util.ParameterUtil;
import com.jinfang.graduationproject.vo.teacher.translate.ExamineResqVo;
import com.jinfang.graduationproject.vo.teacher.translate.QueryTranslateRespVo;
import com.jinfang.graduationproject.vo.teacher.translate.QueryTranslateResqVo;

import lombok.AllArgsConstructor;

/**
 * --------------------------- 外文翻译 (GpTranslationServiceImpl)
 * --------------------------- 作者： lz 时间： 2020-08-14 23:07:27 说明：
 * ---------------------------
 */
@Service
@AllArgsConstructor
public class GpTranslationServiceImpl implements GpTranslationService {

	private GpTranslationMapper gpTranslationMapper;
	private GpTranslationScoreMapper translationScoreMapper;
	private GpTranslationHistoryMapper gpTranslationHistoryMapper;
	private GpTranslationScoreHistoryMapper gpTranslationScoreHistoryMapper;
	

	@Override
	public int save(GpTranslation record) {
		if (record.getId() == null || record.getId() == 0) {
			record.setSubmitTime(new Date());
			record.setCreateDate(new Date());
			gpTranslationMapper.add(record);
			return record.getId().intValue();
		}
		record.setModifyDate(new Date());
		gpTranslationMapper.update(record);
		return record.getId().intValue();
	}

	@Override
	public int delete(GpTranslation record) {
		return gpTranslationMapper.delete(record.getId());
	}

	@Override
	public int delete(List<GpTranslation> records) {
		for (GpTranslation record : records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public GpTranslation findById(Long id) {
		return gpTranslationMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		PageResult page = MybatisPageHelper.findPage(pageRequest, gpTranslationMapper, "findPageByName",
				setTranslation(pageRequest));
		if (page != null && page.getContent().size() > 0) {
			for (Object data : page.getContent()) {
				QueryTranslateRespVo dto = (QueryTranslateRespVo)data;
				dto.setScoreList(translationScoreMapper.findByTranslationIdList(dto.getId()));
			}
		}
		return page;
	}

	/**
	 * 设置参数
	 */
	private QueryTranslateResqVo setTranslation(PageRequest pageRequest) {
		QueryTranslateResqVo resq = new QueryTranslateResqVo();
		String grade = ParameterUtil.getColumnFilterValue(pageRequest, "grade");
		Long teacherId =(Long)pageRequest.getExtProps().get("teacherId");
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
	public HttpResult examine(ExamineResqVo resqVo) {
		if (resqVo == null) {
			return HttpResult.error("参数异常");
		}
		if(resqVo.getTranslationScoreList()==null){
			return HttpResult.error("参数异常");
		}
		resqVo.setApproveTime(new Date());
		int id = save(resqVo);
		if (id > 0) {
			if(resqVo.getTranslationScoreList() !=null && resqVo.getTranslationScoreList().size()>0){
				setScoreList(resqVo,resqVo.getId());
				translationScoreMapper.addBatch(resqVo.getTranslationScoreList());
				gpTranslationScoreHistoryMapper.copyTranslationIdScoreInsert(resqVo.getId());
			}
			//保存历史表
			gpTranslationHistoryMapper.copyTranslationInsert(resqVo.getId());
			return HttpResult.ok("提交成功");
		}
		return HttpResult.error("提交失败");
	}

	//设置评分关联id
	private void setScoreList(ExamineResqVo resqVo,Long id){
		for(GpTranslationScore score : resqVo.getTranslationScoreList()){
			score.setTranslationId(id);
		}
	}
	
	@Override
	public HttpResult getByStudentNoTranslationList(PageRequest pageRequest) {
		try {
			if (pageRequest.getExtProps().get("studentNo") == null) {
				return HttpResult.error("参数为空【studentNo】");
			}
			PageResult page = MybatisPageHelper.findPage(pageRequest, gpTranslationMapper, "getByStudentNoTranslationList",
					ApproveStatus.ACCEPT.getCode(), pageRequest.getExtProps().get("studentNo"));
			return HttpResult.ok(page);
		} catch (Exception e) {
			e.printStackTrace();
			return HttpResult.error("异常" + e.getMessage());
		}
	}

	@Override
	public HttpResult write(GpTranslation reocrd) {
		try {
			int id = save(reocrd);
			if(id>0){
				return HttpResult.ok("提交成功！");
			}
			return HttpResult.error("提交失败！");
		} catch (Exception e) {
			e.printStackTrace();
			return HttpResult.error("异常" + e.getMessage());
		}
	}

	@Override
	public HttpResult add(Long id, String studentNo) {
		try {
			GpTranslation record = findById(id);
			if(record==null){
				return HttpResult.error("数据异常！id不存在！");
			}
			GpTranslation info = new GpTranslation();
			info.setStatus(TranslationConstants.Status.WAITING.getCode());
			info.setId(id);
			info.setApproveSuggest("");
			info.setSubmitTime(new Date());
			info.setSubmitUser(studentNo);
			int i = gpTranslationMapper.update(info);
			if(i>0){
				return HttpResult.ok();
			}
			return HttpResult.error();
		} catch (Exception e) {
			e.printStackTrace();
			return HttpResult.error("异常" + e.getMessage());
		}
	}

	@Override
	public GpTranslation findBySubjectIdAndStudentId(Long subjectId, Long studentId) {
		return gpTranslationMapper.selectBySubjectIdAndStudentId(subjectId, studentId);
	}
}

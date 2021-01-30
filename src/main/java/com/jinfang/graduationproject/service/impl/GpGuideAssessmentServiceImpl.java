
    /**
    * @Description: TODO(用一句话描述该文件做什么)
    * @author lz
    * @date 2020年8月27日
    * @version V1.0  
    */
    
package com.jinfang.graduationproject.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jinfang.graduationproject.constants.teacher.AssessmentConstants;
import com.jinfang.graduationproject.constants.teacher.SubjectStudentConstants.ApproveStatus;
import com.jinfang.graduationproject.domain.GpGuideAssessment;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.MybatisPageHelper;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.mapper.GpGuideAssessmentMapper;
import com.jinfang.graduationproject.service.GpGuideAssessmentService;
import com.jinfang.graduationproject.util.ParameterUtil;
import com.jinfang.graduationproject.vo.teacher.translate.QueryTranslateResqVo;

import lombok.AllArgsConstructor;

/**
    * @Description: TODO(指导教师学生评语表)
    * @author lz
    * @date 2020年8月27日
    *
    */
@Service
@AllArgsConstructor
public class GpGuideAssessmentServiceImpl implements GpGuideAssessmentService {
	private GpGuideAssessmentMapper gpGuideAssessmentMapper;

	@Override
	public int save(GpGuideAssessment record) {
		if (record.getId() == null || record.getId() == 0) {
			record.setCreateDate(new Date());
			return gpGuideAssessmentMapper.add(record);
		}
		record.setModifyDate(new Date());
		return gpGuideAssessmentMapper.update(record);
	}

	@Override
	public int delete(GpGuideAssessment record) {
		return gpGuideAssessmentMapper.delete(record.getId());
	}

	@Override
	public int delete(List<GpGuideAssessment> records) {
		for (GpGuideAssessment record : records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public GpGuideAssessment findById(Long id) {
		return gpGuideAssessmentMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, gpGuideAssessmentMapper,"findPageByName",
				setAssessment(pageRequest));
	}

	/**
	 * 设置参数
	 */
	private QueryTranslateResqVo setAssessment(PageRequest pageRequest) {
		QueryTranslateResqVo resq = new QueryTranslateResqVo();
		String grade = ParameterUtil.getColumnFilterValue(pageRequest, "grade");
		Long teacherId = (Long)pageRequest.getExtProps().get("teacherId");
		Long schoolId = (Long)pageRequest.getExtProps().get("schoolId");
		String subjectName = ParameterUtil.getColumnFilterValue(pageRequest, "subjectName");
		String status = ParameterUtil.getColumnFilterValue(pageRequest, "status");
		resq.setGrade(grade);
		resq.setTeacherId(String.valueOf(teacherId));
		resq.setSubjectName(subjectName);
		resq.setStatus(status);
		resq.setStudentStatus(ApproveStatus.ACCEPT.getCode());
		resq.setSchoolId(schoolId);
		return resq;
	}


	@Override
	public HttpResult add(GpGuideAssessment record) {
		if (record == null) {
			return HttpResult.error("参数异常！");
		}
		record.setStatus(AssessmentConstants.Status.YES_FILL_IN.getCode());
		int id = save(record);
		if (id > 0) {
			return HttpResult.ok("成功");
		}
		return HttpResult.error("失败！");
	}

	@Override
	public GpGuideAssessment findBySubjectIdAndStudentId(Long subjectId, Long studentId) {
		return gpGuideAssessmentMapper.selectBySubjectIdAndStudentId(subjectId, studentId);
	}
}

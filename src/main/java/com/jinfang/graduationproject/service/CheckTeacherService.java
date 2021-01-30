package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.CcStudent;
import com.jinfang.graduationproject.domain.GpCheckTeacher;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;

import java.util.List;

public interface CheckTeacherService extends CurdService<GpCheckTeacher> {

	PageResult findPage(PageRequest pageRequest);

	/**
	 * 根据所属专业和教师姓名查询教师列表信息
	 * @param teacherName 教师姓名
	 * @param majorName 所属专业
	 * @return 结果
	 */
	List<CcStudent> findTeachers(String teacherName, String majorName);

	/**
     * 拉入教师
	 * @param defenseGroupName 组别名称
	 * @param teacherIds 教师ID数组，逗号隔开
	 * @return 处理结果
	 */
	HttpResult joinTeacher(String defenseGroupName, String teacherIds);

}

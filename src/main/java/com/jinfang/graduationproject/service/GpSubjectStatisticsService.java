package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.GpSubjectStatistics;

/**
 * --------------------------- 学生选题统计表（更新） (GpSubjectStatisticsService)
 * --------------------------- 作者： lz 时间： 2020-08-14 23:07:27 说明：
 * ---------------------------
 */
public interface GpSubjectStatisticsService extends CurdService<GpSubjectStatistics> {

	/**
	 * 根据课题id获取 课题统计数据
	 */
	GpSubjectStatistics findBySubjectId(Long subjectId);

}

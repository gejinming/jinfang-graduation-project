package com.jinfang.graduationproject.service;

import java.util.List;

import com.jinfang.graduationproject.domain.GpSubjectLiterature;

/**
 * ---------------------------
 * 课题文献关系一对多） (GpSubjectLiteratureService)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-14 23:07:27
 * 说明：
 * ---------------------------
 */
public interface GpSubjectLiteratureService extends CurdService<GpSubjectLiterature> {

    /**
     * 根据课题id获取文献列表
     */
    List<GpSubjectLiterature> getBySubjectIdList(Long subjectId);

    /**
     * 批量保存
     *
     * @param list 课题文献列表
     * @param subjectId             课题ID
     */
    int batchSave(List<GpSubjectLiterature> list, Long subjectId);

    /**
     * 根据课题ID删除所有课题文献
     *
     * @param subjectId 主题ID
     * @return 返回行数
     */
    int deleteBySubjectId(Long subjectId);


}

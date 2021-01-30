package com.jinfang.graduationproject.mapper;

import com.jinfang.graduationproject.domain.GpSubjectLiterature;

import java.util.List;

/**
 * ---------------------------
 * 课题文献关系一对多） (GpSubjectLiteratureMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-14 23:07:27
 * 说明：
 * ---------------------------
 */
public interface GpSubjectLiteratureMapper {

    /**
     * 添加课题文献关系一对多）
     *
     * @param record
     * @return
     */
    int add(GpSubjectLiterature record);

    /**
     * 删除课题文献关系一对多）
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 删除 根据课题id删除
     *
     * @param id
     * @return
     */
    int deleteBySubjectId(Long id);

    /**
     * 修改课题文献关系一对多）
     *
     * @param record
     * @return
     */
    int update(GpSubjectLiterature record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpSubjectLiterature findById(Long id);

    /**
     * 基础分页查询
     *
     */
    List<GpSubjectLiterature> findPage();

    /**
     * 根据课题id获取文献列表
     *
     * @param subjectId
     * @return
     */
    List<GpSubjectLiterature> getBySubjectIdList(Long subjectId);

    int batchInsert(List<GpSubjectLiterature> list);

}
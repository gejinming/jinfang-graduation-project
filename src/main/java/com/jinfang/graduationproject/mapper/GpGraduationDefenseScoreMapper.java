package com.jinfang.graduationproject.mapper;

import com.jinfang.graduationproject.domain.GpGraduationDefenseScore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ---------------------------
 * 毕业答辩评分表 (GpGraduationDefenseScoreMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-03 22:11:30
 * 说明：
 * ---------------------------
 */
public interface GpGraduationDefenseScoreMapper {

    /**
     * 添加毕业答辩评分表
     *
     * @param record
     * @return
     */
    int add(GpGraduationDefenseScore record);

    /**
     * 删除毕业答辩评分表
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改毕业答辩评分表
     *
     * @param record
     * @return
     */
    int update(GpGraduationDefenseScore record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpGraduationDefenseScore findById(Long id);

    /**
     * 根据毕业答辩就ID获取得分列表信息
     *
     * @param graduationDefenseId 毕业答辩ID
     */
    List<GpGraduationDefenseScore> findByGraduationDefenseId(@Param("graduationDefenseId") Long graduationDefenseId);


    /**
     * 基础分页查询
     *
     * @return
     */
    List<GpGraduationDefenseScore> findPage();

    int batchInsert(List<GpGraduationDefenseScore> list);

}
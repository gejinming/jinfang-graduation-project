package com.jinfang.graduationproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinfang.graduationproject.domain.GpOpeningDefenseScore;

/**
 * ---------------------------
 * 开题答辩评分表 (GpOpeningDefenseScoreMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-03 22:11:30
 * 说明：
 * ---------------------------
 */
public interface GpOpeningDefenseScoreMapper {

    /**
     * 添加开题答辩评分表
     *
     * @param record
     * @return
     */
    int add(GpOpeningDefenseScore record);

    /**
     * 删除开题答辩评分表
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改开题答辩评分表
     *
     * @param record
     * @return
     */
    int update(GpOpeningDefenseScore record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpOpeningDefenseScore findById(Long id);

    /**
     * 根据开题答辩就ID获取得分列表信息
     * @param openingDefenseId 开题答辩就ID
     */
    List<GpOpeningDefenseScore> findByOpeningDefenseId(@Param("openingDefenseId") Long openingDefenseId);

    /**
     * 基础分页查询
     *
     * @return
     */
    List<GpOpeningDefenseScore> findPage();


    int batchInsert(List<GpOpeningDefenseScore> list);
}
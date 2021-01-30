package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpScoreRole;
import org.apache.ibatis.annotations.Param;

/**
 * ---------------------------
 * 评分角色 (GpScoreRoleMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-27 00:18:12
 * 说明：
 * ---------------------------
 */
public interface GpScoreRoleMapper {

    /**
     * 添加评分角色
     *
     * @param record
     * @return
     */
    int add(GpScoreRole record);

    /**
     * 删除评分角色
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改评分角色
     *
     * @param record
     * @return
     */
    int update(GpScoreRole record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpScoreRole findById(Long id);

    /**
     * 基础分页查询
     */
    List<GpScoreRole> findPage(@Param("grade") String grade, @Param("schoolId") Long schoolId);

    /**
     * 删除之前已有的信息
     * @param record 记录
     * @return 删除行数
     */
    int deleteBygGradeComposeId(GpScoreRole record);

}
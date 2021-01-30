package com.jinfang.graduationproject.mapper;

import com.jinfang.graduationproject.domain.GpTimeControl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ---------------------------
 * 时间控制 (GpTimeControlMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-27 00:18:12
 * 说明：
 * ---------------------------
 */
public interface GpTimeControlMapper {

    /**
     * 添加时间控制
     *
     * @param record
     * @return
     */
    int add(GpTimeControl record);

    /**
     * 删除时间控制
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改时间控制
     *
     * @param record
     * @return
     */
    int update(GpTimeControl record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpTimeControl findById(Long id);

    /**
     * 基础分页查询
     */
    List<GpTimeControl> findPage(@Param("grade") String grade, @Param("schoolId") Long schoolId);

    int batchInsert(List<GpTimeControl> list);


}
package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.CcStudent;
import org.apache.ibatis.annotations.Param;

/**
 * ---------------------------
 * 学生表，表全名(certification student) (CcStudentMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-27 14:11:59
 * 说明：
 * ---------------------------
 */
public interface CcStudentMapper {

    /**
     * 添加学生表，表全名(certification student)
     *
     * @param record
     * @return
     */
    int add(CcStudent record);

    /**
     * 删除学生表，表全名(certification student)
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改学生表，表全名(certification student)
     *
     * @param record
     * @return
     */
    int update(CcStudent record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    CcStudent findById(Long id);


    /**
     * 基础分页查询
     *
     * @return
     */
    List<CcStudent> findPage();


    /**
     * 根据学号获取学生信息
     *
     * @param id 编号
     * @return 学生信息
     */
    CcStudent findInfoById(@Param("id") Long id);

    /**
     * 根据学校ID和学校ID查询总量
     *
     * @param studentId 学生ID
     * @return 数量
     */
    long selectByStudentIdInEduClass(@Param("studentId") Long studentId);
}
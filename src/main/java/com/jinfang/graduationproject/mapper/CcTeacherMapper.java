package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.CcTeacher;
import org.apache.ibatis.annotations.Param;

/**
 * ---------------------------
 * 教师表,表全名(certification teacher) (CcTeacherMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-27 14:11:59
 * 说明：
 * ---------------------------
 */
public interface CcTeacherMapper {

    /**
     * 添加教师表,表全名(certification teacher)
     *
     * @param record
     * @return
     */
    int add(CcTeacher record);

    /**
     * 删除教师表,表全名(certification teacher)
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改教师表,表全名(certification teacher)
     *
     * @param record
     * @return
     */
    int update(CcTeacher record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    CcTeacher findById(Long id);

    CcTeacher findExtById(@Param("id") Long id);

    /**
     * 基础分页查询
     *
     * @return
     */
    List<CcTeacher> findPage();

    List<CcTeacher> findList(@Param("grade") String grade,
                             @Param("schoolId") Long schoolId,
                             @Param("majorName") String majorName,
                             @Param("teacherName") String teacherName);


    /**
     * 根据教师ID和角色名称查询数量
     *
     * @param teacherId 教师ID
     * @param roleName  角色名称
     * @return 数量
     */
    long selectCountByRoleName(@Param("teacherId") Long teacherId,
                               @Param("roleName") String roleName);

    /**
     * 获取学校负责人教师信息
     *
     * @param schoolId 学习ID
     * @param roleName 专业负责人角色名称
     * @return 教师信息
     */
    CcTeacher selectLeadTeacher(@Param("schoolId") Long schoolId, @Param("roleName") String roleName);
}
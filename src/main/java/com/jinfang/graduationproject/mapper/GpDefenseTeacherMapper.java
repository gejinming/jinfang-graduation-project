package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpDefenseTeacher;
import org.apache.ibatis.annotations.Param;

/**
 * ---------------------------
 * 答辩组教师 (GpDefenseTeacherMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-27 00:18:12
 * 说明：
 * ---------------------------
 */
public interface GpDefenseTeacherMapper {

    /**
     * 添加答辩组教师
     *
     * @param record
     * @return
     */
    int add(GpDefenseTeacher record);

    /**
     * 删除答辩组教师
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改答辩组教师
     *
     * @param record
     * @return
     */
    int update(GpDefenseTeacher record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpDefenseTeacher findById(Long id);

    GpDefenseTeacher findByTeacherId(@Param("teacherId") Long teacherId);

    /**
     * 基础分页查询
     *
     * @return
     */
    List<GpDefenseTeacher> findPage(@Param("grade") String grade, @Param("groupName") String groupName,
                                    @Param("teacherName") String teacherName,
                                    @Param("schoolId") Long schoolId);


    List<GpDefenseTeacher> findPageInChecker(@Param("schoolId") Long schoolId);

    int batchInsert(List<GpDefenseTeacher> list);

    List<GpDefenseTeacher> findMembers(@Param("grade") String grade, @Param("teacherId") Long teacherId);

    /**
     * 根据届别，组名和id更新其他为非组长
     * @param grade 届别
     * @param groupName 组别
     * @param id 组长ID
     * @return 行数
     */
    int updateNotHeadman(@Param("grade") String grade,
                      @Param("groupName") String groupName,
                      @Param("id") Long id);

}
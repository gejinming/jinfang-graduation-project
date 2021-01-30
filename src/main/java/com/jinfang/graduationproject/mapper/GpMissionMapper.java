package com.jinfang.graduationproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinfang.graduationproject.domain.GpMission;
import com.jinfang.graduationproject.vo.student.task.GetByStuidentNoListRespVo;
import com.jinfang.graduationproject.vo.teacher.mission.QueryMissionResqVo;

/**
 * --------------------------- 任务书 (GpMissionMapper) ---------------------------
 * 作者： lz 时间： 2020-08-14 23:07:27 说明： ---------------------------
 */
public interface GpMissionMapper {

    /**
     * 添加任务书
     *
     * @param record
     * @return
     */
    int add(GpMission record);

    /**
     * 删除任务书
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改任务书
     *
     * @param record
     * @return
     */
    int update(GpMission record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpMission findById(Long id);

    /**
     * 基础分页查询
     *
     * @param record
     * @return
     */
    List<GpMission> findPageByName(QueryMissionResqVo record);

    /**
     * 基础分页查询
     *
     * @return
     */
    List<GpMission> findPage();

    /**
     * @Title: getByStuidentNoList @Description:
     * TODO(根据学生编号查询课题列表) @param @param studentNo @param @return 参数 @return
     * List<GpMission> 返回类型 @throws
     */
    List<GetByStuidentNoListRespVo> getByStuidentNoListAll(@Param(value = "statusList") String[] statusList,
                                                           @Param(value = "studentNo") String studentNo);

    GpMission selectBySubjectIdAndStudentId(@Param("subjectId") Long subjectId, @Param("studentId") Long studentId);

}
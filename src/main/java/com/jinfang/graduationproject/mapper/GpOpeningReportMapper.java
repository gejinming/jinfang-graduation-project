package com.jinfang.graduationproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinfang.graduationproject.domain.GpOpeningReport;
import com.jinfang.graduationproject.vo.student.opening.GetByStudentNoOpeningListRespVo;
import com.jinfang.graduationproject.vo.teacher.mission.QueryMissionResqVo;

/**
 * --------------------------- 开题报告 (GpOpeningReportMapper)
 * --------------------------- 作者： lz 时间： 2020-08-30 00:58:44 说明：
 * ---------------------------
 */
public interface GpOpeningReportMapper {

    /**
     * 添加开题报告
     *
     * @param record
     * @return
     */
    int add(GpOpeningReport record);

    /**
     * 删除开题报告
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改开题报告
     *
     * @param record
     * @return
     */
    int update(GpOpeningReport record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpOpeningReport findById(Long id);

    /**
     * 根据课题ID获取开题报告信息
     *
     * @param subjectStudentId 课题学生ID
     * @return 开题报告信息
     */
    GpOpeningReport selectBySubjectStudentId(@Param("subjectStudentId") Long subjectStudentId);

    /**
     * 基础分页查询
     *
     * @param record
     * @return
     */
    List<GpOpeningReport> findPage();

    /**
     * 基础分页查询
     *
     * @param record
     * @return
     */
    List<GetByStudentNoOpeningListRespVo> findPageByName(QueryMissionResqVo record);


    /**
     * @Title: getByStudentNoReviewList @Description:
     * TODO(根据学生编号已接收查询开题列表分页) @param @param status 状态已接收 @param @param
     * studentNo 学生编号 @param @return 参数 @return List
     * <GetByStudentNoReviewListRespVo> 返回类型 @throws
     */
    List<GetByStudentNoOpeningListRespVo> getByStudentNoOpeningList(@Param(value = "status") Integer status,
                                                                    @Param(value = "studentNo") String studentNo);
}
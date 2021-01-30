package com.jinfang.graduationproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinfang.graduationproject.domain.GpSubjectStatistics;
import com.jinfang.graduationproject.vo.student.topic.FindStudenttPageRespVo;
import com.jinfang.graduationproject.vo.teacher.topic.FindPageByNameRespVo;

/**
 * --------------------------- 学生选题统计表（更新） (GpSubjectStatisticsMapper)
 * --------------------------- 作者： lz 时间： 2020-08-14 23:07:27 说明：
 * ---------------------------
 */
public interface GpSubjectStatisticsMapper {

    /**
     * 添加学生选题统计表（更新）
     *
     * @param record
     * @return
     */
    int add(GpSubjectStatistics record);

    /**
     * 删除学生选题统计表（更新）
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改学生选题统计表（更新）
     *
     * @param record
     * @return
     */
    int update(GpSubjectStatistics record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpSubjectStatistics findById(Long id);

    /**
     * 基础分页查询
     *
     * @return
     */
    List<GpSubjectStatistics> findPage();

    /**
     * 根据课题id获取 课题统计数据
     */
    GpSubjectStatistics findBySubjectId(Long subjectId);


    /**
     * 学生选题管理(指导教师)
     *
     * @return
     */
    List<FindPageByNameRespVo> findPageByName(@Param(value = "grade") String grade,
                                              @Param(value = "subjectId") String subjectId);

    /**
     * 选题查询分页(学生)
     *
     * @return
     */
    List<FindStudenttPageRespVo> findStudentPage(@Param(value = "grade") String grade,
                                                 @Param(value = "name") String name,
                                                 @Param(value = "status") String status,
                                                 @Param(value = "studentNo") String studentNo);


    /**
     * 待处理数量+1
     *
     * @param subjectId 课题Id
     */
    int addOneWaitingAmount(@Param("subjectId") Long subjectId);

    /**
     * 待处理数量-1，不能减到负数
     *
     * @param subjectId 课题Id
     */
    int minusOneWaitingAmount(@Param("subjectId") Long subjectId);

    /**
     * 累加拒绝数，并清零等待处理数目
     *
     * @param subjectId    课题ID
     * @param rejectAmount 拒绝数
     */
    int updateRejectAmountAndClearWaiting(@Param("subjectId") Long subjectId,
                                          @Param("rejectAmount") int rejectAmount);

    /**
     * 更新接收数量，同时对 待处理数量 -1
     *
     * @param subjectId    课题ID
     * @param acceptAmount 接收数量
     */
    int updateAcceptAmount(@Param("subjectId") Long subjectId,
                           @Param("acceptAmount") int acceptAmount);

    /**
     * 更新拒绝数量，同时对 待处理数量 -1
     *
     * @param subjectId    课题ID
     * @param rejectAmount 接收数量
     */
    int updateRejectAmount(@Param("subjectId") Long subjectId,
                           @Param("rejectAmount") int rejectAmount);

}
package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpSubject;
import com.jinfang.graduationproject.dto.teacher.topic.GpSubjectListResqDto;
import com.jinfang.graduationproject.vo.teacher.leader.topic.FindExaminePageReqVo;
import org.apache.ibatis.annotations.Param;

/**
 * --------------------------- 课题表 (GpSubjectMapper) ---------------------------
 * 作者： lz 时间： 2020-08-27 00:19:38 说明： ---------------------------
 */
public interface GpSubjectMapper {

    /**
     * 添加课题表
     *
     * @param record
     * @return
     */
    int add(GpSubject record);

    /**
     * 删除课题表
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改课题表
     *
     * @param record
     * @return
     */
    int update(GpSubject record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpSubject findById(Long id);

    /**
     * 基础分页查询
     *
     * @param record
     * @return
     */
    List<GpSubject> findPageByName(GpSubject record);

    /**
     * 判断当前课题限制人数和接收人数是否达到
     */
    int computeMemberLimit(Long id);

    /**
     * 根据老师获取通过课题列表
     *
     * @param record 记录
     * @return 课题列表
     */
    List<GpSubject> findBySubmitUser(GpSubject record);

    /**
     * 专业负责人 查询审核列表
     *
     * @param reqVo 请求信息
     * @return 集合
     */
    List<GpSubjectListResqDto> selectExaminePage(FindExaminePageReqVo reqVo);

    /**
     * 根据教师ID和届别获取 学生限制总数量
     *
     * @param grade     届别
     * @param teacherId 教师ID
     * @return 总数量
     */
    Long selectCountByTeacherId(@Param("teacherId") Long teacherId,
                                @Param("grade") String grade,
                                @Param("subjectId") Long subjectId);

}
package com.jinfang.graduationproject.mapper;

import com.jinfang.graduationproject.domain.GpSubjectStudent;
import com.jinfang.graduationproject.vo.student.topic.ChoiceResultListRespVo;
import com.jinfang.graduationproject.vo.student.topic.SubjectStudentPageRes;
import com.jinfang.graduationproject.vo.teacher.topic.SeeReceiveStudentReqVo;
import com.jinfang.graduationproject.vo.teacher.topic.SeeReceiveStudentRespVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ---------------------------
 * 学生选题关系表一对多） (GpSubjectStudentMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-30 00:58:44
 * 说明：
 * ---------------------------
 */

public interface GpSubjectStudentMapper {

    /**
     * 添加学生选题关系表一对多）
     *
     * @param record
     * @return
     */
    int add(GpSubjectStudent record);

    /**
     * 删除学生选题关系表一对多）
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改学生选题关系表一对多）
     *
     * @param record
     * @return
     */
    int update(GpSubjectStudent record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    
    GpSubjectStudent findById(Long id);

    /**
     * 获取学生分页信息
     *
     * @return
     */
    List<SubjectStudentPageRes> findPage(@Param(value = "subjectName") String subjectName,
                                         @Param(value = "teacherName") String teacherName);

    /**
     * 根据subjectId查询
     *
     * @param req
     * @return
     */

    List<SeeReceiveStudentRespVo> seeReceiveStudent(SeeReceiveStudentReqVo req);

    /**
     * 根据学生ID查询所有的选题信息
     *
     * @param studentId 学生ID
     * @return map --> {key:subjectId, value: status}
     */
    List<GpSubjectStudent> selectSubjectByStudentId(@Param("studentId") Long studentId);

    /**
     * 根据课题ID和学生ID查询 状态
     *
     * @param subjectId 课题ID
     * @param studentId 学生ID
     * @return 返回状态
     */
    int selectBySubjectIdAndStudentId(@Param("subjectId") Long subjectId,
                                      @Param("studentId") Long studentId);

    /**
     * 根据课题ID获取已选题的学生姓名列表
     *
     * @param subjectId 主体ID
     * @return 返回学生姓名列表
     */
    List<String> findReceiveStudentNames(@Param("subjectId") Long subjectId);

    /**
     * 根据学生编号查询选题结果
     */
    List<ChoiceResultListRespVo> choiceResultList(String studentNo);

    /**
     * 将所有的待处理的学生个数更新为已拒绝，由于教师接收数量达到上限
     *
     * @param subjectId 课题ID
     */
    int updateWaitingToRefuse(@Param("subjectId") Long subjectId);


}
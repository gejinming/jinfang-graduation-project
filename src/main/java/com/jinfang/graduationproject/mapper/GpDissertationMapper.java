package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.vo.student.dissertation.PassedDissertationRespVo;
import org.apache.ibatis.annotations.Param;

import com.jinfang.graduationproject.domain.GpDissertation;
import com.jinfang.graduationproject.vo.student.dissertation.GetByStudentNoDissertationRespVo;
import com.jinfang.graduationproject.vo.teacher.guider.dissertation.FindPageByNameRespVo;
import com.jinfang.graduationproject.vo.teacher.translate.QueryTranslateResqVo;

/**
 * ---------------------------
 * 毕业论文 (GpDissertationMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-30 00:58:44
 * 说明：
 * ---------------------------
 */
public interface GpDissertationMapper {

    /**
     * 添加毕业论文
     *
     * @param record
     * @return
     */
    int add(GpDissertation record);

    /**
     * 删除毕业论文
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改毕业论文
     *
     * @param record
     * @return
     */
    int update(GpDissertation record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpDissertation findById(Long id);

    /**
     * 基础分页查询
     *
     * @return
     */
    List<GpDissertation> findPage();

    /**
     * 基础分页查询
     *
     * @param record
     * @return
     */
    List<FindPageByNameRespVo> findPageByName(QueryTranslateResqVo record);

    /**
     * @Title: getByStudentNoReviewList @Description:
     * TODO(根据学生编号已接收查询毕业论文列表分页) @param @param status 状态已接收 @param @param
     * studentNo 学生编号 @param @return 参数 @return
     * List<GetByStudentNoReviewListRespVo> 返回类型 @throws
     */
    List<GetByStudentNoDissertationRespVo> getByStudentNoList(@Param(value = "status") Integer status,
                                                              @Param(value = "studentNo") String studentNo);

    /**
     * 获取已通过的论文
     *
     * @param schoolId    学校ID
     * @param grade       届别
     * @param subjectName 课题名称
     * @param studentName 学生名称
     */
    List<PassedDissertationRespVo> findPageInPassedByLeader(@Param("schoolId") Long schoolId,
                                                    @Param("grade") String grade,
                                                    @Param("subjectName") String subjectName,
                                                    @Param("studentName") String studentName);

    List<PassedDissertationRespVo> findPageInPassedByGuider(@Param("schoolId") Long schoolId,
                                                            @Param("teacherId") Long teacherId,
                                                            @Param("grade") String grade,
                                                            @Param("subjectName") String subjectName,
                                                            @Param("studentName") String studentName);

    /**
     * 获取已通过的论文（答辩组组长）
     *
     * @param schoolId    学校ID
     * @param grade       届别
     * @param subjectName 课题名称
     * @param studentName 学生名称
     */
    List<PassedDissertationRespVo> findPageInPassedByHeadman(@Param("schoolId") Long schoolId,
                                                    @Param("teacherId") Long teacherId,
                                                    @Param("grade") String grade,
                                                    @Param("subjectName") String subjectName,
                                                    @Param("studentName") String studentName);

    /**
     * 根据论文文件ID获取论文详细信息
     *
     * @param fileId 论文文件ID
     * @return 论文信息
     */
    GetByStudentNoDissertationRespVo selectByFileId(@Param("fileId") Long fileId);

    /**
     * 根据论文相似度文件ID取论文详细信息
     *
     * @param similarityFileId 论文相似度文件ID
     * @return 论文信息
     */
    GetByStudentNoDissertationRespVo selectBySimilarityFileId(@Param("similarityFileId") Long similarityFileId);

    GpDissertation selectBySubjectStudentId(@Param("subjectId") Long subjectId, @Param("studentId") Long studentId);
}
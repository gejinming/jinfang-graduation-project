package com.jinfang.graduationproject.mapper;

import com.jinfang.graduationproject.domain.GpBornDocument;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ---------------------------
 * 学生文件资料 (GpBornDocumentMapper)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-11-03 10:51:56
 * 说明：
 * ---------------------------
 */
public interface GpBornDocumentMapper {

    /**
     * 添加学生文件资料
     *
     * @param record
     * @return
     */
    int add(GpBornDocument record);

    /**
     * 删除学生文件资料
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 删除
     *
     * @param subjectId 课题ID
     * @param studentId 学院ID
     * @return 影响行数
     */
    int deleteBySubjectIdAndStudentId(@Param("subjectId") Long subjectId, @Param("studentId") Long studentId);

    int deleteByGradeAndSchoolId(@Param("grade") String grade, @Param("schoolId") Long schoolId);

    /**
     * 修改学生文件资料
     *
     * @param record
     * @return
     */
    int update(GpBornDocument record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GpBornDocument findById(Long id);

    List<GpBornDocument> findBySubjectStudentId(@Param("subjectStudentId") Long subjectStudentId);

    List<GpBornDocument> findByGrade(@Param("grade") String grade);

    /**
     * 基础分页查询
     *
     * @return
     */
    List<GpBornDocument> findPage();

    int batchInsert(List<GpBornDocument> list);

    List<String> selectGradeList();
}
package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.CcTeacher;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public interface TeacherService extends CurdService<CcTeacher> {

    /**
     * 获取教师信息，包括 院校名称，专业名称等
     *
     * @param teacherId 教师ID
     * @return 教师
     */
    CcTeacher findExtInfoById(Long teacherId);

    /**
     * 根据所属专业和教师姓名查询教师列表信息
     *
     * @param grade       届别
     * @param schoolId    学校ID
     * @param majorName   所属专业
     * @param teacherName 教师姓名
     * @return 结果
     */
    List<CcTeacher> findList(String grade, Long schoolId, String majorName, String teacherName);

    /**
     * 获取用户信息（包括角色）
     *
     * @param teacherId 教师ID
     * @return 教师信息
     */
    CcTeacher getTeacherInfo(Long teacherId);

    /**
     * 是否为专业负责人
     *
     * @param teacherId 教师ID
     * @return
     */
    boolean isSchoolLeader(Long teacherId);

    /**
     * 根据学校ID查询 专业负责人，通过角色判断，如果专业 一个学校内有多个专业负责人，则按照倒叙取最近一个教师信息
     *
     * @param schoolId 学校ID
     * @return 专业负责人
     */
    CcTeacher getSchoolLeaderTeacher(Long schoolId);

    @AllArgsConstructor
    @Getter
    enum JobTitle {
        PROFESSOR(1, "教授"), ASSOCIATE_PROFESSOR(2, "副教授"), LECTURER(3, "讲师"), OTHER(4, "其他");

        private int code;
        private String des;

        public static JobTitle of(Integer code) {
            if (code == null) {
                return OTHER;
            }

            for (JobTitle jt : values()) {
                if (jt.code == code) {
                    return jt;
                }
            }

            return OTHER;
        }
    }
}

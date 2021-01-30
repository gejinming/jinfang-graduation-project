package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.GpGraduationDefense;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import com.jinfang.graduationproject.vo.defense.GraduationDefenseScoreReq;
import com.jinfang.graduationproject.vo.defense.SubjectStudentGraduationDefenseRes;

public interface GraduationDefenseService extends CurdService<GpGraduationDefense> {

    /**
     * 评分
     *
     * @param graduationDefenseScoreReq     毕业答辩评分信息
     */
    HttpResult score(GraduationDefenseScoreReq graduationDefenseScoreReq);

    /**
     * 根据学生选题ID查询相关信息
     *
     * @param subjectStudentId 学生选题ID
     */
    SubjectStudentGraduationDefenseRes findBySubjectStudentId(Long subjectStudentId, LoginUserMeta userMeta);

    /**
     * 根据毕业答辩记录ID查询
     *
     * @param graduationDefenseId 毕业答辩记录ID
     */
    SubjectStudentGraduationDefenseRes findByGraduationDefenseId(Long graduationDefenseId);
}

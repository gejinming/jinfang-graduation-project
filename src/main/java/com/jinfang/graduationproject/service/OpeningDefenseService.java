package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.GpOpeningDefense;
import com.jinfang.graduationproject.vo.defense.SubjectStudentOpeningDefenseRes;

public interface OpeningDefenseService extends CurdService<GpOpeningDefense> {

    /**
     * 根据学生选题ID查询相关信息
     *
     * @param subjectStudentId 学生选题ID
     */
    SubjectStudentOpeningDefenseRes findBySubjectStudentId(Long subjectStudentId);

    /**
     * 根据开题答辩记录ID查询
     *
     * @param openingDefenseId 开题答辩记录ID
     */
    SubjectStudentOpeningDefenseRes findByOpeningDefenseId(Long openingDefenseId);


    GpOpeningDefense findDefenseBySubjectStudentId(Long subjectStudentId);

}

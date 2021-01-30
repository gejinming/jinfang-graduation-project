package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.GpSubjectStudentScoreWeight;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.vo.score.SubjectStudentCompleteVo;

import java.util.List;

public interface SubjectStudentScoreService {

    PageResult findPage(PageRequest pageRequest);

    /**
     * 增加
     *
     * @param gpSubjectStudentScoreWeight 记录请求
     * @return 回复
     */
    HttpResult save(GpSubjectStudentScoreWeight gpSubjectStudentScoreWeight);

    List<SubjectStudentCompleteVo> findCompleteStudents(String grade, Long schoolId);

}

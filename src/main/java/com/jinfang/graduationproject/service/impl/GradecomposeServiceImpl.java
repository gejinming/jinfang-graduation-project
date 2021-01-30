package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.mapper.CcGradecomposeMapper;
import com.jinfang.graduationproject.service.GradecomposeService;
import com.jinfang.graduationproject.vo.score.GradeComposeIndicationReq;
import com.jinfang.graduationproject.vo.score.GradeComposeReq;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class GradecomposeServiceImpl implements GradecomposeService {

    private CcGradecomposeMapper ccGradecomposeMapper;

    @Override
    public List<GradeComposeReq> findBySchoolIdAndPeriod(String grade, Long schoolId, Set<String> roleNames) {
        List<GradeComposeReq> list = ccGradecomposeMapper.selectListBySchoolIdAndPeriod(grade, schoolId);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        if (CollectionUtils.isEmpty(roleNames)) {
            log.warn("Ignore data by current user can't match any role ");
            return null;
        }

        return filterByRole(list, roleNames);
    }

    /**
     * 根据当前登录人角色 和 评分角色进行过滤，如果匹配则追加相关成绩组成信息
     *
     * @param list      成绩组成集合
     * @param roleNames 当前登录人角色名称（登录人可以存在多重角色）
     * @return 过滤不符合评分角色后的集合数据
     */
    private List<GradeComposeReq> filterByRole(List<GradeComposeReq> list, Set<String> roleNames) {
        List<GradeComposeReq> finalList = new ArrayList<>();
        for (GradeComposeReq gradeComposeReq : list) {
            // 角色为空则忽略（理论上不可能为空，数据库必填项）
            if(StringUtils.isEmpty(gradeComposeReq.getRoles())) {
                continue;
            }
            //根据课程组成成绩ID获取所有的课程目标信息
            gradeComposeReq.setIndicationlist(findIndications(gradeComposeReq.getId()));
            // 该成绩下的评分角色
            String[] scoreRoles = gradeComposeReq.getRoles().split(",");
            for(String scoreRole : scoreRoles) {

                // 如果当前登录人的角色中包括 该 评分角色，则添加l
                if(roleNames.contains(scoreRole)) {
                    finalList.add(gradeComposeReq);
                }
            }
        }
        return finalList;
    }

    @Override
    public List<GradeComposeIndicationReq> findIndications(Long courseGradecomposeId) {
        return ccGradecomposeMapper.selectIndications(courseGradecomposeId);
    }
}

package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.CcGradecomposeIndicationScoreRemark;

/**
 * ---------------------------
 * 开课课程成绩组成元素课程目标关联的分数范围备注 (CcGradecomposeIndicationScoreRemarkMapper)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-30 00:58:44
 * 说明：  
 * ---------------------------
 */
public interface CcGradecomposeIndicationScoreRemarkMapper {

	/**
	 * 添加开课课程成绩组成元素课程目标关联的分数范围备注
	 * @param record
	 * @return
	 */
    int add(CcGradecomposeIndicationScoreRemark record);

    /**
     * 删除开课课程成绩组成元素课程目标关联的分数范围备注
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改开课课程成绩组成元素课程目标关联的分数范围备注
     * @param record
     * @return
     */
    int update(CcGradecomposeIndicationScoreRemark record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    CcGradecomposeIndicationScoreRemark findById(Long id);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<CcGradecomposeIndicationScoreRemark> findPage();
    
}
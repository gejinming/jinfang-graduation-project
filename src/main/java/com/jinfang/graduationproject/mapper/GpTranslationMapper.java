package com.jinfang.graduationproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinfang.graduationproject.domain.GpTranslation;
import com.jinfang.graduationproject.vo.student.translation.GetByStudentNoTranslationListRespVo;
import com.jinfang.graduationproject.vo.teacher.translate.QueryTranslateRespVo;
import com.jinfang.graduationproject.vo.teacher.translate.QueryTranslateResqVo;

/**
 * ---------------------------
 * 外文翻译 (GpTranslationMapper)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-30 00:58:44
 * 说明：  
 * ---------------------------
 */
public interface GpTranslationMapper {

	/**
	 * 添加外文翻译
	 * @param record
	 * @return
	 */
    int add(GpTranslation record);

    /**
     * 删除外文翻译
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改外文翻译
     * @param record
     * @return
     */
    int update(GpTranslation record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    GpTranslation findById(Long id);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<GpTranslation> findPage();
    
    /**
	 * 基础分页查询
	 * 
	 * @param record
	 * @return
	 */
	List<QueryTranslateRespVo> findPageByName(QueryTranslateResqVo record);		
	
	/**
	 * 
	 * @Title: getByStudentNoReviewList @Description:
	 * TODO(根据学生编号已接收查询外文翻譯列表分页) @param @param status 状态已接收 @param @param
	 * studentNo 学生编号 @param @return 参数 @return
	 * List<GetByStudentNoReviewListRespVo> 返回类型 @throws
	 */
	List<GetByStudentNoTranslationListRespVo> getByStudentNoTranslationList(@Param(value="status") Integer status,@Param(value="studentNo")  String studentNo);

	GpTranslation selectBySubjectIdAndStudentId(@Param("subjectId") Long subjectId, @Param("studentId") Long studentId);

}
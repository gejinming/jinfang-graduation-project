package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.constants.FileBizType;
import com.jinfang.graduationproject.domain.GpDissertation;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.dto.page.PageResult;
import com.jinfang.graduationproject.vo.teacher.opening.DissertationExamineRespVo;

/**
 * ---------------------------
 * 毕业论文 (GpDissertationService)
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-14 23:07:27
 * 说明：
 * ---------------------------
 */
public interface GpDissertationService extends CurdService<GpDissertation> {

    /**
     * 查看/检查
     */
    HttpResult examine(DissertationExamineRespVo resqVo);

    /**
     * @Title: getByStudentNoList @Description:
     * TODO(根据学生编号查询毕业论文数据) @param @param studentNo @param @return
     * 参数 @return HttpResult 返回类型 @throws
     */
    HttpResult getByStudentNoList(PageRequest pageRequest);


    /**
     * @Title: write @Description: TODO(提交) @param @param reocrd @param @return
     * 参数 @return HttpResult 返回类型 @throws
     */
    HttpResult add(Long id, String studentNo);

    /**
     * 扩展分页（用于查询已通过的论文记录）
     *
     * @param pageRequest 查询条件
     */
    PageResult findPageInPassedByLeader(PageRequest pageRequest);

    PageResult findPageInPassedByGuider(PageRequest pageRequest);

    /**
     * 答辩组教师查询列表
     * @param pageRequest 条件
     * @return 结果
     */
    PageResult findPageInPassedByHeadman(PageRequest pageRequest);

    /**
     * 根据文件ID和类型查询论文名称
     *
     * @param fileId      文件ID
     * @param fileBizType 类型
     * @return 论文信息
     */
    String findByFileId(Long fileId, FileBizType fileBizType);

    GpDissertation findBySubjectStudentId(Long subjectId, Long studentId);

}

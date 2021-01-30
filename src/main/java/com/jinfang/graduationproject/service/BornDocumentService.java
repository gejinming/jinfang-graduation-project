package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.vo.LoginUserMeta;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BornDocumentService {

    /**
     * 生成文档
     *
     * @param userMeta 登录用户信息，如角色，学校ID等
     * @return 处理结果
     */
    boolean bornDoc(LoginUserMeta userMeta);

    /**
     * 按照学生课题ID下载 单个学生的资料信息
     * @param subjectStudentId 学生课题ID
     * @return 下载文件流
     */
    ResponseEntity<InputStreamResource> download(Long subjectStudentId);

    /**
     * 按照届别下载资料（专业负责人专有）
     * @param grade 届别
     * @return 下载文件流
     */
    ResponseEntity<InputStreamResource> download(String grade);

    List<String> findGradeList();

}

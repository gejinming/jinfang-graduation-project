package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.domain.GpInspection;

/**
 * ---------------------------
 * 中期检查表 (GpInspectionService)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-09-09 13:46:25
 * 说明：  
 * ---------------------------
 */
public interface GpInspectionService extends CurdService<GpInspection> {

    GpInspection findBySubjectStudentId(Long subjectStudentId);
}

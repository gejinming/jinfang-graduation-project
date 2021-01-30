package com.jinfang.graduationproject.vo.record;

import com.jinfang.graduationproject.domain.GpWorkRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkRecordAddReq {

    /**
     * 课题id
     */
    private Long subjectId;

    /**
     * 学生id
     */
    private Long studentId;

    /**
     * 页面记录
     */
    private List<GpWorkRecord> workRecords = new ArrayList<>();

    /**
     * 操作账号（前端忽略此值）
     */
    private String operator;

}

package com.jinfang.graduationproject.vo.teacher.defense;

import com.jinfang.graduationproject.domain.CcTeacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefenseTeacherJoinReq implements Serializable {

    private String defenseGroupName;

    private List<CcTeacher> teachers;

    /**
     * 方便参数传递，前端无需设置此值
     */
    private String operatorUser;
}

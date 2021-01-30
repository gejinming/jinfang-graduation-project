package com.jinfang.graduationproject.vo.teacher.checker;

import com.jinfang.graduationproject.vo.LoginUserMeta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckerTeacherDispatchReq implements Serializable {

    private Long defenseTeacherId;

    /**
     * 学生ID集合
     */
    private String studentIds;

    /**
     * 方便参数传递，前端无需设置此值
     */
    private transient LoginUserMeta loginUserMeta;

}

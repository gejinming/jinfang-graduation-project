package com.jinfang.graduationproject.constants.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DefenseTeacherType {

    MEMBER(1, "答辩组教师"), HEADMAN(2, "答辩组组长");

    @Getter
    private int code;
    
    @Getter
    private String title;


}

package com.jinfang.graduationproject.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SystemRole {

    STUDENT("学生"), LEADER("专业负责人"), HEADMAN("答辩组组长"),

    DEFENSE("答辩组教师"),

    CHECKER("评阅教师"), GUIDER("指导教师");

    @Getter
    private String roleName;


}

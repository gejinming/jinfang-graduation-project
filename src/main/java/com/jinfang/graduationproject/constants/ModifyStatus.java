package com.jinfang.graduationproject.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModifyStatus {

    CLOSE(0), OPEN(1);

    private int code;
}

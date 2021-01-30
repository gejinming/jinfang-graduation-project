package com.jinfang.graduationproject.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuOpenStatus {

    OPEN(0), FROZE(1);

    private int code;
}

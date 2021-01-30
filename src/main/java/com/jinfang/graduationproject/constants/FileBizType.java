package com.jinfang.graduationproject.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum FileBizType {

    UNKNOWN("x", "未知"), DISSERTATION("1", "毕业论文"), DISSERTATION_SIMILAR("2", "毕业论文相似度");

    private String code;
    private String desc;

    public static FileBizType of(String code) {
        if (StringUtils.isEmpty(code)) {
            return UNKNOWN;
        }

        for (FileBizType bizType : FileBizType.values()) {
            if (bizType.getCode().equals(code)) {
                return bizType;
            }
        }

        return UNKNOWN;
    }
}

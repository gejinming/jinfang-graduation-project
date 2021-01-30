package com.jinfang.graduationproject.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Slf4j
public class DecodeUtil {

    public static String decode(String value) {
        if (StringUtils.isEmpty(value)) {
            return "";
        }

        try {
            return URLDecoder.decode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.warn("{} ->转码失败", value, e);
            return value;
        }
    }

    public static String encode(String value) {
        if (StringUtils.isEmpty(value)) {
            return "";
        }

        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.warn("{} -> 编码失败", value, e);
            return value;
        }
    }
}

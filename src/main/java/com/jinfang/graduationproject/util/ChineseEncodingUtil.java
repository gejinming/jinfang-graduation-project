package com.jinfang.graduationproject.util;

/**
 * 校验名字.存在一些汉字 𨰻 Unicode是28C3B1. 而java里因为string是char[],一个char只能存两个字节.所有java的string默认是utf-16编码的.需要转utf-32.再判断Unicode
 *
 * @author zhanghao3
 * @date 2018/11/29
 */
public class ChineseEncodingUtil {

    public static int length(String text) {
        int length = 0;
        for (int i = 0; i < text.length(); i++) {
            int ascii = Character.codePointAt(text, i);
            if (ascii >= 0 && ascii <= 255) {
                length++;
            } else {
                length += 2;
            }
        }
        return length;
    }

    /**
     * 判断是否为汉字
     */
    public static boolean isChinese(String text) {
        try {
            byte[] bytes = text.getBytes("UTF-32");
            String s = bytes2HexString(bytes);
            int index = 0;
            while (index < s.length()) {
                String sub = s.substring(index, index + 8);
                if (!isChineseChar(Integer.parseInt(sub, 16))) {
                    return false;
                }
                index += 8;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isChineseChar(final int codePoint) {
        // https://en.wikipedia.org/wiki/CJK_Unified_Ideographs
        //Character.UnicodeBlock 20000..E0000; HAN 太大
        return (codePoint >= 0x4e00 && codePoint <= 0x9fff) ||
                (codePoint >= 0x3400 && codePoint <= 0x4dbf) ||
                (codePoint >= 0x20000 && codePoint <= 0x2a6df) ||
                (codePoint >= 0x2a700 && codePoint <= 0x2b73f) ||
                (codePoint >= 0x2b740 && codePoint <= 0x2b81f) ||
                (codePoint >= 0x2b820 && codePoint <= 0x2ceaf) ||
                (codePoint >= 0xf900 && codePoint <= 0xfaff) ||
                (codePoint >= 0x2f800 && codePoint <= 0x2fa1f);
    }

    /**
     * byte[] 转为16进制String
     */
    private static String bytes2HexString(byte[] b) {
        StringBuilder ret = new StringBuilder();
        for (byte b1 : b) {
            String hex = Integer.toHexString(b1 & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret.append(hex.toUpperCase());
        }
        return ret.toString();
    }

}
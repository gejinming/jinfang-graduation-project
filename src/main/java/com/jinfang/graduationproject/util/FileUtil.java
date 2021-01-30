package com.jinfang.graduationproject.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileUtil {

    public static String read(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        inputStream.close();
        return bos.toString(StandardCharsets.UTF_8.name());
    }
}

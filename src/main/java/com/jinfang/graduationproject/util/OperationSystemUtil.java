package com.jinfang.graduationproject.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperationSystemUtil {

    private static final Logger logger = LoggerFactory.getLogger(OperationSystemUtil.class);

    private static final String WINDOWS_FLAG = "Windows";

    public static boolean isWindows = true;

    static {
        isWindows = isWindows();
    }

    private static boolean isWindows() {
        try {
            String osName = System.getProperty("os.name");
            logger.info("Operation system is '{}'", osName);

            // Mac OS x/Windows x
            return osName.startsWith(WINDOWS_FLAG);
        } catch (Exception e) {
            logger.warn("operation system unknown, [{}]", e.getMessage());
            return true;
        }
    }
}

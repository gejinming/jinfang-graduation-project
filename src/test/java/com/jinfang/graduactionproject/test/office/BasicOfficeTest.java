package com.jinfang.graduactionproject.test.office;

import com.deepoove.poi.config.Configure;
import com.jinfang.graduationproject.util.FileDirectoryUtil;
import com.jinfang.graduationproject.util.OfficeUtil;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.jinfang.graduationproject.util.FileDirectoryUtil.createDir;

public class BasicOfficeTest {

    private static final String SOURCE_FILE_PATH = "/Users/tenx/git/jinfang-graduation-project/src/main/resources/template/";

    private static final String TARGET_FILE_PATH = "/Users/tenx/Documents/test/jinfang/target/";

    String sourceFileName;
    String targetFileName;
    final Map<String, Object> params = new HashMap<>();
    Configure configure = Configure.createDefault();

    @Test
    public void render() throws Exception {
        OfficeUtil.renderWord(SOURCE_FILE_PATH + sourceFileName,
                getTargetName(targetFileName), configure, params);
    }

    private String getTargetName(String targetFileName) {
        FileDirectoryUtil.DirMeta dirMeta = FileDirectoryUtil.createDir(TARGET_FILE_PATH, "李四");

        return dirMeta.getPath() + File.separator + targetFileName;
    }

}

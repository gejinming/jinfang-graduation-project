package com.jinfang.graduactionproject.test;

import com.jinfang.graduationproject.constants.SystemRole;
import com.jinfang.graduationproject.util.FileDirectoryUtil;
import com.jinfang.graduationproject.util.ZipUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZipTest {

    private static final String PATH = "/Users/tenx/Documents/test/jinfang/zip/";
//    private static final String DATE_PATH = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + "/";

    private String path;

    @Before
    public void init() {
        FileDirectoryUtil.DirMeta dirMeta = FileDirectoryUtil.createDir(PATH, System.currentTimeMillis() + "");
        path = dirMeta.getPath();
    }

    @Test
    public void test() {
        List<ZipUtil.ZipFileMeta> zipFileMetas = new ArrayList<>();
        zipFileMetas.add(ZipUtil.ZipFileMeta.builder().originFileName(PATH + "a.txt").newFileName("毕业论文.txt").build());
        zipFileMetas.add(ZipUtil.ZipFileMeta.builder().originFileName(PATH + "a.pdf").newFileName("毕业论文相似度.pdf").build());

        String z1 = path + "/毕业论文.zip";

        boolean isOK = ZipUtil.zip(zipFileMetas, z1);

        Assert.assertTrue(isOK);

        String z2 = path +"/毕业论文2.zip";
        isOK = ZipUtil.zip(zipFileMetas, z2);
        Assert.assertTrue(isOK);

        String f1 = path + "/34.pdf";
        zipFileMetas = new ArrayList<>();
        zipFileMetas.add(ZipUtil.ZipFileMeta.builder().originFileName(z1).build());
        zipFileMetas.add(ZipUtil.ZipFileMeta.builder().originFileName(z2).build());
        zipFileMetas.add(ZipUtil.ZipFileMeta.builder().originFileName(f1).build());

        isOK = ZipUtil.zip(zipFileMetas, path + "/汇总.zip");
        Assert.assertTrue(isOK);
    }
}

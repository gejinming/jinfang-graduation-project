package com.jinfang.graduationproject.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class OfficeUtil {

    public static void renderWord(String sourceFilePath, String targetFilePath, Map<String, Object> params) throws Exception {
        renderWord(sourceFilePath, targetFilePath, Configure.createDefault(), params);
    }

    public static void renderWord(String sourceFilePath, String targetFilePath, Configure configure, Map<String, Object> params) throws Exception {
        renderWord(new FileInputStream(sourceFilePath), targetFilePath, configure, params);
    }

    public static void renderWord(InputStream inputStream, String targetFilePath, Configure configure, Map<String, Object> params) throws Exception {
        XWPFTemplate template = XWPFTemplate.compile(inputStream, configure).render(params);
        FileOutputStream out = new FileOutputStream(targetFilePath);
        template.write(out);
        out.flush();
        out.close();
        template.close();
    }

    public static void renderExcel(String sourceFilePath, String targetFilePath, Map<String, Object> params) throws Exception {
        TemplateExportParams templateExportParams = new TemplateExportParams(sourceFilePath);

        // 生成workbook 并导出
        Workbook workbook = ExcelExportUtil.exportExcel(templateExportParams, params);

        FileOutputStream fos = new FileOutputStream(targetFilePath);
        workbook.write(fos);
        fos.close();
    }

}

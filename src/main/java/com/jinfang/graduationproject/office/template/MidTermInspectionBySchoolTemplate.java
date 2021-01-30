package com.jinfang.graduationproject.office.template;

import com.jinfang.graduationproject.office.FileName;
import com.jinfang.graduationproject.office.vo.BornResult;
import com.jinfang.graduationproject.util.FileDirectoryUtil;
import com.jinfang.graduationproject.util.OfficeUtil;
import com.jinfang.graduationproject.util.RandomUtil;
import com.jinfang.graduationproject.vo.score.SummaryCompleteVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class MidTermInspectionBySchoolTemplate {

    @Value("${template.source}")
    private String templateSourcePath;

    @Value("${template.target}")
    private String templateTargetPath;

    public BornResult render(SummaryCompleteVo completeVo) {
        Map<String, Object> params = new HashMap<>();

        String finalFilePath = getTargetName(buildFilename(completeVo));

        boolean isSuccess = true;
        try {
            OfficeUtil.renderWord(templateSourcePath + FileName.Mapping.MID_TERM_INSPECTION_BY_SCHOOL.getSourceFileName(),
                    finalFilePath, params);

        } catch (Exception e) {
            log.error("Born document failed by completeVo[{}]", completeVo, e);
            isSuccess = false;
        }

        return BornResult.builder().fileNameMapping(FileName.Mapping.MID_TERM_INSPECTION_BY_SCHOOL).targetFilePath(finalFilePath)
                .isSuccess(isSuccess).build();
    }

    private String getTargetName(String targetFileName) {
        FileDirectoryUtil.DirMeta dirMeta = FileDirectoryUtil.createDir(templateTargetPath);

        return dirMeta.getPath() + File.separator + targetFileName;
    }

    private String buildFilename(SummaryCompleteVo completeVo) {
        return completeVo.getSchoolId() + FileName.FILE_NAME_CONNECT_CHAR
                + FileName.Mapping.MID_TERM_INSPECTION_BY_SCHOOL.getSort() + FileName.FILE_NAME_CONNECT_CHAR
                + FileName.Mapping.MID_TERM_INSPECTION_BY_SCHOOL.getEnName() + FileName.FILE_NAME_CONNECT_CHAR
                + RandomUtil.randomCode() + FileName.Mapping.MID_TERM_INSPECTION_BY_SCHOOL.getFileType();
    }

}

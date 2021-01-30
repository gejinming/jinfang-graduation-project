package com.jinfang.graduationproject.office;

import com.deepoove.poi.config.Configure;
import com.jinfang.graduationproject.domain.GpSetting;
import com.jinfang.graduationproject.office.vo.BornResult;
import com.jinfang.graduationproject.service.SettingService;
import com.jinfang.graduationproject.util.FileDirectoryUtil;
import com.jinfang.graduationproject.util.OfficeUtil;
import com.jinfang.graduationproject.util.RandomUtil;
import com.jinfang.graduationproject.vo.score.SubjectStudentCompleteVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.io.File;
import java.util.Map;

@Slf4j
public abstract class BaseOfficeTemplate {

    @Value("${template.source}")
    private String templateSourcePath;

    @Value("${template.target}")
    private String templateTargetPath;

    @Resource
    private SettingService settingService;

    protected abstract Map<String, Object> transformParams(SubjectStudentCompleteVo completeVo);

    protected Configure getConfigure() {
        return Configure.createDefault();
    }

    private String getTargetName(String targetFileName) {
        FileDirectoryUtil.DirMeta dirMeta = FileDirectoryUtil.createDir(templateTargetPath);

        return dirMeta.getPath() + File.separator + targetFileName;
    }

    public BornResult render(SubjectStudentCompleteVo completeVo) {
        boolean isSuccess = true;

        String finalFilePath = null;
        try {
            Map<String, Object> params = transformParams(completeVo);

            finalFilePath = getTargetName(buildFilename(completeVo));

            // 如果是Excel，则需要按照excel模板数据导出
            if (fileMapping().isExcel()) {
                OfficeUtil.renderExcel(templateSourcePath + fileMapping().getSourceFileName(),
                        finalFilePath, params);
            } else {
                OfficeUtil.renderWord(templateSourcePath + fileMapping().getSourceFileName(),
                        finalFilePath, getConfigure(), params);
            }


        } catch (Exception e) {
            log.error("Born document failed by completeVo[{}]", completeVo, e);
            isSuccess = false;
        }

        return BornResult.builder().fileNameMapping(fileMapping()).targetFilePath(finalFilePath)
                .isSuccess(isSuccess).build();
    }


    protected abstract FileName.Mapping fileMapping();

    private String buildFilename(SubjectStudentCompleteVo completeVo) {
        return completeVo.getSubjectId() + FileName.FILE_NAME_CONNECT_CHAR +
                completeVo.getStudentId() + FileName.FILE_NAME_CONNECT_CHAR
                + fileMapping().getSort() + FileName.FILE_NAME_CONNECT_CHAR
                + fileMapping().getEnName() + FileName.FILE_NAME_CONNECT_CHAR
                + RandomUtil.randomCode() + fileMapping().getFileType();
    }

    protected GpSetting getSetting(SubjectStudentCompleteVo completeVo) {
        return settingService.findBySchoolIdAndGrade(completeVo.getSchoolId(), completeVo.getGrade());
    }

}

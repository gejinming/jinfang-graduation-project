package com.jinfang.graduationproject.office.template;

import com.jinfang.graduationproject.office.FileName;
import com.jinfang.graduationproject.office.vo.BornResult;
import com.jinfang.graduationproject.util.CalculateUtil;
import com.jinfang.graduationproject.util.FileDirectoryUtil;
import com.jinfang.graduationproject.util.OfficeUtil;
import com.jinfang.graduationproject.util.RandomUtil;
import com.jinfang.graduationproject.vo.score.SubjectStudentCompleteVo;
import com.jinfang.graduationproject.vo.score.SummaryCompleteVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class SubjectSummaryTemplate {

    @Value("${template.source}")
    private String templateSourcePath;

    @Value("${template.target}")
    private String templateTargetPath;

    private void setSubjectType(Map<String, Object> row, int type) {
        if (type == 1) {
            row.put("type1", "√");
            row.put("type2", "");
        } else {
            row.put("type1", "");
            row.put("type2", "√");
        }
    }

    private void setSubjectNature(Map<String, Object> row, int nature) {
        row.put("nature1", "");
        row.put("nature2", "");
        row.put("nature3", "");
        row.put("nature4", "");
        row.put("nature5", "");
        if (nature == 1) {
            row.put("nature1", "√");
        } else if (nature == 2) {
            row.put("nature2", "√");
        } else if (nature == 3) {
            row.put("nature3", "√");
        } else if (nature == 4) {
            row.put("nature4", "√");
        } else if (nature == 5) {
            row.put("nature5", "√");
        }
    }

    /**
     * 设置课题来源，当来源为"工程实际"类型时，需要计数
     *
     * @param row    参数
     * @param source 来源
     * @return 本次是否为工程实际 原来，用于后面计算 百分比
     */
    private int setSubjectSource(Map<String, Object> row, int source) {
        row.put("source1", "");
        row.put("source2", "");
        row.put("source3", "");
        row.put("source4", "");
        row.put("source5", "");
        row.put("source6", "");
        if (source == 1) {
            row.put("source1", "√");
        } else if (source == 2) {
            row.put("source2", "√");
        } else if (source == 3) {
            row.put("source3", "√");
            return 1;
        } else if (source == 4) {
            row.put("source4", "√");
        } else if (source == 5) {
            row.put("source5", "√");
        } else if (source == 6) {
            row.put("source6", "√");
        }

        return 0;
    }

    private Map<String, Object> transformParams(SummaryCompleteVo completeVo) {
        Map<String, Object> params = new HashMap<>();
        params.put("instituteName", completeVo.getInstituteName());
        params.put("majorName", completeVo.getMajorName());
//        params.put("className", completeVo.getClassName());
        params.put("amount", completeVo.getSubjectStudentCompleteVos().size());

        // 工程实际数量
        int source3Amount = 0;

        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < completeVo.getSubjectStudentCompleteVos().size(); i++) {
            Map<String, Object> row = new HashMap<>();

            SubjectStudentCompleteVo subjectStudent = completeVo.getSubjectStudentCompleteVos().get(i);

            row.put("no", i + 1);
            row.put("studentName", subjectStudent.getStudentName());
            row.put("subjectName", subjectStudent.getSubjectName());
            row.put("teacherName", subjectStudent.getTeacherName());
            row.put("score", subjectStudent.getScore());

            setSubjectType(row, subjectStudent.getSubjectType());

            setSubjectNature(row, subjectStudent.getSubjectNature());

            source3Amount += setSubjectSource(row, subjectStudent.getSubjectSource());

            row.put("address", "");
            list.add(row);
        }

        params.put("percent", CalculateUtil.percert(new BigDecimal(source3Amount),
                new BigDecimal(completeVo.getSubjectStudentCompleteVos().size())));

        params.put("studentSubjectList", list);

        return params;
    }

    private String getTargetName(String targetFileName) {
        FileDirectoryUtil.DirMeta dirMeta = FileDirectoryUtil.createDir(templateTargetPath);

        return dirMeta.getPath() + File.separator + targetFileName;
    }

    public BornResult render(SummaryCompleteVo completeVo) {
        Map<String, Object> params = transformParams(completeVo);

        String finalFilePath = getTargetName(buildFilename(completeVo));

        boolean isSuccess = true;
        try {
            OfficeUtil.renderExcel(templateSourcePath + FileName.Mapping.SUBJECT_SUMMARY.getSourceFileName(),
                    finalFilePath, params);

        } catch (Exception e) {
            log.error("Born document failed by completeVo[{}]", completeVo, e);
            isSuccess = false;
        }

        return BornResult.builder().fileNameMapping(FileName.Mapping.SUBJECT_SUMMARY).targetFilePath(finalFilePath)
                .isSuccess(isSuccess).build();

    }

    private String buildFilename(SummaryCompleteVo completeVo) {
        return completeVo.getSchoolId() + FileName.FILE_NAME_CONNECT_CHAR
                + FileName.Mapping.SUBJECT_SUMMARY.getSort() + FileName.FILE_NAME_CONNECT_CHAR
                + FileName.Mapping.SUBJECT_SUMMARY.getEnName() + FileName.FILE_NAME_CONNECT_CHAR
                + RandomUtil.randomCode() + FileName.Mapping.SUBJECT_SUMMARY.getFileType();
    }

}

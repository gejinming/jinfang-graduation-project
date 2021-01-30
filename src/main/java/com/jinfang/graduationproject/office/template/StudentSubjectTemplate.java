package com.jinfang.graduationproject.office.template;

import com.deepoove.poi.config.Configure;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import com.jinfang.graduationproject.office.FileName;
import com.jinfang.graduationproject.office.vo.BornResult;
import com.jinfang.graduationproject.office.vo.StudentSubjectRowVo;
import com.jinfang.graduationproject.util.FileDirectoryUtil;
import com.jinfang.graduationproject.util.OfficeUtil;
import com.jinfang.graduationproject.util.RandomUtil;
import com.jinfang.graduationproject.vo.score.SubjectTeacherCompleteVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class StudentSubjectTemplate {

    @Value("${template.source}")
    private String templateSourcePath;

    @Value("${template.target}")
    private String templateTargetPath;

    private String nature(int nature) {
        if (nature == 1) {
            return "学术论文";
        } else if (nature == 2) {
            return "工程设计";
        } else if (nature == 3) {
            return "实验";
        } else if (nature == 4) {
            return "理论计算";
        } else if (nature == 5) {
            return "其他";
        }

        return "";
    }

    private String source(int source) {
        if (source == 1) {
            return "科研";
        } else if (source == 2) {
            return "生产实际";
        } else if (source == 3) {
            return "工程实际";
        } else if (source == 4) {
            return "实验";
        } else if (source == 5) {
            return "教师科研课题";
        } else if (source == 6) {
            return "其他";
        }

        return "";
    }

    protected Map<String, Object> transformParams(SubjectTeacherCompleteVo completeVo) {
        Map<String, Object> params = new HashMap<>();
        params.put("instituteName", completeVo.getInstituteName());
        params.put("teacherName", completeVo.getTeacherName());
        params.put("studentAmount", completeVo.getSubjectStudentCompleteVos().size());

        if (CollectionUtils.isEmpty(completeVo.getSubjectStudentCompleteVos())) {
            return params;
        }

        List<StudentSubjectRowVo> studentSubjects = new ArrayList<>();
        completeVo.getSubjectStudentCompleteVos().forEach(subjectStudent -> {
            StudentSubjectRowVo studentSubjectRowVo = StudentSubjectRowVo.builder().subjectName(subjectStudent.getSubjectName())
                    .studentName(subjectStudent.getStudentName()).nature(nature(subjectStudent.getSubjectNature()))
                    .source(source(subjectStudent.getSubjectSource())).build();

            if (subjectStudent.getSubjectType() == 1) {
                studentSubjectRowVo.setD("√");
            } else {
                studentSubjectRowVo.setP("√");
            }

            studentSubjects.add(studentSubjectRowVo);
        });

        params.put("studentSubjects", studentSubjects);

        return params;
    }

    private String getTargetName(String targetFileName) {
        FileDirectoryUtil.DirMeta dirMeta = FileDirectoryUtil.createDir(templateTargetPath);

        return dirMeta.getPath() + File.separator + targetFileName;
    }

    public BornResult render(SubjectTeacherCompleteVo completeVo) {
        Map<String, Object> params = transformParams(completeVo);

        String finalFilePath = getTargetName(buildFilename(completeVo));

        boolean isSuccess = true;
        try {
            OfficeUtil.renderWord(templateSourcePath + FileName.Mapping.STUDENT_SUBJECT.getSourceFileName(),
                    finalFilePath, getConfigure(), params);

        } catch (Exception e) {
            log.error("Born document failed by completeVo[{}]", completeVo, e);
            isSuccess = false;
        }

        return BornResult.builder().fileNameMapping(FileName.Mapping.STUDENT_SUBJECT).targetFilePath(finalFilePath)
                .isSuccess(isSuccess).build();

    }

    private Configure getConfigure() {
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();

        return Configure.newBuilder().bind("studentSubjects", policy).build();
    }

    private String buildFilename(SubjectTeacherCompleteVo completeVo) {
        return completeVo.getTeacherId() + FileName.FILE_NAME_CONNECT_CHAR
                + FileName.Mapping.STUDENT_SUBJECT.getSort() + FileName.FILE_NAME_CONNECT_CHAR
                + FileName.Mapping.STUDENT_SUBJECT.getEnName() + FileName.FILE_NAME_CONNECT_CHAR
                + RandomUtil.randomCode() + FileName.Mapping.STUDENT_SUBJECT.getFileType();
    }

}

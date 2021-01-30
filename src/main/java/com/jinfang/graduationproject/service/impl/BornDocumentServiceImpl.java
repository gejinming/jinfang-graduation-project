package com.jinfang.graduationproject.service.impl;

import com.jinfang.graduationproject.constants.teacher.SubjectConstants;
import com.jinfang.graduationproject.domain.*;
import com.jinfang.graduationproject.mapper.CcCoursePeriodeMapper;
import com.jinfang.graduationproject.mapper.GpBornDocumentMapper;
import com.jinfang.graduationproject.mapper.GpDocMetaMapper;
import com.jinfang.graduationproject.office.FileName;
import com.jinfang.graduationproject.office.template.*;
import com.jinfang.graduationproject.office.vo.BornResult;
import com.jinfang.graduationproject.service.*;
import com.jinfang.graduationproject.util.DateUtil;
import com.jinfang.graduationproject.util.FileDirectoryUtil;
import com.jinfang.graduationproject.util.FileDownloadUtil;
import com.jinfang.graduationproject.util.ZipUtil;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import com.jinfang.graduationproject.vo.score.SubjectStudentCompleteVo;
import com.jinfang.graduationproject.vo.score.SubjectTeacherCompleteVo;
import com.jinfang.graduationproject.vo.score.SummaryCompleteVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BornDocumentServiceImpl implements BornDocumentService {

    private static final String SUMMARY_FILE_NAME = "论文及过程材料";

    /**
     * 生成 zip 磁盘路径，方便下载
     */
    @Value("${storage.zip}")
    private String storageZip;

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final GpBornDocumentMapper gpBornDocumentMapper;
    private final CcCoursePeriodeMapper ccCoursePeriodeMapper;
    private final SubjectStudentScoreService subjectStudentScoreService;
    private final GpDissertationService dissertationService;
    private final GpDocMetaMapper gpDocMetaMapper;

    private final CheckTeacherAssessmentTemplate checkTeacherAssessmentTemplate;
    private final CopyrightAuthorizationTemplate copyrightAuthorizationTemplate;
    private final DefenseTeacherAssessmentTemplate defenseTeacherAssessmentTemplate;
    private final GraduationDefenseRecordTemplate graduationDefenseRecordTemplate;
    private final GraduationProjectBannerTemplate graduationProjectBannerTemplate;
    private final GraduationDesignBannerTemplate graduationDesignBannerTemplate;
    private final GuideTeacherAssessmentTemplate guideTeacherAssessmentTemplate;
    private final MidTermInspectionByGuideTemplate midTermInspectionByGuideTemplate;
    private final MidTermInspectionByInstituteTemplate midTermInspectionByInstituteTemplate;
    private final MidTermInspectionBySchoolTemplate midTermInspectionBySchoolTemplate;

    private final MissionTemplate missionTemplate;
    private final OpeningReportTemplate openingReportTemplate;
    private final OpeningReportDefenseRecordTemplate openingReportDefenseRecordTemplate;
    private final ProcessDocBannerTemplate processDocBannerTemplate;
    private final ReviewTemplate reviewTemplate;
    private final StudentSubjectTemplate studentSubjectTemplate;
    private final SubjectSummaryTemplate subjectSummaryTemplate;
    private final TranslationTemplate translationTemplate;
    private final WorkInstructionProcessTemplate workInstructionProcessTemplate;
    private final WorkRecordTemplate workRecordTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bornDoc(LoginUserMeta userMeta) {
        // 1. 根据届别 和 学校ID 查询 生成成绩表中的 所有 课题和学生记录
        List<SubjectStudentCompleteVo> subjectStudentCompleteVos = subjectStudentScoreService.findCompleteStudents(userMeta.getGrade(), userMeta.getSchoolId());
        if (CollectionUtils.isEmpty(subjectStudentCompleteVos)) {
            log.warn("0 files born");
            return false;
        }

        log.info("Found '{}' student-subjects ,it will building..", subjectStudentCompleteVos.size());
        List<GpBornDocument> bornDocuments = new ArrayList<>();

        bornSubjectStudentDoc(bornDocuments, subjectStudentCompleteVos);

        List<SubjectTeacherCompleteVo> subjectTeacherCompleteVos = buildSubjectTeacherCompleteVo(subjectStudentCompleteVos);

        log.info("Found '{}' teacher-subjects ,it will building..");
        subjectTeacherCompleteVos.forEach(subjectTeacherCompleteVo -> bornTeacherDoc(bornDocuments, subjectTeacherCompleteVo));

        bornSummaryDoc(bornDocuments, buildSummaryCompleteVo(subjectStudentCompleteVos, userMeta));

        log.info("Summary docs------> expect :{}, fact : {}",
                subjectStudentCompleteVos.size() * 15 + subjectTeacherCompleteVos.size() + 1,
                bornDocuments.size());

        deleteOldRecord(userMeta.getGrade(), userMeta.getSchoolId());

        saveBornDocument(bornDocuments);

        return true;
    }

    private List<SubjectTeacherCompleteVo> buildSubjectTeacherCompleteVo(List<SubjectStudentCompleteVo> subjectStudentCompleteVos) {
        Map<Long, List<SubjectStudentCompleteVo>> teacherSubjectMap = new HashMap<>();
        List<SubjectTeacherCompleteVo> subjectTeacherCompleteVos = new ArrayList<>();

        // 按照不同教师进行分组
        subjectStudentCompleteVos.forEach(subjectStudentCompleteVo -> {
            List<SubjectStudentCompleteVo> list = teacherSubjectMap.computeIfAbsent(subjectStudentCompleteVo.getTeacherId(), k -> new ArrayList<>());

            list.add(subjectStudentCompleteVo);
        });

        // 设置
        teacherSubjectMap.forEach((teacherId, vos) -> {
            CcTeacher ccTeacher = teacherService.findExtInfoById(teacherId);
            if (ccTeacher != null) {
                subjectTeacherCompleteVos.add(SubjectTeacherCompleteVo.builder().teacherId(teacherId).teacherName(ccTeacher.getName())
                        .instituteName(ccTeacher.getInstituteName()).majorName(ccTeacher.getMajorName()).schoolId(ccTeacher.getSchoolId())
                        .subjectStudentCompleteVos(vos).build());
            }
        });

        return subjectTeacherCompleteVos;
    }

    private SummaryCompleteVo buildSummaryCompleteVo(List<SubjectStudentCompleteVo> subjectStudentCompleteVos, LoginUserMeta userMeta) {
        SummaryCompleteVo summaryCompleteVo = new SummaryCompleteVo();
        summaryCompleteVo.setSchoolId(userMeta.getSchoolId());
        CcCoursePeriode coursePeriode = ccCoursePeriodeMapper.selectExtByGradeAndSchoolId(userMeta.getGrade(), userMeta.getSchoolId());
        if (coursePeriode != null) {
            summaryCompleteVo.setSchoolId(coursePeriode.getSchoolId());
            summaryCompleteVo.setMajorName(coursePeriode.getMajorName());
            summaryCompleteVo.setInstituteName(coursePeriode.getInstituteName());
        }

        summaryCompleteVo.setSubjectStudentCompleteVos(subjectStudentCompleteVos);

        return summaryCompleteVo;
    }

    private void bornSubjectStudentDoc(List<GpBornDocument> bornDocuments, List<SubjectStudentCompleteVo> subjectStudentCompleteVos) {
        for (SubjectStudentCompleteVo completeVo : subjectStudentCompleteVos) {
            addBornDocument(bornDocuments, completeVo, checkTeacherAssessmentTemplate.render(completeVo));
            addBornDocument(bornDocuments, completeVo, copyrightAuthorizationTemplate.render(completeVo));
            addBornDocument(bornDocuments, completeVo, defenseTeacherAssessmentTemplate.render(completeVo));
            addBornDocument(bornDocuments, completeVo, graduationDefenseRecordTemplate.render(completeVo));

            if (SubjectConstants.SubjectType.DESIGNER.getCode() == completeVo.getSubjectType()) {
                addBornDocument(bornDocuments, completeVo, graduationDesignBannerTemplate.render(completeVo));
            } else {
                addBornDocument(bornDocuments, completeVo, graduationProjectBannerTemplate.render(completeVo));
            }

            addBornDocument(bornDocuments, completeVo, guideTeacherAssessmentTemplate.render(completeVo));
            addBornDocument(bornDocuments, completeVo, midTermInspectionByGuideTemplate.render(completeVo));
            addBornDocument(bornDocuments, completeVo, missionTemplate.render(completeVo));
            addBornDocument(bornDocuments, completeVo, openingReportTemplate.render(completeVo));
            addBornDocument(bornDocuments, completeVo, openingReportDefenseRecordTemplate.render(completeVo));
            addBornDocument(bornDocuments, completeVo, processDocBannerTemplate.render(completeVo));
            addBornDocument(bornDocuments, completeVo, reviewTemplate.render(completeVo));
            addBornDocument(bornDocuments, completeVo, translationTemplate.render(completeVo));
            addBornDocument(bornDocuments, completeVo, workInstructionProcessTemplate.render(completeVo));
            addBornDocument(bornDocuments, completeVo, workRecordTemplate.render(completeVo));
        }
    }

    private void bornTeacherDoc(List<GpBornDocument> bornDocuments, SubjectTeacherCompleteVo subjectTeacherCompleteVo) {
        addBornDocument(bornDocuments, subjectTeacherCompleteVo, studentSubjectTemplate.render(subjectTeacherCompleteVo));
    }

    private void bornSummaryDoc(List<GpBornDocument> bornDocuments, SummaryCompleteVo summaryCompleteVo) {
        addBornDocument(bornDocuments, summaryCompleteVo, subjectSummaryTemplate.render(summaryCompleteVo));
        addBornDocument(bornDocuments, summaryCompleteVo, midTermInspectionByInstituteTemplate.render(summaryCompleteVo));
        addBornDocument(bornDocuments, summaryCompleteVo, midTermInspectionBySchoolTemplate.render(summaryCompleteVo));
    }

    private void addBornDocument(List<GpBornDocument> bornDocuments, SubjectStudentCompleteVo completeVo, BornResult bornResult) {
        if (!bornResult.isSuccess()) {
            return;
        }

        GpBornDocument bornDocument = new GpBornDocument();
        bornDocument.setSubjectId(completeVo.getSubjectId());
        bornDocument.setStudentId(completeVo.getStudentId());
        bornDocument.setTeacherId(completeVo.getTeacherId());

        bornDocument.setGrade(completeVo.getGrade());
        bornDocument.setSchoolId(completeVo.getSchoolId());
        bornDocument.setFilePath(bornResult.getTargetFilePath());
        bornDocument.setFileType(bornResult.getFileNameMapping().name());
        bornDocument.setBornType(2);

        bornDocuments.add(bornDocument);
    }

    private void addBornDocument(List<GpBornDocument> bornDocuments, SubjectTeacherCompleteVo completeVo, BornResult bornResult) {
        if (!bornResult.isSuccess()) {
            return;
        }

        GpBornDocument bornDocument = new GpBornDocument();
        bornDocument.setSchoolId(completeVo.getSchoolId());
        bornDocument.setTeacherId((completeVo.getTeacherId()));

        // 默认届别当前年度
        bornDocument.setGrade(DateUtil.getCurrentYear() + "");
        bornDocument.setSchoolId(completeVo.getSchoolId());
        bornDocument.setFilePath(bornResult.getTargetFilePath());
        bornDocument.setFileType(bornResult.getFileNameMapping().name());
        bornDocument.setBornType(2);

        bornDocuments.add(bornDocument);
    }

    private void addBornDocument(List<GpBornDocument> bornDocuments, SummaryCompleteVo completeVo, BornResult bornResult) {
        if (!bornResult.isSuccess()) {
            return;
        }

        GpBornDocument bornDocument = new GpBornDocument();
        bornDocument.setSchoolId(completeVo.getSchoolId());

        // 默认届别当前年度
        bornDocument.setGrade(DateUtil.getCurrentYear() + "");
        bornDocument.setSchoolId(completeVo.getSchoolId());
        bornDocument.setFilePath(bornResult.getTargetFilePath());
        bornDocument.setFileType(bornResult.getFileNameMapping().name());
        bornDocument.setBornType(2);
        bornDocument.setIsSuper(1);

        bornDocuments.add(bornDocument);
    }

    private void deleteOldRecord(String grade, Long schoolId) {
        gpBornDocumentMapper.deleteByGradeAndSchoolId(grade, schoolId);
    }

    private void saveBornDocument(List<GpBornDocument> bornDocuments) {
        if (CollectionUtils.isEmpty(bornDocuments)) {
            log.warn("no file born");
            return;
        }
        // 批量插入
        int rows = gpBornDocumentMapper.batchInsert(bornDocuments);

        log.info("Total bornDocuments amount is '{}', batch insert size : {}", bornDocuments.size(), rows);
    }

    private String currentTimeFileName() {
        return DateFormatUtils.format(new Date(), SUMMARY_FILE_NAME + "-yyyyMMddHHmmssSSS") + ZipUtil.ZIP_TYPE;
    }

    @Override
    public ResponseEntity<InputStreamResource> download(Long subjectStudentId) {
        List<GpBornDocument> documents = gpBornDocumentMapper.findBySubjectStudentId(subjectStudentId);
        if (CollectionUtils.isEmpty(documents)) {
            log.warn("Can't find any docs by subjectStudentId[{}]", subjectStudentId);
            return null;
        }

        String studentName = null;
        String subjectName = null;
        Long subjectId = null;
        Long studentId = null;
        List<ZipUtil.ZipFileMeta> zipFileMetas = new ArrayList<>();
        for (GpBornDocument document : documents) {
            if (StringUtils.isEmpty(studentName)) {
                studentId = document.getStudentId();
                studentName = document.getStudentName();
                subjectId = document.getSubjectId();
                subjectName = document.getSubjectName();
            }

            zipFileMetas.add(parseFileMeta(document));
        }

        getDissertationSimilarityFile(zipFileMetas, subjectId, studentId);

        String zipFileName = studentName + FileName.FILE_NAME_CONNECT_CHAR + subjectName + ZipUtil.ZIP_TYPE;
        String zipFilePath = getFileDir() + File.separator + zipFileName;
        boolean isOK = ZipUtil.zip(zipFileMetas, zipFilePath);
        if (isOK) {
            return zipFileToStream(zipFilePath, zipFileName);
        }

        return null;
    }

    private String getStudentName(Long studentId) {
        CcStudent student = studentService.findInfoById(studentId);
        if (student == null) {
            log.warn("Can't find any students by id[{}]", studentId);
            return studentId + "";
        }
        return student.getName();
    }

    private String getTeacherName(Long teacherId) {
        CcTeacher teacher = teacherService.findById(teacherId);
        if (teacher == null) {
            log.warn("Can't find any teachers by id[{}]", teacherId);
            return teacherId + "";
        }
        return teacher.getName();
    }

    /**
     * 获取压缩后目录
     */
    private String getFileDir() {
        FileDirectoryUtil.DirMeta dirMeta = FileDirectoryUtil.createDir(storageZip, System.currentTimeMillis() + "");
        return dirMeta.getPath();
    }

    /**
     * 压缩所有学生的文件`
     *
     * @param studentDocuments 多组学生文件
     * @return 压缩后的zip名称集合
     */
    private List<String> zipStudentDocuments(Map<Long, List<GpBornDocument>> studentDocuments) {
        List<String> zipFileNames = new ArrayList<>();
        studentDocuments.forEach((studentId, documents) -> {
            String studentName = getStudentName(studentId);
            String zipFileName = getFileDir() + File.separator + studentName + ZipUtil.ZIP_TYPE;
            Long subjectId = null;

            List<ZipUtil.ZipFileMeta> zipFileMetas = new ArrayList<>();
            for (GpBornDocument document : documents) {
                zipFileMetas.add(parseFileMeta(document));
                subjectId = document.getSubjectId();
            }

            getDissertationSimilarityFile(zipFileMetas, subjectId, studentId);

            boolean isOK = ZipUtil.zip(zipFileMetas, zipFileName);
            if (isOK) {
                zipFileNames.add(zipFileName);
            }

            log.info("压缩学生:{}， 文件名:{}， 文件数量:{} , 结果:{}", studentName,
                    zipFileName, zipFileMetas.size(), isOK);
        });


        return zipFileNames;
    }

    private void appendTeacherDocuments(Map<Long, List<GpBornDocument>> teacherDocuments, List<ZipUtil.ZipFileMeta> zipFileMetas) {
        teacherDocuments.forEach((teacherId, documents) -> {
            String teacherName = getTeacherName(teacherId);

            for (GpBornDocument document : documents) {
                // 拼接后的名称为， 教师-xxx.docx
                String newFileName = teacherName + FileName.FILE_NAME_CONNECT_CHAR + FileName.Mapping.of(document.getFileType()).tranlateCnName();
                zipFileMetas.add(ZipUtil.ZipFileMeta.builder().originFileName(document.getFilePath()).newFileName(newFileName).build());
            }

        });
    }

    private void appendSummaryDocuments(List<GpBornDocument> summaryDocuments, List<ZipUtil.ZipFileMeta> zipFileMetas) {
        for (GpBornDocument document : summaryDocuments) {
            zipFileMetas.add(parseFileMeta(document));
        }
    }

    /**
     * 获取论文相似度文件（用户上传）
     *
     * @param zipFileMetas 组装的数据
     * @param subjectId    课题ID
     * @param studentId    学生iD
     */
    private void getDissertationSimilarityFile(List<ZipUtil.ZipFileMeta> zipFileMetas, Long subjectId, Long studentId) {
        GpDissertation dissertation = dissertationService.findBySubjectStudentId(subjectId, studentId);
        if (dissertation == null) {
            log.warn("Can't find any dissertations by subjectId[{}] and studentId:{}", subjectId, studentId);
            return;
        }

        if (dissertation.getSimilarityFileId() == null) {
            log.warn("Can't find dissertation similarity file by subjectId[{}] and studentId:{}", subjectId, studentId);
            return;
        }

        GpDocMeta gpDocMeta = gpDocMetaMapper.findById(dissertation.getSimilarityFileId());
        if (gpDocMeta == null) {
            log.warn("Can not find any data by id[{}]", dissertation.getSimilarityFileId());
            return;
        }

        String newFileName = FileName.Mapping.DISSERTATION_SIMILARITY_FILE.tranlateCnName();

        zipFileMetas.add(ZipUtil.ZipFileMeta.builder().originFileName(gpDocMeta.getPath()).newFileName(newFileName).build());

    }

    private ZipUtil.ZipFileMeta parseFileMeta(GpBornDocument document) {
        String newFileName = FileName.Mapping.of(document.getFileType()).tranlateCnName();

        return ZipUtil.ZipFileMeta.builder().originFileName(document.getFilePath()).newFileName(newFileName).build();
    }

    @Override
    public ResponseEntity<InputStreamResource> download(String grade) {
        if (StringUtils.isEmpty(grade)) {
            log.warn("Ignored caused by 'grade is empty'");
            return null;
        }

        List<GpBornDocument> documents = gpBornDocumentMapper.findByGrade(grade);
        List<ZipUtil.ZipFileMeta> zipFileMetas = new ArrayList<>();

        // 因目前教师就一个文件，没必要压缩到单独的zip，直接放置最外层即可
        appendTeacherDocuments(categoryTeachers(documents), zipFileMetas);

        // 总文件也放置在最外层
        appendSummaryDocuments(categorySummarry(documents), zipFileMetas);

        String finalZipName = currentTimeFileName();
        String zipFileName = getFileDir() + File.separator + finalZipName;

        boolean isOK = ZipUtil.zip(zipFileMetas, zipFileName);
        if (isOK) {
            return zipFileToStream(zipFileName, finalZipName);
        }

        return null;
    }

    private Map<Long, List<GpBornDocument>> categoryStudents(List<GpBornDocument> documents) {
        Map<Long, List<GpBornDocument>> fileNameMapping = new HashMap<>();
        documents.forEach(document -> {
            if (document.getStudentId() != null && document.getSubjectId() != null) {
                if (CollectionUtils.isEmpty(fileNameMapping.get(document.getStudentId()))) {
                    fileNameMapping.put(document.getStudentId(), new ArrayList<>());
                }

                fileNameMapping.get(document.getStudentId()).add(document);
            }
        });
        return fileNameMapping;
    }

    private Map<Long, List<GpBornDocument>> categoryTeachers(List<GpBornDocument> documents) {
        Map<Long, List<GpBornDocument>> fileNameMapping = new HashMap<>();
        documents.forEach(document -> {
            // 判断是否为教师独有的文件类型
            if (FileName.Mapping.STUDENT_SUBJECT.name().equalsIgnoreCase(document.getFileType())) {
                if (CollectionUtils.isEmpty(fileNameMapping.get(document.getTeacherId()))) {
                    fileNameMapping.put(document.getTeacherId(), new ArrayList<>());
                }

                fileNameMapping.get(document.getTeacherId()).add(document);
            }
        });
        return fileNameMapping;
    }

    private List<GpBornDocument> categorySummarry(List<GpBornDocument> documents) {
        List<GpBornDocument> summaryDocs = new ArrayList<>();
        documents.forEach(document -> {
            if (document.getIsSuper() == 1) {
                summaryDocs.add(document);
            }
        });

        return summaryDocs;
    }

    private ResponseEntity<InputStreamResource> zipFileToStream(String zipFileName, String newFileName) {
        log.info("Compress to zip {} --> {}", zipFileName, newFileName);
        return FileDownloadUtil.download(zipFileName, newFileName);
    }

    @Override
    public List<String> findGradeList() {
        return gpBornDocumentMapper.selectGradeList();
    }
}

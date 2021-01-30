package com.jinfang.graduationproject.service;

import com.alibaba.fastjson.JSON;
import com.jinfang.graduationproject.constants.FileBizType;
import com.jinfang.graduationproject.domain.GpDocMeta;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.mapper.GpDocMetaMapper;
import com.jinfang.graduationproject.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    /**
     * 上传磁盘写入路径
     */
    @Value("${storage.disk}")
    private String storageDisk;

    /**
     * 生成的网络预览路径
     */
    @Value("${storage.domain}")
    private String storageDomain;

    /**
     * 生成 zip 磁盘路径，方便下载
     */
    @Value("${storage.zip}")
    private String storageZip;

    private final GpDocMetaMapper gpDocMetaMapper;
    private final GpDissertationService gpDissertationService;

    public HttpResult upload(MultipartFile multipartFile) {
        return upload(multipartFile, FileBizType.UNKNOWN.getCode());
    }

    public HttpResult upload(MultipartFile multipartFile, String bizType) {
        if (multipartFile == null) {
            return HttpResult.error("上传文件数据为空");
        }

        try {
            FileUploadUtil.FileUploadResult fileUploadResult = FileUploadUtil.upload(multipartFile.getBytes(),
                    multipartFile.getOriginalFilename(), storageDisk);

            if (!fileUploadResult.isResult()) {
                return HttpResult.error(fileUploadResult.getMsg());
            }

            Long docId = saveFiled(multipartFile.getOriginalFilename(), bizType, fileUploadResult);

            return HttpResult.ok(docId);
        } catch (Exception e) {
            log.error("multipartFile[{}]. type[{}] upload failed", multipartFile.getOriginalFilename(), e);
            return HttpResult.error("文件上传失败");
        }

    }

    private Long saveFiled(String originName, String bizType, FileUploadUtil.FileUploadResult fileUploadResult) {
        GpDocMeta docMeta = GpDocMeta.builder().originName(originName).newName(fileUploadResult.getFilename())
                .type(bizType).size(fileUploadResult.getFileSize()).path(fileUploadResult
                        .getFileFullName()).url(fileUploadResult.getUrlPathName())
                .createDate(new Date()).build();

        int result = gpDocMetaMapper.add(docMeta);
        if (result <= 0) {
            throw new RuntimeException("file[" + fileUploadResult.getFileFullName() + "] insert failed");
        }

        return docMeta.getId();
    }

    /**
     * 根据文件ID获取文件 预览路径，需要提前配置好nginx url映射
     *
     * @param id 文件ID
     * @return 预览URL
     */
    public HttpResult getFilePreviewUrl(Long id) {
        if (id == null || id == 0) {
            log.warn("Args[id] is necessary");

            return HttpResult.error("数据资源无效");
        }

        GpDocMeta gpDocMeta = gpDocMetaMapper.findById(id);
        if (gpDocMeta == null) {
            log.warn("Can not find any data by id[{}]", id);
            return HttpResult.error("数据资源无效");
        }

        if (StringUtils.isEmpty(gpDocMeta.getUrl())) {
            log.error("Url is null by id[{}]", id);
            return HttpResult.error("数据资源无效");
        }

//        String url = DecodeUtil.encode(storageDomain + gpDocMeta.getUrl());
        String url = storageDomain + gpDocMeta.getUrl();
        log.info("Preview file[{}] url -> {}", id, url);

        return HttpResult.data(url);
    }

    public ResponseEntity<InputStreamResource> download(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            log.error("download failed by ids is empty.");
            return null;
        }

        List<GpDocMeta> gpDocMetas = gpDocMetaMapper.findByIds(ids);
        if (CollectionUtils.isEmpty(gpDocMetas)) {
            log.warn("Can not find any data by id[{}]", gpDocMetas);
            return null;
        }

        List<ZipUtil.ZipFileMeta> zipFileMetas = new ArrayList<>();
        for (GpDocMeta gpDocMeta : gpDocMetas) {
            FileBizType bizType = FileBizType.of(gpDocMeta.getType());
            if (FileBizType.UNKNOWN == bizType) {
                continue;
            }

            String newFileName = gpDissertationService.findByFileId(gpDocMeta.getId(), bizType);
            if (StringUtils.isEmpty(newFileName)) {
                continue;
            }

            // 拼接文件扩展名
            newFileName = newFileName + FileUploadUtil.getFileExtName(gpDocMeta.getNewName());

            zipFileMetas.add(ZipUtil.ZipFileMeta.builder().originFileName(gpDocMeta.getPath()).newFileName(newFileName).build());
        }

        return getZipFile(zipFileMetas);
    }

//    private List<String> findFileNamesByIds(String id) {
//        if (StringUtils.isEmpty(ids)) {
//            log.error("ids[{}] is empty", ids);
//            return null;
//        }
//
//        List<String> fileNames = new ArrayList<>();
//        String[] loanDocIdArray = ids.split(",");
//        List<GpDocMeta> loanDocList = gpDocMetaMapper.findByIds(Arrays.asList(loanDocIdArray));
//
//        List<String> fileNames = new ArrayList<>();
//        for (FileMeta loanDoc : loanDocList) {
//            if (loanDoc == null) {
//                continue;
//            }
//
//            if (isPdf) {
//                fileNames.add(loanDoc.getPdfPath());
//            } else {
//                fileNames.add(loanDoc.getDocPath());
//            }
//        }
//
//        return fileNames;
//    }

    /**
     * 获取携带日期格式路径
     *
     * @param dir 原路径
     * @return 原路径追加日期后的路径
     */
    private FileDirectoryUtil.DirMeta getDirWithDate(String dir) {
        return FileDirectoryUtil.createDir(dir);
    }

    private String getDateTimeFileName(int size) {
        return new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date()) + "-" + size
                + "-" + RandomUtil.randomCode();
    }

    /**
     * 返回 将文件压缩成ZIP包数据流
     *
     * @param zipFileMetas 需要压缩的文件名称数组
     * @return ZIP数据流
     */
    private ResponseEntity<InputStreamResource> getZipFile(List<ZipUtil.ZipFileMeta> zipFileMetas) {
        if (CollectionUtils.isEmpty(zipFileMetas)) {
            log.warn("Can not find any zip files");
            return null;
        }

        FileDirectoryUtil.DirMeta dirMeta = getDirWithDate(storageZip);
        String targetFileName = getDateTimeFileName(zipFileMetas.size()) + ".zip";
        String targetFileFullName = dirMeta.getPath() + File.separator + targetFileName;

        log.info("====Download file :{}", JSON.toJSONString(zipFileMetas));

        boolean isZipOk = ZipUtil.zip(zipFileMetas, targetFileFullName);
        if (!isZipOk) {
            log.error("docFileNames[{}] zip failed", zipFileMetas);
            return null;
        }

        return FileDownloadUtil.download(targetFileFullName, targetFileName);
    }

}

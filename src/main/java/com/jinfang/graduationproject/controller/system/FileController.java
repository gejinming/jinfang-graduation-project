package com.jinfang.graduationproject.controller.system;

import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/file")
@AllArgsConstructor
@Api(tags = "文件操作接口")
public class FileController {

    private FileService fileService;

    @PostMapping(value = "/upload")
    @ApiOperation(value = "上传文件", httpMethod = "POST")
    public HttpResult upload(@RequestParam("file") MultipartFile file) {
        return fileService.upload(file);
    }

    @PostMapping(value = "/uploadWithType")
    @ApiOperation(value = "根据文件名称上传", httpMethod = "POST")
    public HttpResult uploadWithName(@RequestParam("file") MultipartFile file,
                                     @RequestParam("type") String type) {
        return fileService.upload(file, type);
    }

    @GetMapping(value = "/preview/{id}")
    @ApiOperation(value = "获取预览文件URL", httpMethod = "GET")
    public HttpResult preview(@PathVariable("id") Long id) {
        return fileService.getFilePreviewUrl(id);
    }

    @GetMapping(value = "/download")
    @ApiOperation(value = "下载文件", httpMethod = "GET")
    public ResponseEntity<InputStreamResource> download(@RequestParam("ids") List<String> ids) {
        return fileService.download(ids);
    }

}

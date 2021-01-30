package com.jinfang.graduationproject.handler;

import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.http.HttpStatus;
import com.jinfang.graduationproject.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public HttpResult handlerMaxUploadFile(MaxUploadSizeExceededException ex) {
        log.warn("upload failed", ex);
        return HttpResult.error("文件上传大小不能超过20M");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public HttpResult exception(HttpServletRequest request, HttpServletResponse response, Exception e) {
        if (e instanceof AuthException) {
            log.warn("鉴权失败 -> {}", e.getMessage());
            return HttpResult.error(HttpStatus.SC_UNAUTHORIZED, "鉴权失败，请重新登录");
        }

        if (e instanceof AccessDeniedException) {
            log.warn("资源不可访问", e);
            return HttpResult.error(HttpStatus.SC_FORBIDDEN, "资源不可访问");
        }

        if (e instanceof HttpRequestMethodNotSupportedException) {
            log.warn("资源不可访问", e);
            return HttpResult.error(HttpStatus.SC_METHOD_NOT_ALLOWED, "资源不可访问");
        }

        log.error("Global exception", e);

        return HttpResult.error();
    }

}

package com.jinfang.graduationproject.controller;

import com.jinfang.graduationproject.dto.page.PageRequest;
import com.jinfang.graduationproject.exception.AuthException;
import com.jinfang.graduationproject.service.SystemService;
import com.jinfang.graduationproject.util.JwtTokenUtils;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {

    @Resource
    protected HttpServletRequest request;

    @Resource
    protected HttpServletResponse response;

    @Autowired
    private SystemService systemService;

    /**
     * 获取当前登录用信息
     * @param refresh 是否强制刷新内存数据
     */
    protected LoginUserMeta getLoginUser(boolean refresh) {
        String token = JwtTokenUtils.getToken(request);
        if (StringUtils.isEmpty(token)) {
            throw new AuthException("Invalid token");
        }

        boolean isExpired = JwtTokenUtils.isTokenExpired(token);
        if (isExpired) {
            throw new AuthException("Token expired");
        }

        return systemService.login(token, refresh);
    }

    protected LoginUserMeta getLoginUser() {
        return getLoginUser(false);
    }

    protected void addSchoolId(PageRequest pageRequest, LoginUserMeta loginUserMeta) {
        if (loginUserMeta == null || loginUserMeta.getSchoolId() == null) {
            throw new IllegalArgumentException("SchoolId is empty");
        }

//        pageRequest.getColumnFilters().put("schoolId",
//                ColumnFilter.builder().name("schoolId").value(loginUserMeta.getSchoolId().toString()).build());

        pageRequest.getExtProps().put("schoolId", loginUserMeta.getSchoolId());
    }

    protected void addTeacherId(PageRequest pageRequest, LoginUserMeta loginUserMeta) {
        if (loginUserMeta == null || loginUserMeta.getTeacherId() == null) {
            throw new IllegalArgumentException("SchoolId is empty");
        }

//        pageRequest.getColumnFilters().put("teacher_id",
//                ColumnFilter.builder().name("teacher_id").value(loginUserMeta.getTeacherIdText()).build());

        pageRequest.getExtProps().put("teacherId", loginUserMeta.getTeacherId());
    }

    protected void addGrade(PageRequest pageRequest, LoginUserMeta loginUserMeta) {
        if (loginUserMeta == null) {
            throw new IllegalArgumentException("loginUserMeta is empty");
        }

        pageRequest.getExtProps().put("grade", loginUserMeta.getGrade());
    }

    protected void addCondition(PageRequest pageRequest, String name, Object value) {
        if (value == null) {
            throw new IllegalArgumentException(name + " is empty");
        }

//        pageRequest.getColumnFilters().put("teacher_id",
//                ColumnFilter.builder().name("teacher_id").value(loginUserMeta.getTeacherIdText()).build());

        pageRequest.getExtProps().put(name, value);
    }
}

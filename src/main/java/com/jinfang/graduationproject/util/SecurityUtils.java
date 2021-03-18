package com.jinfang.graduationproject.util;

import com.alibaba.fastjson.JSON;
import com.jinfang.graduationproject.security.SsoAuthenticationToken;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

/**
 * Security相关操作
 */
@Slf4j
public class SecurityUtils {

    /**
     * 系统登录认证
     */
    public static String login(HttpServletRequest request,
                               AuthenticationManager authenticationManager,
                               LoginUserMeta loginUserMeta) {

        Claims tokenClaims = JwtTokenUtils.getClaimsFromToken(JwtTokenUtils.getToken(request));

        SsoAuthenticationToken ssoAuthenticationToken = new SsoAuthenticationToken(JSON.toJSONString(loginUserMeta));

        ssoAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 执行登录认证过程
        Authentication authentication = authenticationManager.authenticate(ssoAuthenticationToken);

        // 认证成功存储认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return JwtTokenUtils.exchangeToken(tokenClaims, authentication.getAuthorities());
    }

    /**
     * 获取令牌进行认证
     */
    public static void checkAuthentication(HttpServletRequest request) {
        // 获取令牌并根据令牌获取登录认证信息
        Authentication authentication = JwtTokenUtils.getAuthentication(request);
        if (authentication == null) {
            return;
        }
        /*
        * 设置登录认证信息到上下文
         以后就可以 拿到数据了
         * Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        * */

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * 获取当前用户名
     */
    public static String getUsername() {
        return getUsername(getAuthentication());
    }

    /**
     * 获取用户名
     */
    private static String getUsername(Authentication authentication) {
        String username = null;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }

    /**
     * 获取当前登录信息
     */
    static Authentication getAuthentication() {
        if (SecurityContextHolder.getContext() == null) {
            return null;
        }

        return SecurityContextHolder.getContext().getAuthentication();
    }

}

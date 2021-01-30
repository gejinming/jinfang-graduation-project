package com.jinfang.graduationproject.util;

import com.jinfang.graduationproject.exception.AuthException;
import com.jinfang.graduationproject.security.GrantedAuthorityImpl;
import com.jinfang.graduationproject.security.SsoAuthenticationToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

/**
 * JWT工具类
 */
@Slf4j
public class JwtTokenUtils implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 权限列表
     */
    private static final String AUTHORITIES = "authorities";
    /**
     * 密钥
     */
    private static final String SECRET = "zheJingJinFangSchool";

    private static final String ISSUER = "Jinfang";

    /**
     * 有效期12小时
     */
    private static final long EXPIRE_TIME = 6 * 60 * 60 * 1000;

    /**
     * 角色
     */
    private static final String ROLE_CLAIMS = "role";

    public static String getRole(Claims claims) {
        if (claims == null || claims.get(ROLE_CLAIMS) == null) {
            return null;
        }

        return claims.get(ROLE_CLAIMS).toString();
    }

    /**
     * 生成token（专业认证系统生成规则）
     *
     * @param username 用户名，一般为教师ID或者学生学号
     * @param role     角色，目前 teacher, student
     * @return token
     */
    public static String createToken(String username, String role) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(ROLE_CLAIMS, role);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                //设置角色名
                .setClaims(claims)
                //设置发证人
                .setIssuer(ISSUER)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .compact();
    }

    /**
     * 根据原有的TOKEN 交换 本系统内新的TOKEN
     *
     * @param originTokenClaims 原有TOKEN
     * @param authorities       权限清单
     */
    static String exchangeToken(Claims originTokenClaims, Collection<? extends GrantedAuthority> authorities) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(ROLE_CLAIMS, originTokenClaims.get(ROLE_CLAIMS));
        claims.put(AUTHORITIES, authorities);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                //设置角色名
                .setClaims(claims)
                //设置发证人
                .setIssuer(originTokenClaims.getIssuer())
                .setSubject(originTokenClaims.getSubject())
                .setIssuedAt(originTokenClaims.getIssuedAt())
                .setExpiration(originTokenClaims.getExpiration())
                .compact();
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    private static String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 根据请求令牌获取登录认证信息
     *
     * @param request 令牌
     * @return 用户名
     */
    static Authentication getAuthentication(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = JwtTokenUtils.getToken(request);
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        Authentication authentication = null;
        if (SecurityUtils.getAuthentication() == null) {
            // 上下文中Authentication为空
            Claims claims = getClaimsFromToken(token);
            if (claims == null) {
                return null;
            }

            String username = claims.getSubject();
            if (username == null) {
                return null;
            }

            if (isTokenExpired(token)) {
                return null;
            }

            Object authors = claims.get(AUTHORITIES);
            if (authors == null) {
                return null;
            }

            List<GrantedAuthority> authorities = new ArrayList<>();
            if (authors instanceof List) {
                for (Object object : (List) authors) {
                    authorities.add(new GrantedAuthorityImpl((String) ((Map) object).get("authority")));
                }
            }

            authentication = new SsoAuthenticationToken(username, authorities);


        } else {
            if (validateToken(token, SecurityUtils.getUsername())) {
                // 如果上下文中Authentication非空，且请求令牌合法，直接返回当前登录认证信息
                authentication = SecurityUtils.getAuthentication();
            }
        }

        return authentication;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.warn("Can't find parse claims from token[{}]", token, e);
            claims = null;
        }
        return claims;
    }

    /**
     * 验证令牌
     */
    private static boolean validateToken(String token, String username) {
        String userName = getUsernameFromToken(token);
        return (userName.equals(username) && !isTokenExpired(token));
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            log.warn("isTokenExpired failed by token[{}]", token, e);
            return false;
        }
    }

    /**
     * 获取请求token
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String tokenHead = "Bearer ";
        if (token == null) {
            token = request.getHeader("token");
        } else if (token.contains(tokenHead)) {
            token = token.substring(tokenHead.length());
        }

        if (StringUtils.isEmpty(token)) {
            throw new AuthException("Can't find token from Authorization|token..");
        }

        return token;
    }

}
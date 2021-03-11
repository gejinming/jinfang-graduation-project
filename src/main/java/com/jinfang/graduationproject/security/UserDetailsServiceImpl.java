package com.jinfang.graduationproject.security;

import com.alibaba.fastjson.JSON;
import com.jinfang.graduationproject.service.SysMenuService;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户登录认证信息查询
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String userBody) throws UsernameNotFoundException {
        try {
            LoginUserMeta loginUserMeta = JSON.parseObject(userBody, LoginUserMeta.class);
            if (loginUserMeta == null) {
                throw new UsernameNotFoundException("userBody[" + userBody + "] translate null");
            }

            List<String> permissions = sysMenuService.findPermsByRoleName(loginUserMeta.getRoles(), loginUserMeta.getSchoolId());
            if (CollectionUtils.isEmpty(permissions)) {
                return null;
            }
            List<GrantedAuthority> grantedAuthorities = permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
            // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
            return new JwtUserDetails(loginUserMeta.getPrincipalId() + "", grantedAuthorities);

        } catch (Exception e) {
            throw new UsernameNotFoundException("userBody[" + userBody + "] parse failed");
        }
    }
}
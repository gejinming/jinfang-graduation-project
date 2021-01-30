package com.jinfang.graduationproject.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jinfang.graduationproject.constants.SysConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 安全用户模型，目前因为本项目是采用上一个项目token，顾密码暂时无用
 */
public class JwtUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String username;

    /**
     * 因为目前免密，顾和用户名设置为一样
     */
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    JwtUserDetails(String username, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = username;
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

}
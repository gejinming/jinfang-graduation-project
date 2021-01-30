package com.jinfang.graduationproject.service;

import com.jinfang.graduationproject.vo.LoginUserMeta;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface SystemService {

    LoginUserMeta login(String token, boolean refresh);

    List<GrantedAuthority> getBySubjectId(String subjectId) throws UsernameNotFoundException;

}

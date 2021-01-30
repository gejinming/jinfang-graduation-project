package com.jinfang.graduationproject.security;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * 自定义令牌对象
 */
public class SsoAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    public SsoAuthenticationToken(Object principal) {
        super(principal, principal);
    }

    public SsoAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public SsoAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String token) {
        super(principal, credentials, authorities);
    }

}

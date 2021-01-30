package com.jinfang.graduationproject.config;

import com.jinfang.graduationproject.security.JwtAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Spring Security Config
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        // 使用自定义身份验证组件
        auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用 csrf, 由于使用的是JWT，我们这里不需要csrf
        http.cors().and().csrf().disable()
                .authorizeRequests()
                // 跨域预检请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 不拦截资源配置
                .antMatchers("/", "/login", "/captcha.jpg**",
                        "/swagger-ui.html", "/swagger-resources", "/v2/api-docs", "/webjars/springfox-swagger-ui/**")
                .permitAll()
                // 其他所有请求需要身份认证
                .anyRequest().authenticated();

        // 退出登录处理器
//        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        // token验证过滤器
      //http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


}
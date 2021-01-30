package com.jinfang.graduationproject.controller.system;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.jinfang.graduationproject.controller.BaseController;
import com.jinfang.graduationproject.dto.http.HttpResult;
import com.jinfang.graduationproject.dto.http.HttpStatus;
import com.jinfang.graduationproject.util.IOUtils;
import com.jinfang.graduationproject.util.SecurityUtils;
import com.jinfang.graduationproject.vo.LoginResult;
import com.jinfang.graduationproject.vo.LoginUserMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登录控制器
 *
 * @author Louis
 * @date Oct 29, 2018
 */
@Slf4j
@RestController
@AllArgsConstructor
@Api(tags = "系统入口接口")
public class SysIndexController extends BaseController {

    private Producer producer;
    private AuthenticationManager authenticationManager;

    @ApiOperation(value = "验证码接口", notes = "暂时未使用")
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存到验证码到 session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    @ApiOperation(value = "登录接口", notes = "通过专业认证token交换毕业设计系统新token")
    @PostMapping(value = "/login")
    public HttpResult login() {
        try {
            LoginUserMeta userMeta = getLoginUser(true);

            String token = SecurityUtils.login(request, authenticationManager, userMeta);
            if (StringUtils.isEmpty(token)) {
                return HttpResult.error(HttpStatus.SC_UNAUTHORIZED, "鉴权失败");
            }

            LoginResult loginResult = LoginResult.builder().id(userMeta.getPrincipalId()).name(userMeta.getPrincipalName())
                    .role(StringUtils.join(userMeta.getRoles())).token(token).build();

            return HttpResult.data(loginResult);
        } catch (Exception e) {
            log.error("鉴权失败", e);
            return HttpResult.error(HttpStatus.SC_UNAUTHORIZED, "鉴权失败");
        }


    }

    @ApiOperation(value = "退出接口", notes = "移除相关的token数据")
    @PostMapping(value = "/logout")
    public HttpResult logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return HttpResult.ok("退出成功");
    }


}

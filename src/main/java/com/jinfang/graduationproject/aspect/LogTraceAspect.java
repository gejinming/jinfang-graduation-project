package com.jinfang.graduationproject.aspect;

import com.alibaba.fastjson.JSONObject;
import com.jinfang.graduationproject.util.HttpUtils;
import com.jinfang.graduationproject.util.IPUtils;
import com.jinfang.graduationproject.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * 系统日志，切面处理类，记录日志
 */
@Slf4j
@Aspect
@Component
public class LogTraceAspect {

    @Pointcut("execution(public * com.jinfang.graduationproject.service..*.*(..))")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long cost = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveSysLog(point, cost);
        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long cost) {
        String userName = SecurityUtils.getUsername();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        JSONObject loggerTrace = new JSONObject();

//		Method method = signature.getMethod();
//		com.louis.merak.admin.annotation.SysLog syslogAnno = method.getAnnotation(com.louis.merak.admin.annotation.SysLog.class);
//		if(syslogAnno != null){
//			//注解上的描述
//			sysLog.setOperation(syslogAnno.value());
//		}

        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        loggerTrace.put("method", className + "." + methodName + "()");

        // 请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = JSONObject.toJSONString(args[0]);
            if (params.length() > 200) {
                params = params.substring(0, 200) + "...";
            }
            loggerTrace.put("params", params);
        } catch (Exception e) {
        }

        // 获取request
        HttpServletRequest request = HttpUtils.getHttpServletRequest();
        // 设置IP地址
        loggerTrace.put("ip", IPUtils.getIpAddr(request));

        // 用户名
        loggerTrace.put("username", userName);
        loggerTrace.put("cost", cost);

        log.info(loggerTrace.toJSONString());
    }


}

package com.mu.demo.advice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author guozhengMu
 * @version 1.0
 * @date 2020/10/16 14:16
 * @description
 * @modify
 */

@Aspect
@Component
@Order(0)
@Slf4j
public class PermissionSecondAdvice {
    @Pointcut("@annotation(com.mu.demo.annotation.DeleteAnnotation)")
    private void permissionCheck() {
    }

    @Around("permissionCheck()")
    public Object permissionCheckSecond(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("===================第二个切面===================：" + System.currentTimeMillis());
        // 获取签名
        Signature signature = joinPoint.getSignature();
        // 获取切入的包名
        String declaringTypeName = signature.getDeclaringTypeName();
        // 获取即将执行的方法名
        String funcName = signature.getName();
        log.info("执行方法为: {}，属于{}包", funcName, declaringTypeName);

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        Map map = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Object id = map.get("id");
        if (Integer.parseInt(id.toString()) < 0) {
            return JSON.parseObject("{\"message\":\"id非法\",\"code\":403}");
        }

        return joinPoint.proceed();
    }
}

package com.onboarding.api.controller.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class InfoResponseAspect {

    @Around("@annotation(InfoResponseInterceptor)")
    public Object intercept(ProceedingJoinPoint joinPoint) throws Throwable {
        Long time = System.currentTimeMillis();
        ResponseEntity response = (ResponseEntity) joinPoint.proceed();
        HttpHeaders headers = new HttpHeaders();
        headers.add("time",String.valueOf(System.currentTimeMillis()-time));
        return new ResponseEntity<>(response.getBody(),headers,response.getStatusCode());
    }
}

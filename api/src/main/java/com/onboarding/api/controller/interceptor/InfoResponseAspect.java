package com.onboarding.api.controller.interceptor;

import com.onboarding.api.controller.exception.QueryLimitExceededException;
import com.onboarding.api.service.WaiterService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Aspect
@Component
@RequiredArgsConstructor
public class InfoResponseAspect {

    private final Environment env;

    private final WaiterService waiterService;

    @Around("@annotation(InfoResponseInterceptor)")
    public Object intercept(ProceedingJoinPoint joinPoint) throws Throwable {
        Long time = System.currentTimeMillis();
        if(waiterService.getQueryCount()==Integer.parseInt(env.getProperty("query.limit")))
            throw new QueryLimitExceededException("El mozo excedio las consultas");
        waiterService.addQuery();
        ResponseEntity response = (ResponseEntity) joinPoint.proceed();
        HttpHeaders headers = new HttpHeaders();
        headers.add("time",String.valueOf(System.currentTimeMillis()-time));
        headers.add("waiter", waiterService.getName());
        headers.add("queryCount",String.valueOf(waiterService.getQueryCount()));
        return new ResponseEntity<>(response.getBody(),headers,response.getStatusCode());
    }
}

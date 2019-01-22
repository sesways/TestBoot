package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class TestAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut(value = "execution(* com.example.*.*.*(..))")
    public void print(){}

    @Before("print()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("<=====这是一个前置切入点-begin=====>");
        logger.info("info:" + request.getRequestURI());
        logger.info("<=====这是一个前置切入点-end=====>");
    }
}

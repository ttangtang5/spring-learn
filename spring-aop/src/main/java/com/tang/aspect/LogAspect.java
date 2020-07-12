package com.tang.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author tang
 * @Date 2020/7/4 11:01
 * @Version 1.0
 **/
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com.tang.service.UserService.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println("log before");
    }

    @After("pointCut()")
    public void after(){
        System.out.println("log after");
    }

    @Around("pointCut()")
    public void around(JoinPoint joinPoint, ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("log around before");
        proceedingJoinPoint.proceed();
        System.out.println("log around after");
    }
}

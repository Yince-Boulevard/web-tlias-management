package com.yince.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 演示五种通知类型
 * @author yince boulevard
 */
@Slf4j
//@Aspect
@Component
public class MyAspect1 {

    // 切入点表达式
    @Pointcut("execution(* com.yince.service.impl.*.*(..))")
    private void pt(){}

    /**
     * 前置通知 - 在目标方法运行之前执行
     * @param
     * @return
     */
    @Before("pt()")
    public void before(){
        log.info("@Before 前置通知....");
    }

    /**
     * 环绕通知 - 在目标方法运行 之前 和 之后 执行
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("@Around 环绕通知 before ....");
        Object result = joinPoint.proceed();
        log.info("@Around 环绕通知 after ....");
        return result;
    }

    /**
     * 后置通知 - 在目标方法运行之后执行（无论正常结束还是异常结束）
     * @param
     * @return
     */
    @After("pt()")
    public void after(){
        log.info("@After 后置通知....");
    }

    /**
     * 后置通知 - 在目标方法运行之后执行（正常返回后才会通知，出现异常不会允许）
     * @param
     * @return
     */
    @AfterReturning("pt()")
    public void afterReturning(){
        log.info("@AfterReturning 返回后通知....");
    }

    /**
     * 异常通知 - 在目标方法运行之后执行（出现异常才会允许）
     * @param
     * @return
     */
    @AfterThrowing("pt()")
    public void afterThrowing(){
        log.info("@AfterThrowing 异常后通知....");
    }
}

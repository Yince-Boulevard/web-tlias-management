package com.yince.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 切面类
 * @author yince boulevard
 * @date 2025-3-4 21:27:04
 */
@Slf4j
//@Aspect // 标识为切面(AOP)类
@Component
public class RecordTimeAspect {

    @Around("execution(* com.yince.service.impl.*.*(..))")  // 应用于 com.yince.service.impl包下所有类的所有方法
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        // 1. 记录开始时间
        long begin = System.currentTimeMillis();
        // 2. 执行原始方法
        Object result = pjp.proceed();
        // 3. 记录结束时间
        long end = System.currentTimeMillis();
        // 4. 计算方法执行时长
        log.info("方法{} 执行时长：{}ms",pjp.getSignature(), end - begin);
        return result;
    }
}

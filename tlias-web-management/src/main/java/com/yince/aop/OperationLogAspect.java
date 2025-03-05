package com.yince.aop;

import com.yince.mapper.OperateLogMapper;
import com.yince.pojo.OperateLog;
import com.yince.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.yince.anno.Log)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 获取操作人ID、类名、方法名、方法参数
        int operateEmpId = getCurrentUserId(); // 假设操作人ID为1，实际应用中应从上下文中获取
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String methodParams = Arrays.toString(joinPoint.getArgs());

        // 记录开始时间
        LocalDateTime startTime = LocalDateTime.now();

        // 执行目标方法
        Object result = joinPoint.proceed();

        // 记录结束时间
        LocalDateTime endTime = LocalDateTime.now();

        // 计算操作耗时
        long costTime = java.time.Duration.between(startTime, endTime).toMillis();

        // 记录操作时间
        String operateTime = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // 转换为LocalDateTime对象
        LocalDateTime operateTimeObj = LocalDateTime.parse(operateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


        // 封装操作日志对象
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(operateEmpId);
        operateLog.setOperateTime(operateTimeObj);
        operateLog.setClassName(className);
        operateLog.setMethodName(methodName);
        operateLog.setMethodParams(methodParams);
        operateLog.setReturnValue(result.toString());
        operateLog.setCostTime(costTime);
        // 将操作日志插入数据库
        log.info("操作日志：{}", operateLog);
        operateLogMapper.insert(operateLog);

        return result;
    }

    private Integer getCurrentUserId() {
        // 1. 获取token
        return CurrentHolder.getCurrentId();
    }
}

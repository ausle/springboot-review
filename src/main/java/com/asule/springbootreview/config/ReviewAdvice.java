package com.asule.springbootreview.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
@Slf4j
public class ReviewAdvice {

    // 定义一个切入点，声明对哪些方法进行拦截处理
    @Pointcut(value = "execution( * com.asule.springbootreview.*.*(..))")
    public void point(){

    }

    @Around("point()")
    public Object logger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        ObjectMapper objectMapper = new ObjectMapper();

        log.info("调用前："+className+":"+methodName+"传递的参数为："+objectMapper.writeValueAsString(args));
        // 调用真正的业务方法
        Object proceed = proceedingJoinPoint.proceed();

        log.info("调用前："+className+":"+methodName+"返回值："+objectMapper.writeValueAsString(proceed));
        return proceed;
    }


}

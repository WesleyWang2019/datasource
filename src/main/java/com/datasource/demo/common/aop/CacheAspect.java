package com.datasource.demo.common.aop;

import com.datasource.demo.common.annotation.LocalCacheAnnotation;
import com.datasource.demo.common.cache.LocalCacheHandler;
import com.datasource.demo.util.CacheKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author Wesley Wang
 * @date 2020/3/24 13:34
 */
@Aspect
@Component
@Slf4j
public class CacheAspect {

    @Autowired
    private LocalCacheHandler localCacheHandler;

    @Pointcut("@annotation(com.datasource.demo.common.annotation.LocalCacheAnnotation)")
    public void localCache() {

    }

    @Around("localCache()")
    public Object aroundLocalCache(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        LocalCacheAnnotation annotation = getMethod(joinPoint).getAnnotation(LocalCacheAnnotation.class);
        int localExpire = annotation.localExpire();
        String prefix = annotation.keyPrefix();
        Method method = getMethod(joinPoint);
        String key = buildKey(method, prefix, args);


        return null;

    }

    private String buildKey(Method method, String keyPrefix, Object[] args) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : args) {
            if (null != obj) {
                if (obj instanceof List || obj instanceof Map) {
                    sb.append(obj.hashCode() + "_");
                } else {
                    sb.append(obj.toString() + "_");
                }
            }
        }
        String methodName = method.getName();
        String key = CacheKeyUtil.getDSKey(keyPrefix + sb.toString() + methodName);
        return key;
    }

    private Method getMethod(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature ms = (MethodSignature) signature;
        Method method = ms.getMethod();
        return method;
    }

}

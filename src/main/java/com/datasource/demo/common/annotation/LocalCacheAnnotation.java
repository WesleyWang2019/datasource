package com.datasource.demo.common.annotation;


import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalCacheAnnotation {
    int localExpire() default 3600;

    int redisExpire() default 36000;

    String keyPrefix() default "";
}

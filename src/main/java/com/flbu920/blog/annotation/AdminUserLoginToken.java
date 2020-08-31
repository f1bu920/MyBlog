package com.flbu920.blog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于登录验证
 * @Target(ElementType.METHOD):作用于方法
 * @Target(ElementType.TYPE):作用于接口、类、枚举、注解
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminUserLoginToken {
    boolean required() default true;
}

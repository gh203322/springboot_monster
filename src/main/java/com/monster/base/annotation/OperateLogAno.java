package com.monster.base.annotation;

import java.lang.annotation.*;

/**
 * @author Administrator
 * @Date 2019/6/19
 * @see
 * @系统操作日志记录 注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLogAno {

      String value() default "";
}

package com.study.spring.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * @author huweifeng
 * @version 1.0
 * @date 2022/1/18 15:07
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Configuration {
}

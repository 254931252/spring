package com.study.spring.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * @author huweifeng
 * @version 1.0
 * @date 2022/1/18 15:14
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ComponentScan {
    String value();
    
    
}

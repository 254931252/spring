package com.study.spring.springframework.stereotype;

import java.lang.annotation.*;

/**
 * @author huweifeng
 * @version 1.0
 * @date 2022/1/18 14:54
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
    String value();
}

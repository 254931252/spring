package com.study.spring.test;

import com.study.spring.config.AppConfig;
import com.study.spring.service.UserService;
import com.study.spring.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author huweifeng
 * @version 1.0
 * @date 2022/1/18 14:40
 */
public class SpringTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = (UserService) annotationConfigApplicationContext.getBean("userService");
        userService.test();
        
    }
}

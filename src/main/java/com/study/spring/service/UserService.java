package com.study.spring.service;

import com.study.spring.springframework.context.annotation.Autowired;
import com.study.spring.springframework.context.annotation.Scope;
import com.study.spring.springframework.stereotype.Component;

/**
 * @author huweifeng
 * @version 1.0
 * @date 2022/1/18 14:50
 */
@Scope("prototype")
@Component
public class UserService {
    
    @Autowired
    private OrderService orderService;
    
    public void test(){
        System.out.println("hello userService");
        orderService.test();
    }
    
}

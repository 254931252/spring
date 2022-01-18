package com.study.spring.springframework.beans.factory.config;

import com.study.spring.springframework.context.annotation.Scope;
import com.study.spring.springframework.stereotype.Component;

/**
 * @author huweifeng
 * @version 1.0
 * @date 2022/1/18 17:24
 */
public class BeanDefinition {
    
    private Class clazz;
    
    private Scope scope;
    
    private Component component;


    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}

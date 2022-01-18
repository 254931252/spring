package com.study.spring.springframework.context.annotation;

import com.study.spring.springframework.beans.factory.config.BeanDefinition;
import com.study.spring.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author huweifeng
 * @version 1.0
 * @date 2022/1/18 14:49
 */
public class AnnotationConfigApplicationContext {

    private HashMap<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    /**
     * 单例池
     */
    private HashMap<String, Object> singletonBeans = new HashMap<>();


    public AnnotationConfigApplicationContext(Class<?> configClass) {
        // 扫描，创建beanDefinition
        scan(configClass);
        // 创建singletonBean
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Scope scope = beanDefinition.getScope();
            if (Objects.nonNull(scope) && Objects.equals("singleton", scope.value())) {
                singletonBeans.put(beanDefinition.getComponent().value(), createBean(beanDefinition.getClazz()));
            }
        });
    }

    private void scan(Class<?> configClass) {
        if (configClass.isAnnotationPresent(Configuration.class)) {
            if (configClass.isAnnotationPresent(ComponentScan.class)) {
                ComponentScan componentScan = configClass.getAnnotation(ComponentScan.class);
                String packages = componentScan.value();
                String resourceName = packages.replace(".", "/");
                ClassLoader classLoader = configClass.getClassLoader();
                URL url = classLoader.getResource(resourceName);
                File directory = new File(Objects.requireNonNull(url).getPath());
                if (directory.isDirectory()) {
                    // 遍历扫描包下的文件
                    for (File file : Objects.requireNonNull(directory.listFiles())) {
                        try {
                            int index1 = file.getAbsolutePath().indexOf("com");
                            int index2 = file.getAbsolutePath().lastIndexOf(".class");

                            Class<?> aClass = classLoader.loadClass(file.getAbsolutePath().replace("\\", ".").substring(index1, index2));
                            // 判断是否含有@Component注解
                            if (aClass.isAnnotationPresent(Component.class)) {
                                Component component = aClass.getAnnotation(Component.class);
                                String beanName = component.value();
                                BeanDefinition beanDefinition = createBeanDefinition(aClass);
                                beanDefinitionMap.put(beanName, beanDefinition);
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
    }

    private BeanDefinition createBeanDefinition(Class<?> clazz) {
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setClazz(clazz);
        if (clazz.isAnnotationPresent(Scope.class)) {
            beanDefinition.setScope(clazz.getAnnotation(Scope.class));
        }
        if (clazz.isAnnotationPresent(Component.class)) {
            beanDefinition.setComponent(clazz.getAnnotation(Component.class));
        }
        return beanDefinition;
    }


    public Object getBean(String beanName) {
        if (!beanDefinitionMap.containsKey(beanName)) {
            throw new NullPointerException("no such beanName " + beanName);
        }
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        Scope scope = beanDefinition.getScope();
        if (Objects.nonNull(scope) && Objects.equals("singleton", scope.value())) {
            return singletonBeans.get(beanName);
        } else {
            return createBean(beanDefinition.getClazz());
        }
    }

    private Object createBean(Class<?> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("createBean failed");
    }
}

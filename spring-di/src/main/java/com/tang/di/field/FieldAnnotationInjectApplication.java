package com.tang.di.field;

import com.tang.pojo.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 手动配置
 * 字段注入：@autowired、@Resource、@inject
 * 此种方式和自动装配byName、byType没有关联，
 * 此种方式是通过注解BeanPostProcessor处理，自动装配通过AbstractAutowireCapableBeanFactory#populate方法处理
 *
 * 参考：https://www.pianshen.com/article/41211436038/
 *
 *
 */
public class FieldAnnotationInjectApplication {

    @Autowired
    private Car car;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(FieldAnnotationInjectApplication.class);
        applicationContext.refresh();

        FieldAnnotationInjectApplication fieldAnnotationInjectApplication = applicationContext.getBean(FieldAnnotationInjectApplication.class);

        System.out.println(fieldAnnotationInjectApplication.car);
    }

    @Bean
    public Car car() {
        return new Car(1L, "mycar");
    }
}

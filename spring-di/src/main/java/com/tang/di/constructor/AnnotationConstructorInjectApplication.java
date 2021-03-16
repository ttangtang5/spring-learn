package com.tang.di.constructor;

import com.tang.pojo.Car;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 手动装配
 * 注解 实现constructor注入
 */
public class AnnotationConstructorInjectApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationConstructorInjectApplication.class);
        applicationContext.refresh();

        Car car = applicationContext.getBean(Car.class);

        System.out.println("car = " + car);
    }

    @Bean
    public Car car() {
        Car car = new Car(1L, "mycar");
        return car;
    }
}

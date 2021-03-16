package com.tang.di.setter;

import com.tang.pojo.Car;
import com.tang.pojo.CarHolder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 手动装配
 * 注解 实现setter注入
 */
public class AnnotationSetterInjectApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationSetterInjectApplication.class);
        applicationContext.refresh();

        Car car = applicationContext.getBean(Car.class);

        System.out.println("car = " + car);
    }

    @Bean
    public Car car() {
        Car car = new Car();
        car.setMyId(1L);
        car.setMyName("myCar");
        return car;
    }
}

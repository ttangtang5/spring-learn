package com.tang.di.annotation;

import com.tang.pojo.Car;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(SpringConfig.class)
public class CustomAutowiredApplication {

    @CustomAutowired
    private Car car;

    @MyAutowired
    private Car car2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(CustomAutowiredApplication.class);
        applicationContext.refresh();

        CustomAutowiredApplication bean = applicationContext.getBean(CustomAutowiredApplication.class);
        System.out.println("bean = " + bean.car);
        System.out.println("bean = " + bean.car2);
    }

    @Bean
    public Car car() {
        return new Car(1L, "mycar");
    }
}

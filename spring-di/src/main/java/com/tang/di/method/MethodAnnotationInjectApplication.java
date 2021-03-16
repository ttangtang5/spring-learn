package com.tang.di.method;

import com.tang.di.aware.AwareInterfaceInjectApplication;
import com.tang.pojo.Car;
import com.tang.pojo.CarHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 手动配置
 * 方法注入:@Bean、@Autowired、@Resource
 */
public class MethodAnnotationInjectApplication {

    private Car car;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MethodAnnotationInjectApplication.class);
        applicationContext.refresh();

        CarHolder carHolder = applicationContext.getBean(CarHolder.class);

        System.out.println("carHolder = " + carHolder.getCar());

        MethodAnnotationInjectApplication injectApplication = applicationContext.getBean(MethodAnnotationInjectApplication.class);
        System.out.println("injectApplication = " + injectApplication.car);
    }

    @Bean
    public Car car() {
        return new Car(1L, "mycar");
    }

    @Bean
    public CarHolder carHolder(Car car) {
        return new CarHolder(car);
    }

    //@Autowired
    @Resource
    public void inject(Car car) {
        this.car = car;
    }
}

package com.tang.di.field;

import com.tang.pojo.Car;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * 懒加载
 * {@link Lazy} 注解实现
 * {@link ObjectFactory} 实现延迟加载 注入的时候先返回了一个代理对象 调用时才真正doResolveDependence
 * {@link org.springframework.beans.factory.ObjectProvider} 实现延迟加载  底层还是{@see ObjectFactroy} 注入的时候先返回了一个代理对象
 */
public class LazyInjectApplication {

    @Autowired
    @Lazy
    private Car car;

    @Autowired
    private ObjectFactory<Car> carObjectFactory;

    @Autowired
    private ObjectProvider<Car> carObjectProvider;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyInjectApplication.class);
        applicationContext.refresh();

        LazyInjectApplication lz = applicationContext.getBean(LazyInjectApplication.class);

        System.out.println(lz.car);
        System.out.println(lz.carObjectFactory.getObject());
        System.out.println(lz.carObjectProvider.getObject());
        for (Car car : lz.carObjectProvider) {
            System.out.println("car = " + car);
        }
    }

    @Bean
    public Car car() {
        return new Car(1L, "mycar");
    }
}

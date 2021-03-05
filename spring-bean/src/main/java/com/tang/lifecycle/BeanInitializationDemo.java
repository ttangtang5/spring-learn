package com.tang.lifecycle;

import com.tang.pojo.Car;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * bean 初始化方式
 * 1、@PostConstruct (支持xml，注解)
 * 2、实现InitializingBean
 * 3、@Bean(initMethod = "xxxx") (支持xml，注解)
 * 4、BeanDefinition#setInitMethodName bean(initMethod) API方式
 *
 * 执行顺序：PostConstruct->实现InitializingBean->initMethod
 */

public class BeanInitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanInitializationDemo.class);
        System.out.println("spring context init finsh...");
        Car bean = applicationContext.getBean(Car.class);
        System.out.println("bean = " + bean);

        applicationContext.close();
    }

    @Bean(initMethod = "initMethod")
    public Car car() {
        Car car = new Car();
        car.setId(1L);
        car.setName("tang");
        return car;
    }
}

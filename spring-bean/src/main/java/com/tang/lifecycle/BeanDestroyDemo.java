package com.tang.lifecycle;

import com.tang.pojo.Car;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * bean 销毁回调方法
 * 1、@PreDestroy (支持xml，注解)
 * 2、实现DisposableBean
 * 3、@Bean(destroyMethod = "xxxx") (支持xml，注解)
 * 4、BeanDefinition#setDestroyMethodName bean(destroyMethod) API方式
 *
 * 执行顺序：PreDestroy->实现DisposableBean->destroyMethod
 */
public class BeanDestroyDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanDestroyDemo.class);
        System.out.println("spring context init finsh...");
        Car bean = applicationContext.getBean(Car.class);
        System.out.println("bean = " + bean);

        applicationContext.close();
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public Car car() {
        Car car = new Car();
        car.setId(1L);
        car.setName("tang");
        return car;
    }
}

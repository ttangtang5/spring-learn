package com.tang.di.setter;

import com.tang.pojo.Car;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 手动装配
 * setter注入 xml配置
 *
 *
 */
public class XmlSetterInjectApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-setter-context.xml");

        Car car = applicationContext.getBean(Car.class);

        System.out.println("car = " + car);
    }
}

package com.tang.di.constructor;

import com.tang.pojo.CarHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 自动装配-Autowiring
 * constructor注入 xml配置
 */
public class XmlConstructorAutowiredInjectApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-constructor-autowired-context.xml");

        CarHolder car = applicationContext.getBean(CarHolder.class);

        System.out.println("car = " + car.getCar());
    }
}

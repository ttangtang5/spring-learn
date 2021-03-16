package com.tang.di.setter;

import com.tang.pojo.Car;
import com.tang.pojo.CarHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 自动装配-Autowiring 注入模式
 * setter注入 xml配置
 */
public class XmlSetterAutowiredInjectApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-setter-autowired-context.xml");

        CarHolder car = applicationContext.getBean(CarHolder.class);

        System.out.println("car = " + car.getCar());
    }
}

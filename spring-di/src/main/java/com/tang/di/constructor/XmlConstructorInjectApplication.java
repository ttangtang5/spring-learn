package com.tang.di.constructor;

import com.tang.pojo.Car;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 手动装配
 * constructor注入 xml配置
 */
public class XmlConstructorInjectApplication {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:/META-INF/spring-setter-context.xml");

        Car car = beanFactory.getBean(Car.class);

        System.out.println("car = " + car);
    }
}

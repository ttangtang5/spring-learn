package com.tang.registry;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.tang.pojo.Car;
import com.tang.pojo.Order;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * {@link BeanDefinition} 的注册方式
 * 1、Xml
 * 2、注解：@Bean、@Component、@Import
 * 3、api方式BeanDefinitionRegistry#registerBeanDefinition
 * 4、api方式BeanDefinitionReaderUtils#registerWithGeneratedName 通过默认方式处理beanName,底层还是调用BeanDefinitionRegistry
 * 5、api方式AnnotationConfigApplicationContext#register 可以注册配置类本身
 */
public class BeanRegisterDemo {

    public static void main(String[] args) {
        // 1、xml注册
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF/bean-register-context.xml");

        Car car = (Car) applicationContext.getBean("xml-car");
        System.out.println("car = " + car);

        // 2、注解
        AnnotationConfigApplicationContext annApplicationContext = new AnnotationConfigApplicationContext();
        annApplicationContext.register(RegisterConfig.class);
        annApplicationContext.refresh();

        Car beanCar = (Car) annApplicationContext.getBean("bean-car");
        RegisterConfig.User beanUser = (RegisterConfig.User) annApplicationContext.getBean(RegisterConfig.User.class);
        Order beanOrder = (Order) annApplicationContext.getBean(Order.class);
        System.out.println("beanCar = " + beanCar);
        System.out.println("beanUser = " + beanUser);
        System.out.println("beanOrder = " + beanOrder);

        // 3、api
        GenericApplicationContext genericApplicationContext = new GenericApplicationContext();
        registerByBeanDefinitionRegistry(genericApplicationContext, "api-car-1");
        registerByBeanDefinitionReaderUtils(genericApplicationContext);
        genericApplicationContext.refresh();
        Car apiBeanCar = genericApplicationContext.getBean(Car.class);
        Order apiBeanOrder = genericApplicationContext.getBean(Order.class);
        System.out.println("apiBeanCar = " + apiBeanCar);
        System.out.println("apiBeanOrder = " + apiBeanOrder);
    }

    /**
     * api方式BeanDefinitionRegistry#registerBeanDefinition
     * @param registry
     * @param beanName
     */
    public static void registerByBeanDefinitionRegistry(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Car.class);
        beanDefinitionBuilder.addPropertyValue("id", 1L);
        beanDefinitionBuilder.addPropertyValue("name", "api-car");
        registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
    }

    /**
     * api方式BeanDefinitionReaderUtils#registerWithGeneratedName 通过默认方式处理beanName,底层还是调用BeanDefinitionRegistry
     * @param registry
     */
    public static void registerByBeanDefinitionReaderUtils(BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Order.class);
        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
    }

}

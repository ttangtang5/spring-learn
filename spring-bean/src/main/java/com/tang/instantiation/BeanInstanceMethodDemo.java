package com.tang.instantiation;

import com.tang.factory.DefaultUserBeanFactory;
import com.tang.factory.UserBeanFactory;
import com.tang.pojo.Car;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * bean实例化的实现方式
 * 常规方式：
 * 1、构造器方式 (支持xml、注解、api)
 * 2、静态工厂方式 (支持xml、api)
 * 3、实例工厂方式(支持xml、api)
 * 4、FactoryBean方式(支持xml、注解、api) 获取本身对象bean，加上前缀“&"
 * 特殊方式：
 * 1、ServiceLoaderFactoryBean方式 底层ServiceLoader (支持xml、注解、api)
 * 2、AutowireCapableBeanFactory#createBean 不推荐使用，通过 createBean 方法可以正常进行依赖注入等，但创建的对象都是多例，而且不会注册到 Spring 容器中，通过 beanFactory.getBeanDefinitionNames() 也不查到对应的 BeanDefinition 信息。
 * 3、BeanDefinitionRegistry#registerBeanDefinition 推荐使用， 官方大量使用
 */
public class BeanInstanceMethodDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF/bean-instantiation-context.xml");

        // 1、构造器方式 (支持xml、注解、api)
        Car constructionCar = applicationContext.getBean("construction-car", Car.class);
        System.out.println("constructionCar = " + constructionCar);

        // 2、静态工厂实例化
        Car staticCar = applicationContext.getBean("static-method-car", Car.class);
        System.out.println("staticCar = " + staticCar);

        // 3、实例工厂实例化
        Car factoryCar = applicationContext.getBean("factory-car", Car.class);
        System.out.println("facrotyCar = " + factoryCar);

        // 4、FactoryBean 实例化
        Car userFactoryBean = applicationContext.getBean("userFactoryBean", Car.class);
        System.out.println("userFactoryBean = " + userFactoryBean);

        // 特殊方式-1、ServiceLoaderFactoryBean方式  ServiceLoaderFactoryBean（加载 ServiceLoader）、ServiceFactoryBean（加载单个对象）、ServiceListFactoryBean（加载全部对象）
        ServiceLoader loaderFactoryBean = applicationContext.getBean("serviceLoaderFactoryBean", ServiceLoader.class);
        Iterator iterator = loaderFactoryBean.iterator();
        while (iterator.hasNext()) {
            UserBeanFactory next = (UserBeanFactory)iterator.next();
            System.out.println("next.createBean() = " + next.createBean());
        }

        // 特殊方式-2、AutowireCapableBeanFactory#createBean
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        DefaultUserBeanFactory defaultUserBeanFactory = autowireCapableBeanFactory.createBean(DefaultUserBeanFactory.class);
        System.out.println("autowireCapableBeanFactory = " + defaultUserBeanFactory);

        // 特殊方式-3、BeanDefinitionRegistry#registerBeanDefinition
        GenericApplicationContext genericApplicationContext = new GenericApplicationContext();
        registerByBeanDefinitionRegistry(genericApplicationContext, "registryCar");
        genericApplicationContext.refresh();
        System.out.println("genericApplicationContext " + genericApplicationContext.getBean("registryCar"));

        applicationContext.close();
        genericApplicationContext.close();
    }

    /**
     * BeanDefinitionRegistry#registerBeanDefinition(String, BeanDefinition) 推荐使用， 官方大量使用
     * @param registry
     * @param beanName
     */
    public static void registerByBeanDefinitionRegistry(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Car.class);
        beanDefinitionBuilder.addPropertyValue("id", 1L);
        beanDefinitionBuilder.addPropertyValue("name", "api-car");
        registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
    }
}

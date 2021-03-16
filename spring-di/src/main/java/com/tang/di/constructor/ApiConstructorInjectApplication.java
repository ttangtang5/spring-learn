package com.tang.di.constructor;

import com.tang.pojo.Car;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 手动配置
 * 通过底层api 构建beanDefinition constructor 属性注入
 */
public class ApiConstructorInjectApplication {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AbstractBeanDefinition beanDefinition = createBeanDefinition();

        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, beanFactory);

        Car car = beanFactory.getBean(Car.class);

        System.out.println("car = " + car);
    }

    private static AbstractBeanDefinition createBeanDefinition() {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Car.class);
        builder.addConstructorArgValue(1L);
        builder.addConstructorArgValue("mycar");

        return builder.getBeanDefinition();
    }


}

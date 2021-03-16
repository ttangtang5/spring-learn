package com.tang.di.setter;

import com.tang.pojo.Car;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 手动配置
 * 通过底层api 构建beanDefinition setter 属性注入
 */
public class ApiSetterInjectApplication {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AbstractBeanDefinition beanDefinition = createBeanDefinition();

        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, beanFactory);

        Car car = beanFactory.getBean(Car.class);

        System.out.println("car = " + car);
    }

    /**
     * 通过setter方法名字设置
     * @return
     */
    private static AbstractBeanDefinition createBeanDefinition() {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Car.class);
        builder.addPropertyValue("myId", 1L);
        builder.addPropertyValue("myName", "mycar");

        return builder.getBeanDefinition();
    }


}

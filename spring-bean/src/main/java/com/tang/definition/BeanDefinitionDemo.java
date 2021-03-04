package com.tang.definition;

import com.tang.pojo.Car;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 创建BeanDefinition的方式
 * 1、BeanDefinitionBuilder构建beanDefinition
 * 2、AbstractBeanDefinition及其子类构建
 */
public class BeanDefinitionDemo {

    public static void main(String[] args) {
        // 1、BeanDefinitionBuilder构建beanDefinition
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Car.class);
        beanDefinitionBuilder.addPropertyValue("id", 1L);
        beanDefinitionBuilder.addPropertyValue("name", "car");

        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        // 2、AbstractBeanDefinition及其子类构建
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(Car.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues
                .add("id", 2L)
                .add("name", "test");
        genericBeanDefinition.setPropertyValues(propertyValues);
    }
}

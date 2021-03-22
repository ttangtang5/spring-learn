package com.tang.di.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

@Configuration
public class SpringConfig {

   /* *//**
     * 额外 添加一个AutowiredAnnotationBeanPostProcessor 处理自定义注解
     * 对于beanPost 用static标识，否则会存在一些bean无法被作用到
     * @return
     *//*
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public AutowiredAnnotationBeanPostProcessor annotationBeanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(CustomAutowired.class);

        return beanPostProcessor;
    }*/

    /**
     * 此种写法会覆盖Spring 内置的AutowiredAnnotationBeanPostProcessor，
     * 需自行把原有注解添加进去， 不建议此种写法， 原有本身对Inject注解又判断校验、还需主要原有优先级
     *
     * 对于beanPost 用static标识，否则会存在一些bean无法被作用到 {@link "https://blog.csdn.net/f641385712/category_10060040.html?spm=1001.2014.3001.5482"}
     *
     * Spring 注入内置beanPost {@link AnnotationConfigUtils#registerAnnotationConfigProcessors}
     * @return
     */
    @Bean(value = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
    @Order(Ordered.LOWEST_PRECEDENCE - 2)
    public static AutowiredAnnotationBeanPostProcessor annotationBeanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(4);
        autowiredAnnotationTypes.add(Autowired.class);
        autowiredAnnotationTypes.add(Value.class);
        //autowiredAnnotationTypes.add(Inject.class);
        autowiredAnnotationTypes.add(CustomAutowired.class);

        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);

        return beanPostProcessor;
    }
}

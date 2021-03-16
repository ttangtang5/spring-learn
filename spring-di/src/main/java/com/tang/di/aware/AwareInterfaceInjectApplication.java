package com.tang.di.aware;

import com.tang.di.setter.AnnotationSetterInjectApplication;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 手动配置
 * 通过aware接口实现，进行注入 不限于注入方法本身参数，可以从容器中获取赋值给属性
 * 不限于BeanFactoryAware接口
 */
public class AwareInterfaceInjectApplication implements BeanFactoryAware {

    private BeanFactory beanFactory;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AwareInterfaceInjectApplication.class);
        applicationContext.refresh();

        AwareInterfaceInjectApplication bean = applicationContext.getBean(AwareInterfaceInjectApplication.class);

        System.out.println(bean.beanFactory == applicationContext.getBeanFactory());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}

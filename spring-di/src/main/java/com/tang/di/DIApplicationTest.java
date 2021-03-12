package com.tang.di;

import com.tang.di.api.UserControllerByConstruction;
import com.tang.di.api.UserControllerBySetter;
import com.tang.di.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @Description 依赖注入示例
 *  依赖注入，依赖被动提供，不依赖容器API
 * @Author tang
 * @Date 2020/6/25 10:27
 * @Version 1.0
 **/
public class DIApplicationTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
//
        //UserControllerByConstruction userControllerByConstruction = (UserControllerByConstruction) applicationContext.getBean("userControllerByConstruction");
        //userControllerByConstruction.test();
//
        //UserControllerBySetter userControllerBySetter = (UserControllerBySetter) applicationContext.getBean("userControllerBySetter");
        //userControllerBySetter.test();
    }

}

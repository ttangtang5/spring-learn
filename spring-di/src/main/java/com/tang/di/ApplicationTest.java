package com.tang.di;

import com.tang.di.api.UserController;
import com.tang.di.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description
 * @Author tang
 * @Date 2020/6/25 10:27
 * @Version 1.0
 **/
public class ApplicationTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
        UserController userController = (UserController) applicationContext.getBean("userController");
        UserController userController1 = (UserController) applicationContext.getBean("userController");
        UserController userController2 = (UserController) applicationContext.getBean("userController");
        userController.test();
        userController1.test();
        userController2.test();
    }
}

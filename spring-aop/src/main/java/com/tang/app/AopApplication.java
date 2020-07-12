package com.tang.app;

import com.tang.config.SpringContext;
import com.tang.controller.UserController;
import com.tang.service.UserService;
import com.tang.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description
 * @Author tang
 * @Date 2020/7/4 10:57
 * @Version 1.0
 **/
public class AopApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringContext.class);
        /*UserController userController = applicationContext.getBean(UserController.class);
        userController.insert();*/
        /**
         * 下面在@EnableAspectJAutoProxy(proxyTargetClass = false) 时会报错。此时使用jdk动态代理，jdk动态代理通过接口实现。
         * 为true时，使用的cglib动态代理，cglib是不需要接口实现。
         * 最后生成的对象为接口类和proxy的子类
         * UserService userService = applicationContext.getBean(UserService.class);
         * 具体验证可通过在bean名称获取，会出现类型转型失败。
         * UserService userService = (UserServiceImpl) applicationContext.getBean("userServiceImpl");
         *
         */
        UserService userService = (UserServiceImpl) applicationContext.getBean("userServiceImpl");
        userService.insert();
    }

}

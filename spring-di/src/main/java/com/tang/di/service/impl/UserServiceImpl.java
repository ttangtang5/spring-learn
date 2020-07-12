package com.tang.di.service.impl;

import com.tang.di.service.UserService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Description
 * @Author tang
 * @Date 2020/6/25 10:14
 * @Version 1.0
 **/
@Service
@Scope("prototype")
public class UserServiceImpl implements UserService{

    public void test() {
        System.out.println("test");
    }

    @PostConstruct
    public void init(){

    }

    @PreDestroy
    public void destroy(){

    }
}

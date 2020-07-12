package com.tang.service.impl;

import com.tang.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author tang
 * @Date 2020/7/4 10:54
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    public void insert() {
        System.out.println("common insert");
    }

    public void insert(String str) {
        System.out.println(str + " insert");
    }
}

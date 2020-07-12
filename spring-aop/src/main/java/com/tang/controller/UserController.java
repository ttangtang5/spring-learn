package com.tang.controller;

import com.tang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Description
 * @Author tang
 * @Date 2020/7/4 10:55
 * @Version 1.0
 **/
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public void insert() {
        userService.insert();
        userService.insert("args");
    }
}

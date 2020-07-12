package com.tang.di.api;

import com.tang.di.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @Description
 * @Author tang
 * @Date 2020/6/25 10:14
 * @Version 1.0
 **/
@Controller
public abstract class UserController {

    /*@Resource(name = "userServiceImpl")
    private UserService userService1;*/

    public void test() {
        System.out.println(getUserService().hashCode());
        //userService1.test();
    }

    @Lookup
    protected abstract UserService getUserService();

    /*public UserController(UserService userService) {
        this.userService1 = userService;
    }*/

    /*public void setUserService222(UserService userService222) {
        this.userService1 = userService222;
    }*/
}

package com.tang.di.api;

import com.tang.di.service.UserService;
import org.springframework.stereotype.Controller;

/**
 * @Description setter注入：根据setter方法名称注入
 *
 * @Author tang
 * @Date 2020/6/25 10:14
 * @Version 1.0
 **/
//@Controller
public class UserControllerBySetter {

    /*@Resource(name = "userServiceImpl")*/
    private UserService userService1;

    public void test() {
        //System.out.println(getUserService().hashCode());
        userService1.test();
    }

   /*
    public abstract class UserController
    @Lookup
    protected abstract UserService getUserService();*/

    public void setUserService3(UserService userService222) {
        this.userService1 = userService222;
    }
}

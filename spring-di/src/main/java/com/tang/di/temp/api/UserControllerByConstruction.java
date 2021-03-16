package com.tang.di.temp.api;

import com.tang.di.temp.service.UserService;

/**
 * @Description 构造器注入：根据构造器参数名称查找注入
 *
 * @Author tang
 * @Date 2020/6/25 10:14
 * @Version 1.0
 **/
//@Controller
public class UserControllerByConstruction {

    private UserService userService1;

    public void test() {
        //System.out.println(getUserService().hashCode());
        userService1.test();
    }

   /*
    public abstract class UserController
    @Lookup
    protected abstract UserService getUserService();*/

    public UserControllerByConstruction(UserService userService3) {
        this.userService1 = userService3;
    }

}

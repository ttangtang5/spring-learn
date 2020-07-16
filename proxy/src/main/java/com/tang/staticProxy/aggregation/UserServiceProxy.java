package com.tang.staticProxy.aggregation;

public class UserServiceProxy implements UserService {

    private UserService userService;

    public UserServiceProxy(UserService userService) {
        this.userService = userService;
    }

    public void insert() {
        System.out.println("proxy");
        userService.insert();
    }
}

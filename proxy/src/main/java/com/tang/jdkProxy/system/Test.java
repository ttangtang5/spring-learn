package com.tang.jdkProxy.system;

public class Test {

    public static void main(String[] args) {
        ProxyHandler proxyHandler = new ProxyHandler();
        UserService userService = (UserService) proxyHandler.bind(new UserServiceImpl());
        userService.insert();
    }
}

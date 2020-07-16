package com.tang.staticProxy.aggregation;

public class Test {

    public static void main(String[] args) {
        /**
         * 静态代理通过聚合实现
         * 聚合：一种包含关系，A类包含B类对象，B类对象不依赖与A类的生命周期。即B类的对象创建并不是再A类里面改创建。
         * 需求代理复杂后也会产出复杂的代理逻辑，即深层次的代理链
         */
        UserService userService = new UserServiceImpl();
        UserService userServiceProxy = new UserServiceProxy(userService);
        userServiceProxy.insert();
    }
}

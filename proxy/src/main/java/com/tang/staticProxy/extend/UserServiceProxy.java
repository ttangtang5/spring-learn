package com.tang.staticProxy.extend;

public class UserServiceProxy extends  UserService {

    @Override
    public void insert() {
        System.out.println("proxy");
        super.insert();
    }
}

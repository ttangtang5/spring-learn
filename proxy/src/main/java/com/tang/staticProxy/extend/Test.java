package com.tang.staticProxy.extend;

public class Test {

    public static void main(String[] args) {
        /**
         * 静态代理  通过继承代理
         * 当代理需求复杂，编码有需要遵循单一职责原则，会造成深层次的继承链，而且代理顺序不同，继承类也会不一样
         * 产生大量的类。
         */
        UserServiceProxy userServiceProxy = new UserServiceProxy();
        userServiceProxy.insert();
    }
}

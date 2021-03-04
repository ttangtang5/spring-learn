package com.tang.factory;

import com.tang.pojo.Car;

public class DefaultUserBeanFactory implements UserBeanFactory {

    @Override
    public Car createBean() {
        Car car = new Car();
        car.setId(1L);
        car.setName("static-factory-car");
        return car;
    }
}

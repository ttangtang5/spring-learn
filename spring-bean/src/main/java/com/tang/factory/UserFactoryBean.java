package com.tang.factory;

import com.tang.pojo.Car;
import org.springframework.beans.factory.FactoryBean;

/**
 * 通过FactoryBean 实例化
 */
public class UserFactoryBean implements FactoryBean<Car> {

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        car.setId(1L);
        car.setName("factroyBean-car");
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }
}

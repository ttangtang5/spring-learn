package com.tang.registry;

import com.tang.pojo.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * 2、注解：@Import
 */
@Import(RegisterConfig.User.class)
@ComponentScan(basePackages = {"com.tang.pojo"})
public class RegisterConfig {

    /**
     * 2、注解：@Bean
     * @return
     */
    @Bean(value = {"bean-car"})
    public Car car() {
        Car car = new Car();
        car.setId(1L);
        car.setName("car");
        return car;
    }

    public static class User {

    }
}

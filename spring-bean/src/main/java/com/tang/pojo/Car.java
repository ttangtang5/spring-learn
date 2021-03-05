package com.tang.pojo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Car implements InitializingBean, DisposableBean {

    private Long id;

    private String name;

    public Car() {
    }

    public Car(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static Car createUser() {
        Car car = new Car();
        car.setId(1L);
        car.setName("static-method-car");
        return car;
    }

    @PostConstruct
    public void init() {
        System.out.println("car PostConstruct init...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("car initializingBean init...");
    }

    public void initMethod() {
        System.out.println("car initMethod init...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("car preDestroy destroy...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("car disposableBean destroy...");
    }

    public void destroyMethod() {
        System.out.println("car destroyMethod init...");
    }
}

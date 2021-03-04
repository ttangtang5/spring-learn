package com.tang.pojo;

public class Car {

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
}

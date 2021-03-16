package com.tang.pojo;

public class Car {

    private Long id;

    private String name;

    public Car() {
    }

    public Car(Long myId, String myName) {
        this.id = myId;
        this.name = myName;
    }

    public void setMyId(Long id) {
        this.id = id;
    }

    public void setMyName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

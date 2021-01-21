package com.tang.dl.pojo;

import com.tang.dl.annotation.SpecialCar;

@SpecialCar
public class TruckCar extends Car {

    private Long loadWeight;

    public Long getLoadWeight() {
        return loadWeight;
    }

    public void setLoadWeight(Long loadWeight) {
        this.loadWeight = loadWeight;
    }

    @Override
    public String toString() {
        return "TruckCar{" +
                "loadWeight=" + loadWeight +
                "} " + super.toString();
    }
}

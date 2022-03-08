package com.zenhomes.Consumption.entity;


public class ConsumptionData {
    Double consumption;
    String villageName;

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(final Double consumption) {
        this.consumption = consumption;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(final String villageName) {
        this.villageName = villageName;
    }

    public ConsumptionData(final Double consumption, final String villageName) {
        this.consumption = consumption;
        this.villageName = villageName;
    }

    public ConsumptionData(){}
}

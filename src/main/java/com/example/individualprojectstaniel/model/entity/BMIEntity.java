package com.example.individualprojectstaniel.model.entity;

public class BMIEntity extends BaseEntity{
    private Double height;
    private Double weight;
    private Double bmi;

    public BMIEntity() {
    }


    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }
}

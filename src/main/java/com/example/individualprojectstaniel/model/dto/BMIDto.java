package com.example.individualprojectstaniel.model.dto;

public class BMIDto {
    private Double height;
    private Double weight;

//    @NotNull
//    @Min(value = 100, message = "Weight must be higher than 10")
    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
//    @NotNull
//    @Min(value = 10, message = "Weight must be higher than 10")
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}

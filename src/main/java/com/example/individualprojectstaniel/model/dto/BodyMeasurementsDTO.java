package com.example.individualprojectstaniel.model.dto;

import com.example.individualprojectstaniel.model.entity.BodyMeasurementsEntity;
import com.example.individualprojectstaniel.model.entity.UserEntity;

import java.time.LocalDate;

public class BodyMeasurementsDTO {
    private BodyMeasurementsEntity bodyMeasurements;
    private UserEntity user;
    private LocalDate date;
    private Integer height;
    private Integer weight;
    private String bodyFat;
    private String waistCircumference;


    public BodyMeasurementsDTO() {
    }


    public BodyMeasurementsDTO(BodyMeasurementsEntity bodyMeasurements, UserEntity user, LocalDate date, Integer height, Integer weight, String bodyFat, String waistCircumference) {
        this.bodyMeasurements = bodyMeasurements;
        this.user = user;
        this.date = date;
        this.height = height;
        this.weight = weight;
        this.bodyFat = bodyFat;
        this.waistCircumference = waistCircumference;
    }

    public BodyMeasurementsEntity getBodyMeasurements() {
        return bodyMeasurements;
    }

    public void setBodyMeasurements(BodyMeasurementsEntity bodyMeasurements) {
        this.bodyMeasurements = bodyMeasurements;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(String bodyFat) {
        this.bodyFat = bodyFat;
    }

    public String getWaistCircumference() {
        return waistCircumference;
    }

    public void setWaistCircumference(String waistCircumference) {
        this.waistCircumference = waistCircumference;
    }
}

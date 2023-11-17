package com.example.individualprojectstaniel.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "body_measurements")
public class BodyMeasurementsEntity extends BaseEntity {
    private UserEntity user;
    private LocalDate date;
    private Integer height;
    private Integer weight;
    private String bodyFat;
    private String waistCircumference;

    public BodyMeasurementsEntity() {
    }

    @ManyToOne
    @NotNull(message = "User is required")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @NotNull(message = "Date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @NotNull(message = "Height is required")
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @NotNull(message = "Weight is required")
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Pattern(regexp = "^\\d{1,2}(\\.\\d{1,2})?$", message = "Body fat must have up to 2 decimal places")
    public String getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(String bodyFat) {
        this.bodyFat = bodyFat;
    }

    @Pattern(regexp = "^\\d{1,3}(\\.\\d{1,2})?$", message = "Waist circumference must have up to 2 decimal places")
    public String getWaistCircumference() {
        return waistCircumference;
    }

    public void setWaistCircumference(String waistCircumference) {
        this.waistCircumference = waistCircumference;
    }
}

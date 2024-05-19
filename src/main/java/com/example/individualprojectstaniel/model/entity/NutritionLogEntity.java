package com.example.individualprojectstaniel.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Entity
@Table(name = "nutrition_logs")
public class NutritionLogEntity extends BaseEntity{
    private UserEntity user;
    private String mealDetails;

    @Getter
    private int calories;
    @Getter
    private double protein;
    @Getter
    private double fat;
    @Getter
    private double carbohydrates;
    @Getter
    private LocalTime time;
    private LocalDate date;

    public NutritionLogEntity() {
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }


    @NotNull(message = "Date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getDate() {
        return date;
    }


    @Column(name = "meal_details")
    @NotNull(message = "Meal details are required")
    public String getMealDetails() {
        return mealDetails;
    }

}

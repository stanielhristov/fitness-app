package com.example.individualprojectstaniel.model.dto;

import com.example.individualprojectstaniel.model.entity.NutritionLogEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class NutritionLogDTO {
    private Long id;
    private String mealDetails;
    private int calories;
    private double protein;
    private double fat;
    private double carbohydrates;
    private LocalDate date;
    private LocalTime time;

    public NutritionLogDTO() {
    }

    public NutritionLogDTO(Long id, String mealDetails, int calories, double protein, double fat, double carbohydrates, LocalDate date, LocalTime time) {
        this.id= id;
        this.mealDetails = mealDetails;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.date = date;
        this.time = time;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @NotNull
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @NotNull
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @NotEmpty
    public String getMealDetails() {
        return mealDetails;
    }

    public void setMealDetails(String mealDetails) {
        this.mealDetails = mealDetails;
    }
}
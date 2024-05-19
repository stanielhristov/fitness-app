package com.example.individualprojectstaniel.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class NutritionLogDTO {
    private Long id;
    private Long userId;

    private String mealDetails;
    private int calories;
    private double protein;
    private double fat;
    private double carbohydrates;
    private LocalDate date;
    private LocalTime time;

    public NutritionLogDTO() {
    }
}
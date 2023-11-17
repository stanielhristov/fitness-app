package com.example.individualprojectstaniel.model.dto;

import com.example.individualprojectstaniel.model.entity.GoalsEntity;
import com.example.individualprojectstaniel.model.entity.UserEntity;

import java.time.LocalDate;

public class GoalsDTO {
    private GoalsEntity id;
    private UserEntity user;
    private String description;
    private Integer targetWeight;
    private Integer targetBodyFatPercentage;
    private LocalDate targetDate;

    public GoalsDTO() {
    }

    public GoalsEntity getId() {
        return id;
    }

    public void setId(GoalsEntity id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(Integer targetWeight) {
        this.targetWeight = targetWeight;
    }

    public Integer getTargetBodyFatPercentage() {
        return targetBodyFatPercentage;
    }

    public void setTargetBodyFatPercentage(Integer targetBodyFatPercentage) {
        this.targetBodyFatPercentage = targetBodyFatPercentage;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }
}

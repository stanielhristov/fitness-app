package com.example.individualprojectstaniel.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
@Table(name = "goals")
public class GoalsEntity extends BaseEntity {
    private UserEntity user;
    private String description;
    private Integer targetWeight;
    private Integer targetBodyFatPercentage;
    private LocalDate targetDate;

    public GoalsEntity() {
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column
    @NotNull(message = "Target weight is required")
    @Positive(message = "Target weight must be a positive number")
    public Integer getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(Integer targetWeight) {
        this.targetWeight = targetWeight;
    }

    @Column
    @NotNull(message = "Target body fat percentage is required")
    @Positive(message = "Target body fat percentage must be a positive number")
    public Integer getTargetBodyFatPercentage() {
        return targetBodyFatPercentage;
    }

    public void setTargetBodyFatPercentage(Integer targetBodyFatPercentage) {
        this.targetBodyFatPercentage = targetBodyFatPercentage;
    }

    @NotNull(message = "Target date is required")
    @FutureOrPresent(message = "Target date must be in the future or present")
    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }
}

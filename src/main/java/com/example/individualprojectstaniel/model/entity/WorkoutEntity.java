package com.example.individualprojectstaniel.model.entity;

import com.example.individualprojectstaniel.model.enums.WorkoutIntensityEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "workouts")
public class WorkoutEntity extends BaseEntity {
    private String name;
    private String description;
    private Integer durationTime;
    private WorkoutIntensityEnum intensity;

    public WorkoutEntity() {
    }

    @NotBlank(message = "Name is required")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank(message = "Description is required")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "duration_time")
    @NotNull(message = "Duration time is required")
    @Min(value = 1, message = "Duration time must be at least 1 minute")
    @Max(value = 1440, message = "Duration time cannot exceed 1440 minutes (1 day)")
    public Integer getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(Integer durationTime) {
        this.durationTime = durationTime;
    }

    @Column(name = "intesity")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Intensity is required")
    public WorkoutIntensityEnum getIntensity() {
        return intensity;
    }

    public void setIntensity(WorkoutIntensityEnum intensity) {
        this.intensity = intensity;
    }
}

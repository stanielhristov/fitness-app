package com.example.individualprojectstaniel.model.dto;

import com.example.individualprojectstaniel.model.entity.WorkoutEntity;
import com.example.individualprojectstaniel.model.enums.WorkoutIntensityEnum;

public class WorkoutDTO {
    private WorkoutEntity id;
    private String name;
    private String description;
    private int durationTime;
    private WorkoutIntensityEnum intensity;

    public WorkoutDTO() {
    }

    public WorkoutDTO(WorkoutEntity id, String name, String description, int durationTime, WorkoutIntensityEnum intensity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationTime = durationTime;
        this.intensity = intensity;
    }

    public WorkoutEntity getId() {
        return id;
    }

    public void setId(WorkoutEntity id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(Integer durationTime) {
        this.durationTime = durationTime;
    }

    public WorkoutIntensityEnum getIntensity() {
        return intensity;
    }

    public void setIntensity(WorkoutIntensityEnum intensity) {
        this.intensity = intensity;
    }
}

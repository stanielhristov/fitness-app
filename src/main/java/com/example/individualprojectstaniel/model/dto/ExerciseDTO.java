package com.example.individualprojectstaniel.model.dto;

import com.example.individualprojectstaniel.model.enums.ExerciseCategoryEnum;

public class ExerciseDTO {
    private Long id;
    private String name;
    private ExerciseCategoryEnum category;
    private String description;
    private String videoDemo;
    private String equipmentNeeded;


    public ExerciseDTO() {
    }

    public ExerciseDTO(String name, ExerciseCategoryEnum category, String description, String videoDemo, String equipmentNeeded) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.videoDemo = videoDemo;
        this.equipmentNeeded = equipmentNeeded;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExerciseCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(ExerciseCategoryEnum category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoDemo() {
        return videoDemo;
    }

    public void setVideoDemo(String videoDemo) {
        this.videoDemo = videoDemo;
    }

    public String getEquipmentNeeded() {
        return equipmentNeeded;
    }

    public void setEquipmentNeeded(String equipmentNeeded) {
        this.equipmentNeeded = equipmentNeeded;
    }
}
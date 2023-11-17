package com.example.individualprojectstaniel.model.entity;

import com.example.individualprojectstaniel.model.enums.ExerciseCategoryEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "exercises")
public class ExerciseEntity extends BaseEntity {
    private String name;
    private ExerciseCategoryEnum category;
    private String description;
    private String videoDemo;
    private String equipmentNeeded;

    public ExerciseEntity() {
    }

    @Column
    @NotBlank(message = "Name is required")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Category is required")
    public ExerciseCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(ExerciseCategoryEnum category) {
        this.category = category;
    }

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Description is required")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column
    @URL(message = "Video demo URL is not valid")
    public String getVideoDemo() {
        return videoDemo;
    }

    public void setVideoDemo(String videoDemo) {
        this.videoDemo = videoDemo;
    }

    @Column(name = "equipment_needed")
    @NotBlank(message = "Equipment needed is required")
    public String getEquipmentNeeded() {
        return equipmentNeeded;
    }

    public void setEquipmentNeeded(String equipmentNeeded) {
        this.equipmentNeeded = equipmentNeeded;
    }
}

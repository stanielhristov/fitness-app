package com.example.individualprojectstaniel.model.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ProgressPhotoDTO {
    private Long id;
    private Long userId;
    private LocalDate date;
    private String imageFile;
    private String description;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

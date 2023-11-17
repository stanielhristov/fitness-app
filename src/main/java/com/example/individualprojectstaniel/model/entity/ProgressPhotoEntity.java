package com.example.individualprojectstaniel.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "progress_photos")
public class ProgressPhotoEntity extends BaseEntity {
    private UserEntity user;
    private LocalDate date;
    private String imageFile;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Column(name = "date", nullable = false)
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    @Column(name = "image_file", nullable = false)
    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

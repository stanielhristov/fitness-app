package com.example.individualprojectstaniel.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.hibernate.jdbc.Work;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "workout_logs")
public class WorkoutLogEntity extends BaseEntity {
    private UserEntity user;
    private LocalDate date;
    private LocalTime time;
    private String notes;
    private boolean isCompleted;

    public WorkoutLogEntity() {
    }

    @ManyToOne
    @NotNull(message = "User is required")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

//    @ManyToOne
//    @NotNull(message = "Workout is required")
//    public WorkoutEntity getWorkout() {
//        return workout;
//    }
//
//    public void setWorkout(WorkoutEntity workout) {
//        this.workout = workout;
//    }

    @Column
    @NotNull(message = "Date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Column
    @NotNull(message = "Time is required")
    @DateTimeFormat(pattern = "HH:mm:ss")
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Column
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}

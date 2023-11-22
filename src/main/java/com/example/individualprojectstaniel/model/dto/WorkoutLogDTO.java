package com.example.individualprojectstaniel.model.dto;


import java.time.LocalDate;
import java.time.LocalTime;

public class WorkoutLogDTO {
    private Long id;
    private Long userId;


    private LocalDate date;
    private LocalTime time;
    private String notes;
    private boolean completed;

    public WorkoutLogDTO() {
    }

    public WorkoutLogDTO(Long id, LocalDate date, LocalTime time, String notes, boolean completed) {
        this.id = id;
        //this.workout = workout;
        this.date = date;
        this.time = time;
        this.notes = notes;
        this.completed = completed;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}

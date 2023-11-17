package com.example.individualprojectstaniel.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "challenges")
public class ChallengeEntity extends BaseEntity{
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    private Set<UserEntity> participants;
    private Set<GoalsEntity> goals;

    public ChallengeEntity() {
    }

    @Column
    @NotNull(message = "Name is required")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    @NotNull(message = "Description is required")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "Start date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @NotNull(message = "End date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @ManyToMany
    @NotNull(message = "Participants are required")
    public Set<UserEntity> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<UserEntity> participants) {
        this.participants = participants;
    }

    @ManyToMany
    @NotNull(message = "Goals are required")
    public Set<GoalsEntity> getGoals() {
        return goals;
    }

    public void setGoals(Set<GoalsEntity> goals) {
        this.goals = goals;
    }








}

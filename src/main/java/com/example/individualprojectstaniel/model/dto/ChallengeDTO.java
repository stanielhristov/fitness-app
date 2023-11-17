package com.example.individualprojectstaniel.model.dto;

import com.example.individualprojectstaniel.model.entity.ChallengeEntity;
import com.example.individualprojectstaniel.model.entity.GoalsEntity;
import com.example.individualprojectstaniel.model.entity.UserEntity;

import java.time.LocalDate;
import java.util.Set;

public class ChallengeDTO {
    private ChallengeEntity id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<UserEntity> participants;
    private Set<GoalsEntity> goals;


    public ChallengeDTO() {
    }

    public ChallengeDTO(ChallengeEntity id, String name, String description, LocalDate startDate, LocalDate endDate, Set<UserEntity> participants, Set<GoalsEntity> goals) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.participants = participants;
        this.goals = goals;
    }

    public ChallengeEntity getId() {
        return id;
    }

    public void setId(ChallengeEntity id) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Set<UserEntity> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<UserEntity> participants) {
        this.participants = participants;
    }

    public Set<GoalsEntity> getGoals() {
        return goals;
    }

    public void setGoals(Set<GoalsEntity> goals) {
        this.goals = goals;
    }
}

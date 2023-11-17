package com.example.individualprojectstaniel.service;

import com.example.individualprojectstaniel.model.dto.GoalsDTO;

import java.util.List;

public interface GoalsService {
    GoalsDTO createGoals(GoalsDTO goalsDTO);
    GoalsDTO getGoalsById(Long id);
    List<GoalsDTO> getAllGoals();
    GoalsDTO updateGoals(Long id, GoalsDTO updatedGoalsDTO);
    void deleteGoals(Long id);
}

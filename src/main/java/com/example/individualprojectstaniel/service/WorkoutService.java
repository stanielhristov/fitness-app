package com.example.individualprojectstaniel.service;

import com.example.individualprojectstaniel.model.dto.WorkoutDTO;

import java.util.List;

public interface WorkoutService {
    WorkoutDTO createWorkout(WorkoutDTO workoutDTO);
    WorkoutDTO getWorkoutById(Long id);
    List<WorkoutDTO> getAllWorkouts();
    WorkoutDTO updateWorkout(Long id, WorkoutDTO updatedWorkoutDTO);
    void deleteWorkout(Long id);
}

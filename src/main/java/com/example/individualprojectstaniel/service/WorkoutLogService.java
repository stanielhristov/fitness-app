package com.example.individualprojectstaniel.service;

import com.example.individualprojectstaniel.model.dto.WorkoutLogDTO;
import com.example.individualprojectstaniel.model.entity.WorkoutLogEntity;

import java.util.List;

public interface WorkoutLogService {
    WorkoutLogDTO createWorkoutLog(WorkoutLogDTO workoutLogDTO);
    WorkoutLogDTO getWorkoutLogById(Long id);
    List<WorkoutLogDTO> getAllWorkoutLogs();


    List<WorkoutLogDTO> getAllWorkoutLogsByUserId(Long userId);

    WorkoutLogDTO updateWorkoutLog(Long id, WorkoutLogDTO updatedWorkoutLogDTO);
    void deleteWorkoutLog(Long id);

    WorkoutLogEntity updateWorkoutLog(WorkoutLogEntity updatedWorkoutLog);
}

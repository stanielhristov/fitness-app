package com.example.individualprojectstaniel.service;

import com.example.individualprojectstaniel.model.dto.ExerciseDTO;
import com.example.individualprojectstaniel.model.entity.ExerciseEntity;

import java.util.List;

public interface ExerciseService {
    ExerciseDTO createExercise(ExerciseDTO exerciseDTO);
    ExerciseDTO getExerciseById(Long id);
    List<ExerciseDTO> getAllExercises();
    ExerciseDTO updateExercise(Long id, ExerciseDTO updatedExercise);
    void deleteExercise(Long id);
}

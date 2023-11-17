package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.ExerciseDTO;
import com.example.individualprojectstaniel.service.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {


    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }


    @PostMapping
    public ResponseEntity<ExerciseDTO> createExercise(@RequestBody ExerciseDTO exerciseDTO) {
        ExerciseDTO createdExercise = exerciseService.createExercise(exerciseDTO);
        return new ResponseEntity<>(createdExercise, HttpStatus.CREATED);
    }


    @GetMapping("/{exercises}")
    public ResponseEntity<ExerciseDTO> getExercise(@PathVariable Long exerciseId) {
     ExerciseDTO exercise = exerciseService.getExerciseById(exerciseId);
     return ResponseEntity.ok(exercise);
    }


    @GetMapping
    public ResponseEntity<List<ExerciseDTO>> getAllExercises() {
      List<ExerciseDTO> exercises = exerciseService.getAllExercises();
      return ResponseEntity.ok(exercises);
    }

    @PutMapping("/{exercises}")
    public ResponseEntity<ExerciseDTO> updateExercise(@PathVariable Long exerciseId, @RequestBody ExerciseDTO exerciseDTO) {
      ExerciseDTO updatedExercise = exerciseService.updateExercise(exerciseId, exerciseDTO);
       return ResponseEntity.ok(updatedExercise);
    }

    @DeleteMapping("/{exercises}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long exerciseId) {
      exerciseService.deleteExercise(exerciseId);
        return ResponseEntity.noContent().build();
    }
}
package com.example.individualprojectstaniel.service.Impl;

import com.example.individualprojectstaniel.model.dto.ExerciseDTO;
import com.example.individualprojectstaniel.model.entity.ExerciseEntity;
import com.example.individualprojectstaniel.repository.ExerciseRepository;
import com.example.individualprojectstaniel.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ExerciseDTO createExercise(ExerciseDTO exerciseDTO) {
        ExerciseEntity exercise = modelMapper.map(exerciseDTO, ExerciseEntity.class);
        ExerciseEntity savedExercise = exerciseRepository.save(exercise);
        return modelMapper.map(savedExercise, ExerciseDTO.class);
    }

    @Override
    public ExerciseDTO getExerciseById(Long id) {
        ExerciseEntity exerciseEntity = exerciseRepository.findById(id).orElse(null);
        return (exerciseEntity != null) ? modelMapper.map(exerciseEntity, ExerciseDTO.class) : null;
    }

    @Override
    public List<ExerciseDTO> getAllExercises() {
        List<ExerciseEntity> exercises = exerciseRepository.findAll();

        return exercises.stream()
                .map(exercise -> modelMapper.map(exercise, ExerciseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ExerciseDTO updateExercise(Long id, ExerciseDTO updatedExerciseDTO) {
        ExerciseEntity existingExercise = exerciseRepository.findById(id).orElse(null);

        if (existingExercise != null) {
            modelMapper.map(updatedExerciseDTO, existingExercise);
            ExerciseEntity updatedExercise = exerciseRepository.save(existingExercise);

            return modelMapper.map(updatedExercise, ExerciseDTO.class);
        }

        return null;
    }

    @Override
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }
}

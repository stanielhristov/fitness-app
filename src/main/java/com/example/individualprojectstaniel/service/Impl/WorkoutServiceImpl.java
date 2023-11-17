package com.example.individualprojectstaniel.service.Impl;

import com.example.individualprojectstaniel.model.dto.WorkoutDTO;
import com.example.individualprojectstaniel.model.entity.WorkoutEntity;
import com.example.individualprojectstaniel.repository.WorkoutRepository;
import com.example.individualprojectstaniel.service.WorkoutService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;


    private final ModelMapper modelMapper;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository, ModelMapper modelMapper) {
        this.workoutRepository = workoutRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public WorkoutDTO createWorkout(WorkoutDTO workoutDTO) {
        WorkoutEntity workoutEntity = modelMapper.map(workoutDTO, WorkoutEntity.class);
        workoutEntity = workoutRepository.save(workoutEntity);
        return modelMapper.map(workoutEntity, WorkoutDTO.class);
    }

    @Override
    public WorkoutDTO getWorkoutById(Long id) {
        WorkoutEntity workoutEntity = workoutRepository.findById(id).orElse(null);
        return (workoutEntity != null) ? modelMapper.map(workoutEntity, WorkoutDTO.class) : null;
    }

    @Override
    public List<WorkoutDTO> getAllWorkouts() {
        List<WorkoutEntity> workoutEntities = workoutRepository.findAll();

        return workoutEntities.stream()
                .map(workoutEntity -> modelMapper.map(workoutEntity, WorkoutDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public WorkoutDTO updateWorkout(Long id, WorkoutDTO updatedWorkoutDTO) {
        WorkoutEntity existingWorkout = workoutRepository.findById(id).orElse(null);

        if (existingWorkout != null) {
            modelMapper.map(updatedWorkoutDTO, existingWorkout);
            existingWorkout = workoutRepository.save(existingWorkout);
            return modelMapper.map(existingWorkout, WorkoutDTO.class);
        }

        return null;
    }

    @Override
    public void deleteWorkout(Long id) {
        workoutRepository.deleteById(id);
    }
}

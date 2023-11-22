package com.example.individualprojectstaniel.service.Impl;

import com.example.individualprojectstaniel.model.dto.WorkoutLogDTO;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.model.entity.WorkoutLogEntity;
import com.example.individualprojectstaniel.repository.WorkoutLogRepository;
import com.example.individualprojectstaniel.service.UserService;
import com.example.individualprojectstaniel.service.WorkoutLogService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutLogServiceImpl implements WorkoutLogService {

    private final WorkoutLogRepository workoutLogRepository;
    private final UserService userService;


    private final ModelMapper modelMapper;

    public WorkoutLogServiceImpl(WorkoutLogRepository workoutLogRepository, UserService userService, ModelMapper modelMapper) {
        this.workoutLogRepository = workoutLogRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public WorkoutLogDTO createWorkoutLog(WorkoutLogDTO workoutLogDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.findUserByUsername(auth.getName()).orElseThrow(() -> new IllegalStateException("Username not found!" + auth.getName()));

        WorkoutLogEntity workoutLogEntity = modelMapper.map(workoutLogDTO, WorkoutLogEntity.class);
        workoutLogEntity.setUser(user);

        workoutLogEntity = workoutLogRepository.save(workoutLogEntity);
        return modelMapper.map(workoutLogEntity, WorkoutLogDTO.class);
    }

    @Override
    public WorkoutLogDTO getWorkoutLogById(Long id) {
        WorkoutLogEntity workoutLogEntity = workoutLogRepository.findById(id).orElse(null);
        return (workoutLogEntity != null) ? modelMapper.map(workoutLogEntity, WorkoutLogDTO.class) : null;
    }

    @Override
    public List<WorkoutLogDTO> getAllWorkoutLogs() {
        List<WorkoutLogEntity> workoutLogEntities = workoutLogRepository.findAll();
        return workoutLogEntities.stream()
                .map(workoutLogEntity -> modelMapper.map(workoutLogEntity, WorkoutLogDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkoutLogDTO> getAllWorkoutLogsByUserId(Long userId) {
        List<WorkoutLogEntity> workoutLogEntities = workoutLogRepository.findByUserId(userId);


        List<WorkoutLogDTO> workoutLogDTOS = workoutLogEntities.stream()
                .map(entity -> modelMapper.map(entity, WorkoutLogDTO.class))
                .collect(Collectors.toList());

        return workoutLogDTOS;
    }

    @Override
    public WorkoutLogDTO updateWorkoutLog(Long id, WorkoutLogDTO updatedWorkoutLogDTO) {
        WorkoutLogEntity existingWorkoutLog = workoutLogRepository.findById(id).orElse(null);

        if (existingWorkoutLog != null) {
            modelMapper.map(updatedWorkoutLogDTO, existingWorkoutLog);
            existingWorkoutLog = workoutLogRepository.save(existingWorkoutLog);
            return modelMapper.map(existingWorkoutLog, WorkoutLogDTO.class);
        }

        return null;
    }
    @Override
    public void deleteWorkoutLog(Long id) {
        workoutLogRepository.deleteById(id);
    }

    @Override
    public WorkoutLogEntity updateWorkoutLog(WorkoutLogEntity updatedWorkoutLog) {
        Optional<WorkoutLogEntity> existingWorkoutLogOptional = workoutLogRepository.findById(updatedWorkoutLog.getId());

        if (!existingWorkoutLogOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout log not found");
        }

        WorkoutLogEntity existingWorkoutLog = existingWorkoutLogOptional.get();
        modelMapper.map(updatedWorkoutLog, existingWorkoutLog);

        return workoutLogRepository.save(existingWorkoutLog);
    }
}

package com.example.individualprojectstaniel.service.Impl;

import com.example.individualprojectstaniel.model.dto.WorkoutLogDTO;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.model.entity.WorkoutLogEntity;
import com.example.individualprojectstaniel.repository.WorkoutLogRepository;
import com.example.individualprojectstaniel.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkoutLogServiceImplTest {
    @Mock
    private WorkoutLogRepository workoutLogRepository;

    @Mock
    private UserService userService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private WorkoutLogServiceImpl workoutLogService;

    @Test
    void createWorkoutLog() {
        Authentication auth = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(auth);
        UserEntity user = new UserEntity();
        user.setUsername("testUser");
        when(userService.findUserByUsername(user.getUsername())).thenReturn(Optional.of(user));
        when(auth.getName()).thenReturn(user.getUsername());

        WorkoutLogDTO inputDTO = new WorkoutLogDTO();
        WorkoutLogEntity outputEntity = new WorkoutLogEntity();
        when(modelMapper.map(inputDTO, WorkoutLogEntity.class)).thenReturn(outputEntity);
        when(workoutLogRepository.save(any(WorkoutLogEntity.class))).thenReturn(outputEntity);
        when(modelMapper.map(outputEntity, WorkoutLogDTO.class)).thenReturn(inputDTO);

        WorkoutLogDTO result = workoutLogService.createWorkoutLog(inputDTO);

        verify(userService, times(1)).findUserByUsername(user.getUsername());
        verify(workoutLogRepository, times(1)).save(any(WorkoutLogEntity.class));
        assertEquals(inputDTO, result);
    }

    @Test
    void getWorkoutLogById() {
        WorkoutLogEntity inputEntity = new WorkoutLogEntity();
        WorkoutLogDTO expectedOutputDTO = new WorkoutLogDTO();
        when(workoutLogRepository.findById(1L)).thenReturn(Optional.of(inputEntity));
        when(modelMapper.map(inputEntity, WorkoutLogDTO.class)).thenReturn(expectedOutputDTO);

        WorkoutLogDTO result = workoutLogService.getWorkoutLogById(1L);

        verify(workoutLogRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(inputEntity, WorkoutLogDTO.class);
        assertEquals(expectedOutputDTO, result);
    }

    @Test
    void getAllWorkoutLogs() {
        WorkoutLogEntity inputEntity = new WorkoutLogEntity();
        WorkoutLogDTO expectedOutputDTO = new WorkoutLogDTO();
        when(workoutLogRepository.findAll()).thenReturn(Collections.singletonList(inputEntity));
        when(modelMapper.map(inputEntity, WorkoutLogDTO.class)).thenReturn(expectedOutputDTO);

        List<WorkoutLogDTO> result = workoutLogService.getAllWorkoutLogs();

        verify(workoutLogRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(inputEntity, WorkoutLogDTO.class);
        assertEquals(Collections.singletonList(expectedOutputDTO), result);
    }

    @Test
    void getAllWorkoutLogsByUserId() {
        WorkoutLogEntity inputEntity = new WorkoutLogEntity();
        WorkoutLogDTO expectedOutputDTO = new WorkoutLogDTO();
        when(workoutLogRepository.findByUserId(1L)).thenReturn(Collections.singletonList(inputEntity));
        when(modelMapper.map(inputEntity, WorkoutLogDTO.class)).thenReturn(expectedOutputDTO);

        List<WorkoutLogDTO> result = workoutLogService.getAllWorkoutLogsByUserId(1L);

        verify(workoutLogRepository, times(1)).findByUserId(1L);
        verify(modelMapper, times(1)).map(inputEntity, WorkoutLogDTO.class);
        assertEquals(Collections.singletonList(expectedOutputDTO), result);
    }

    @Test
    void updateWorkoutLog() {
        WorkoutLogEntity workoutLogEntity = new WorkoutLogEntity();
        workoutLogEntity.setId(1L);
        WorkoutLogDTO workoutLogDTO = new WorkoutLogDTO();
        workoutLogDTO.setId(1L);

        when(workoutLogRepository.findById(1L)).thenReturn(Optional.of(workoutLogEntity));
        when(workoutLogRepository.save(any(WorkoutLogEntity.class))).thenReturn(workoutLogEntity);
        when(modelMapper.map(workoutLogEntity, WorkoutLogDTO.class)).thenReturn(workoutLogDTO);
        doNothing().when(modelMapper).map(any(), any(WorkoutLogEntity.class));

        WorkoutLogDTO result = workoutLogService.updateWorkoutLog(1L, workoutLogDTO);

        verify(workoutLogRepository, times(1)).findById(1L);
        verify(workoutLogRepository, times(1)).save(any(WorkoutLogEntity.class));
        verify(modelMapper, times(1)).map(workoutLogEntity, WorkoutLogDTO.class);
        assertEquals(workoutLogDTO, result);
    }

    @Test
    void deleteWorkoutLog() {
        workoutLogService.deleteWorkoutLog(1L);

        verify(workoutLogRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateWorkoutLog() {
        WorkoutLogEntity existingEntity = new WorkoutLogEntity();
        existingEntity.setId(1L);
        WorkoutLogDTO updatedDTO = new WorkoutLogDTO();
        updatedDTO.setId(1L);

        when(workoutLogRepository.findById(1L)).thenReturn(Optional.of(existingEntity));
        when(workoutLogRepository.save(any(WorkoutLogEntity.class))).thenReturn(existingEntity);
        when(modelMapper.map(existingEntity, WorkoutLogDTO.class)).thenReturn(updatedDTO);
        doNothing().when(modelMapper).map(any(), any(WorkoutLogEntity.class));
        WorkoutLogDTO result = workoutLogService.updateWorkoutLog(1L, updatedDTO);

        verify(workoutLogRepository, times(1)).findById(1L);
        verify(workoutLogRepository, times(1)).save(any(WorkoutLogEntity.class));
        verify(modelMapper, times(1)).map(existingEntity, WorkoutLogDTO.class);
        assertEquals(updatedDTO, result);
    }
}
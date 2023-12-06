package com.example.individualprojectstaniel.service.Impl;

import com.example.individualprojectstaniel.model.dto.NutritionLogDTO;
import com.example.individualprojectstaniel.model.entity.NutritionLogEntity;
import com.example.individualprojectstaniel.repository.NutritionLogRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class NutritionLogServiceImplTest {

    @Mock
    private NutritionLogRepository nutritionLogRepository;

    @Mock
    private ModelMapper modelMapper;

    private NutritionLogServiceImpl nutritionLogService;

    @BeforeEach
    void setup() {
        openMocks(this);
        nutritionLogService = new NutritionLogServiceImpl(nutritionLogRepository, modelMapper);
    }

    @Test
    void getAllNutritionLogs() {
        NutritionLogEntity nutritionLog = new NutritionLogEntity();
        NutritionLogDTO entity = new NutritionLogDTO();
        List<NutritionLogEntity> logs = List.of(nutritionLog, nutritionLog);

        when(nutritionLogRepository.findAll()).thenReturn(logs);
        when(modelMapper.map(nutritionLog, NutritionLogDTO.class)).thenReturn(entity);

        List<NutritionLogDTO> actualLogs = nutritionLogService.getAllNutritionLogs();

        assertThat(actualLogs.size()).isEqualTo(2);

        verify(modelMapper, times(2)).map(nutritionLog, NutritionLogDTO.class);
        verify(nutritionLogRepository).findAll();
    }

    @Test
    void getAllNutritionLogsByUserId() {
        Long userId = 1L;
        NutritionLogEntity nutritionLogEntity = new NutritionLogEntity();
        NutritionLogDTO nutritionLogDTO = new NutritionLogDTO();
        List<NutritionLogEntity> nutritionLogs = List.of(nutritionLogEntity, nutritionLogEntity);

        when(nutritionLogRepository.findByUserId(userId)).thenReturn(nutritionLogs);
        when(modelMapper.map(nutritionLogEntity, NutritionLogDTO.class)).thenReturn(nutritionLogDTO);

        List<NutritionLogDTO> actualLogs = nutritionLogService.getAllNutritionLogsByUserId(userId);

        assertThat(actualLogs.size()).isEqualTo(2);

        verify(modelMapper, times(2)).map(nutritionLogEntity, NutritionLogDTO.class);
        verify(nutritionLogRepository).findByUserId(userId);

    }

    @Test
    void getNutritionLogById() {
        Long id = 1L;
        NutritionLogEntity nutritionLogEntity = new NutritionLogEntity();
        NutritionLogDTO nutritionLogDTO = new NutritionLogDTO();

        when(nutritionLogRepository.findById(id)).thenReturn(Optional.of(nutritionLogEntity));
        when(modelMapper.map(nutritionLogEntity, NutritionLogDTO.class)).thenReturn(nutritionLogDTO);

        NutritionLogDTO result = nutritionLogService.getNutritionLogById(id);

        assertThat(result).isEqualTo(nutritionLogDTO);

        verify(modelMapper).map(nutritionLogEntity, NutritionLogDTO.class);
        verify(nutritionLogRepository).findById(id);
    }

    @Test
    void createNutritionLog() {
        NutritionLogEntity nutritionLogEntity = new NutritionLogEntity();
        NutritionLogDTO nutritionLogDTO = new NutritionLogDTO();

        when(modelMapper.map(nutritionLogDTO, NutritionLogEntity.class)).thenReturn(nutritionLogEntity);
        when(nutritionLogRepository.save(nutritionLogEntity)).thenReturn(nutritionLogEntity);
        when(modelMapper.map(nutritionLogEntity, NutritionLogDTO.class)).thenReturn(nutritionLogDTO);

        NutritionLogDTO result = nutritionLogService.createNutritionLog(nutritionLogDTO);

        assertThat(result).isEqualTo(nutritionLogDTO);

        verify(modelMapper).map(nutritionLogDTO, NutritionLogEntity.class);
        verify(nutritionLogRepository).save(nutritionLogEntity);
        verify(modelMapper).map(nutritionLogEntity, NutritionLogDTO.class);

    }

    @Test
    void updateNutritionLog_whenMissingNutritionLog_shouldThrowNotFoundException() {
        Long id = 1L;
        when(nutritionLogRepository.findById(id)).thenReturn(Optional.empty());

        NutritionLogDTO nutritionLogDTO = new NutritionLogDTO();

        EntityNotFoundException actualException = assertThrows(EntityNotFoundException.class, () -> nutritionLogService.updateNutritionLog(id, nutritionLogDTO));
        assertThat(actualException.getMessage()).isEqualTo("Nutrition log not found with id: " + id);
    }

    @Test
    void deleteNutritionLog() {
        Long id = 1L;

        when(nutritionLogRepository.existsById(id)).thenReturn(true);

        assertTrue(nutritionLogService.deleteNutritionLog(id));

        verify(nutritionLogRepository).existsById(id);
        verify(nutritionLogRepository).deleteById(id);

    }
}
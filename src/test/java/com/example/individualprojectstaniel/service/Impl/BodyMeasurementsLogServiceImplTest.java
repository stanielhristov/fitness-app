package com.example.individualprojectstaniel.service.Impl;

import com.example.individualprojectstaniel.model.dto.BodyMeasurementsLogDTO;
import com.example.individualprojectstaniel.model.entity.BodyMeasurementsLogEntity;
import com.example.individualprojectstaniel.repository.BodyMeasurementsLogRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class BodyMeasurementsLogServiceImplTest {

    @Mock
    private BodyMeasurementsLogRepository bodyMeasurementsLogRepository;

    @Mock
    private ModelMapper modelMapper;

    private BodyMeasurementsLogServiceImpl bodyMeasurementsLogService;

    @BeforeEach
    void setUp() {
        openMocks(this);
        bodyMeasurementsLogService = new BodyMeasurementsLogServiceImpl(bodyMeasurementsLogRepository, modelMapper);
    }

    @Test
    void getAllBodyMeasurementsLogs() {
        BodyMeasurementsLogEntity bodyMeasurementsLogEntity = new BodyMeasurementsLogEntity();
        BodyMeasurementsLogDTO bodyMeasurementsLogDTO = new BodyMeasurementsLogDTO();
        List<BodyMeasurementsLogEntity> logs = List.of(bodyMeasurementsLogEntity ,bodyMeasurementsLogEntity);

        when(bodyMeasurementsLogRepository.findAll()).thenReturn(logs);
        when(modelMapper.map(bodyMeasurementsLogEntity, BodyMeasurementsLogDTO.class)).thenReturn(bodyMeasurementsLogDTO);

        List<BodyMeasurementsLogDTO> actualLogs = bodyMeasurementsLogService.getAllBodyMeasurementsLogs();

        assertThat(actualLogs.size()).isEqualTo(2);

        verify(modelMapper, times(2)).map(bodyMeasurementsLogEntity, BodyMeasurementsLogDTO.class);
        verify(bodyMeasurementsLogRepository).findAll();

    }

    @Test
    void getBodyMeasurementsLogById() {
        Long userId = 1L;
        BodyMeasurementsLogEntity bodyMeasurementsLogEntity = new BodyMeasurementsLogEntity();
        BodyMeasurementsLogDTO bodyMeasurementsLogDTO = new BodyMeasurementsLogDTO();
        List<BodyMeasurementsLogEntity> bodyMeasurementLogs = List.of(bodyMeasurementsLogEntity, bodyMeasurementsLogEntity);

        when(bodyMeasurementsLogRepository.findByUserId(userId)).thenReturn(bodyMeasurementLogs);
        when(modelMapper.map(bodyMeasurementsLogEntity, BodyMeasurementsLogDTO.class)).thenReturn(bodyMeasurementsLogDTO);

        List<BodyMeasurementsLogDTO> actualLogs = bodyMeasurementsLogService.getAllBodyMeasurementsLogsByUserId(userId);

        assertThat(actualLogs.size()).isEqualTo(2);

        verify(modelMapper, times(2)).map(bodyMeasurementsLogEntity, BodyMeasurementsLogDTO.class);
        verify(bodyMeasurementsLogRepository).findByUserId(userId);

    }

    @Test
    void getAllBodyMeasurementsLogsByUserId() {
        Long id = 1L;
        BodyMeasurementsLogEntity bodyMeasurementsLogEntity = new BodyMeasurementsLogEntity();
        BodyMeasurementsLogDTO bodyMeasurementsLogDTO = new BodyMeasurementsLogDTO();

        when(bodyMeasurementsLogRepository.findById(id)).thenReturn(Optional.of(bodyMeasurementsLogEntity));
        when(modelMapper.map(bodyMeasurementsLogEntity, BodyMeasurementsLogDTO.class)).thenReturn(bodyMeasurementsLogDTO);

        BodyMeasurementsLogDTO result = bodyMeasurementsLogService.getBodyMeasurementsLogById(id);

        assertThat(result).isEqualTo(bodyMeasurementsLogDTO);

        verify(modelMapper).map(bodyMeasurementsLogEntity, BodyMeasurementsLogDTO.class);
        verify(bodyMeasurementsLogRepository).findById(id);
    }

    @Test
    void createBodyMeasurementsLog() {
        BodyMeasurementsLogEntity bodyMeasurementsLogEntity = new BodyMeasurementsLogEntity();
        BodyMeasurementsLogDTO bodyMeasurementsLogDTO = new BodyMeasurementsLogDTO();

        when(modelMapper.map(bodyMeasurementsLogDTO, BodyMeasurementsLogEntity.class)).thenReturn(bodyMeasurementsLogEntity);
        when(bodyMeasurementsLogRepository.save(bodyMeasurementsLogEntity)).thenReturn(bodyMeasurementsLogEntity);
        when(modelMapper.map(bodyMeasurementsLogEntity, BodyMeasurementsLogDTO.class)).thenReturn(bodyMeasurementsLogDTO);

        BodyMeasurementsLogDTO result = bodyMeasurementsLogService.createBodyMeasurementsLog(bodyMeasurementsLogDTO);

        assertThat(result).isEqualTo(bodyMeasurementsLogDTO);

        verify(modelMapper).map(bodyMeasurementsLogDTO, BodyMeasurementsLogEntity.class);
        verify(bodyMeasurementsLogRepository).save(bodyMeasurementsLogEntity);
        verify(modelMapper).map(bodyMeasurementsLogEntity,BodyMeasurementsLogDTO.class);
    }

    @Test
    void updateBodyMeasurementsLog() {
        Long id = 1L;
        when(bodyMeasurementsLogRepository.findById(id)).thenReturn(Optional.empty());

        BodyMeasurementsLogDTO bodyMeasurementsLogDTO = new BodyMeasurementsLogDTO();

        EntityNotFoundException actualException = assertThrows(EntityNotFoundException.class, () -> bodyMeasurementsLogService.updateBodyMeasurementsLog(id, bodyMeasurementsLogDTO, null));
        assertThat(actualException.getMessage()).isEqualTo("Body Measurements log not found with id: " + id);
    }

    @Test
    void deleteBodyMeasurementsLog() {
        Long id = 1L;

        when(bodyMeasurementsLogRepository.existsById(id)).thenReturn(true);

        assertTrue(bodyMeasurementsLogService.deleteBodyMeasurementsLog(id));

        verify(bodyMeasurementsLogRepository).existsById(id);
        verify(bodyMeasurementsLogRepository).deleteById(id);
    }
}
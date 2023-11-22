package com.example.individualprojectstaniel.service.Impl;

import com.example.individualprojectstaniel.model.dto.BodyMeasurementsLogDTO;
import com.example.individualprojectstaniel.model.entity.BodyMeasurementsLogEntity;
import com.example.individualprojectstaniel.repository.BodyMeasurementsLogRepository;
import com.example.individualprojectstaniel.service.BodyMeasurementsLogService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BodyMeasurementsLogServiceImpl implements BodyMeasurementsLogService {
    private final BodyMeasurementsLogRepository bodyMeasurementsLogRepository;
    private final ModelMapper modelMapper;

    public BodyMeasurementsLogServiceImpl(BodyMeasurementsLogRepository bodyMeasurementsLogRepository, ModelMapper modelMapper) {
        this.bodyMeasurementsLogRepository = bodyMeasurementsLogRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BodyMeasurementsLogDTO> getAllBodyMeasurementsLogs() {
        List<BodyMeasurementsLogEntity> bodyMeasurementsLogEntities = bodyMeasurementsLogRepository.findAll();

        return bodyMeasurementsLogEntities.stream()
                .map(entity -> modelMapper.map(entity, BodyMeasurementsLogDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BodyMeasurementsLogDTO getBodyMeasurementsLogById(Long id) {
        BodyMeasurementsLogEntity bodyMeasurementsLogEntity = bodyMeasurementsLogRepository.findById(id)
                .orElse(null);

        if (bodyMeasurementsLogEntity == null) {
            // You can customize the behavior when the nutrition log is not found, for example:
            return null;
        }

        return modelMapper.map(bodyMeasurementsLogEntity, BodyMeasurementsLogDTO.class);
    }

    @Override
    public List<BodyMeasurementsLogDTO> getAllBodyMeasurementsLogsByUserId(Long userId) {
        List<BodyMeasurementsLogEntity> bodyMeasurementsLogEntities = bodyMeasurementsLogRepository.findByUserId(userId);


        List<BodyMeasurementsLogDTO> bodyMeasurementsLogDTOS = bodyMeasurementsLogEntities.stream()
                .map(entity -> modelMapper.map(entity, BodyMeasurementsLogDTO.class))
                .collect(Collectors.toList());

        return bodyMeasurementsLogDTOS;
    }

    @Override
    public BodyMeasurementsLogDTO createBodyMeasurementsLog(BodyMeasurementsLogDTO bodyMeasurementsLogDTO) {
        BodyMeasurementsLogEntity bodyMeasurementsLogEntity = modelMapper.map(bodyMeasurementsLogDTO, BodyMeasurementsLogEntity.class);

        bodyMeasurementsLogEntity = bodyMeasurementsLogRepository.save(bodyMeasurementsLogEntity);

        return modelMapper.map(bodyMeasurementsLogEntity, BodyMeasurementsLogDTO.class);
    }
    @Override
    public BodyMeasurementsLogDTO updateBodyMeasurementsLog(Long id, BodyMeasurementsLogDTO bodyMeasurementsLogDTO, Authentication auth) {
        BodyMeasurementsLogEntity existingBodyMeasurementsLogEntity = bodyMeasurementsLogRepository.findById(id)
                .orElse(null);

        if (existingBodyMeasurementsLogEntity == null) {
            throw new EntityNotFoundException("Body Measurements log not found with id: " + id);
        }

        modelMapper.map(bodyMeasurementsLogDTO, existingBodyMeasurementsLogEntity);

        bodyMeasurementsLogRepository.save(existingBodyMeasurementsLogEntity);

        return modelMapper.map(existingBodyMeasurementsLogEntity, BodyMeasurementsLogDTO.class);
    }

    @Override
    public boolean deleteBodyMeasurementsLog(Long id) {
        if (bodyMeasurementsLogRepository.existsById(id)) {
            bodyMeasurementsLogRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

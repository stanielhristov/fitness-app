package com.example.individualprojectstaniel.service.Impl;

import com.example.individualprojectstaniel.model.dto.NutritionLogDTO;
import com.example.individualprojectstaniel.model.entity.NutritionLogEntity;
import com.example.individualprojectstaniel.repository.NutritionLogRepository;
import com.example.individualprojectstaniel.service.NutritionLogService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NutritionLogServiceImpl implements NutritionLogService {
    private final NutritionLogRepository nutritionLogRepository;
    private final ModelMapper modelMapper;

    public NutritionLogServiceImpl(NutritionLogRepository nutritionLogRepository, ModelMapper modelMapper) {
        this.nutritionLogRepository = nutritionLogRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<NutritionLogDTO> getAllNutritionLogs() {
        List<NutritionLogEntity> nutritionLogEntities = nutritionLogRepository.findAll();

        List<NutritionLogDTO> nutritionLogDTOs = nutritionLogEntities.stream()
                .map(entity -> modelMapper.map(entity, NutritionLogDTO.class))
                .collect(Collectors.toList());

        return nutritionLogDTOs;
    }

    @Override
    public List<NutritionLogDTO> getAllNutritionLogsByUserId(Long userId) {
        List<NutritionLogEntity> nutritionLogEntities = nutritionLogRepository.findByUserId(userId);

        List<NutritionLogDTO> nutritionLogDTOS = nutritionLogEntities.stream()
                .map(entity -> modelMapper.map(entity, NutritionLogDTO.class))
                .collect(Collectors.toList());

        return nutritionLogDTOS;
    }

    @Override
    public NutritionLogDTO getNutritionLogById(Long id) {
        NutritionLogEntity nutritionLogEntity = nutritionLogRepository.findById(id)
                .orElse(null);

        if (nutritionLogEntity == null) {
            return null;
        }

        return modelMapper.map(nutritionLogEntity, NutritionLogDTO.class);
    }

    @Override
    public NutritionLogDTO createNutritionLog(NutritionLogDTO nutritionLogDTO) {
        NutritionLogEntity nutritionLogEntity = modelMapper.map(nutritionLogDTO, NutritionLogEntity.class);

        nutritionLogEntity = nutritionLogRepository.save(nutritionLogEntity);

        return modelMapper.map(nutritionLogEntity, NutritionLogDTO.class);
    }

    @Override
    public NutritionLogDTO updateNutritionLog(Long id, NutritionLogDTO nutritionLogDTO) {
        NutritionLogEntity existingNutritionLogEntity = nutritionLogRepository.findById(id)
                .orElse(null);

        if (existingNutritionLogEntity == null) {
            throw new EntityNotFoundException("Nutrition log not found with id: " + id);
        }

        modelMapper.map(nutritionLogDTO, existingNutritionLogEntity);

        nutritionLogRepository.save(existingNutritionLogEntity);

        return modelMapper.map(existingNutritionLogEntity, NutritionLogDTO.class);
    }

    @Override
    public boolean deleteNutritionLog(Long id) {
        if (nutritionLogRepository.existsById(id)) {
            nutritionLogRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


}

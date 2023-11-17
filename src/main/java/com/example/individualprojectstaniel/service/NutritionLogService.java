package com.example.individualprojectstaniel.service;

import com.example.individualprojectstaniel.model.dto.NutritionLogDTO;

import java.util.List;

public interface NutritionLogService {
    List<NutritionLogDTO> getAllNutritionLogs();

    NutritionLogDTO getNutritionLogById(Long id);

    NutritionLogDTO createNutritionLog(NutritionLogDTO nutritionLogDTO);

    NutritionLogDTO updateNutritionLog(Long id, NutritionLogDTO nutritionLogDTO);

    boolean deleteNutritionLog(Long id);
}

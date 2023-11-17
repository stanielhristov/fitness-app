package com.example.individualprojectstaniel.service;

import com.example.individualprojectstaniel.model.dto.BodyMeasurementsLogDTO;
import com.example.individualprojectstaniel.model.dto.NutritionLogDTO;

import java.util.List;

public interface BodyMeasurementsLogService {
    List<BodyMeasurementsLogDTO> getAllBodyMeasurementsLogs();
    BodyMeasurementsLogDTO getBodyMeasurementsLogById(Long id);

    BodyMeasurementsLogDTO createBodyMeasurementsLog(BodyMeasurementsLogDTO bodyMeasurementsLogDTO);

    BodyMeasurementsLogDTO updateBodyMeasurementsLog(Long id, BodyMeasurementsLogDTO bodyMeasurementsLogDTO);

    boolean deleteBodyMeasurementsLog(Long id);
}

package com.example.individualprojectstaniel.service;

import com.example.individualprojectstaniel.model.dto.BodyMeasurementsLogDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BodyMeasurementsLogService {
    List<BodyMeasurementsLogDTO> getAllBodyMeasurementsLogs();
    BodyMeasurementsLogDTO getBodyMeasurementsLogById(Long id);

    List<BodyMeasurementsLogDTO> getAllBodyMeasurementsLogsByUserId(Long userId);

    BodyMeasurementsLogDTO createBodyMeasurementsLog(BodyMeasurementsLogDTO bodyMeasurementsLogDTO);

    BodyMeasurementsLogDTO updateBodyMeasurementsLog(Long id, BodyMeasurementsLogDTO bodyMeasurementsLogDTO, Authentication auth);

    boolean deleteBodyMeasurementsLog(Long id);
}

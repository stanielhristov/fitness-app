package com.example.individualprojectstaniel.service;

import com.example.individualprojectstaniel.model.dto.BodyMeasurementsDTO;

public interface BodyMeasurementsService {
    BodyMeasurementsDTO create(BodyMeasurementsDTO bodyMeasurementsDto);
    BodyMeasurementsDTO update(Long id, BodyMeasurementsDTO bodyMeasurementsDto);
    void delete(Long id);
    BodyMeasurementsDTO findById(Long id);
}

package com.example.individualprojectstaniel.service.Impl;

import com.example.individualprojectstaniel.model.dto.BodyMeasurementsDTO;
import com.example.individualprojectstaniel.model.entity.BodyMeasurementsEntity;
import com.example.individualprojectstaniel.repository.BodyMeasurementsRepository;
import com.example.individualprojectstaniel.service.BodyMeasurementsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BodyMeasurementsServiceImpl implements BodyMeasurementsService {

    private final BodyMeasurementsRepository bodyMeasurementsRepository;
    private final ModelMapper modelMapper;


    public BodyMeasurementsServiceImpl(BodyMeasurementsRepository bodyMeasurementsRepository, ModelMapper modelMapper) {
        this.bodyMeasurementsRepository = bodyMeasurementsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BodyMeasurementsDTO create(BodyMeasurementsDTO bodyMeasurementsDto) {
        BodyMeasurementsEntity bodyMeasurementsEntity = modelMapper.map(bodyMeasurementsDto, BodyMeasurementsEntity.class);
        bodyMeasurementsEntity = bodyMeasurementsRepository.save(bodyMeasurementsEntity);

        return modelMapper.map(bodyMeasurementsEntity, BodyMeasurementsDTO.class);
    }

    @Override
    public BodyMeasurementsDTO update(Long id, BodyMeasurementsDTO bodyMeasurementsDto) {
        BodyMeasurementsEntity bodyMeasurementsEntity = bodyMeasurementsRepository.findById(id).orElseThrow(() -> new RuntimeException("BodyMeasurements not found"));
        modelMapper.map(bodyMeasurementsDto, bodyMeasurementsEntity);
        bodyMeasurementsEntity = bodyMeasurementsRepository.save(bodyMeasurementsEntity);
        return modelMapper.map(bodyMeasurementsEntity, BodyMeasurementsDTO.class);
    }

    @Override
    public void delete(Long id) {
        bodyMeasurementsRepository.deleteById(id);
    }

    @Override
    public BodyMeasurementsDTO findById(Long id) {
        BodyMeasurementsEntity bodyMeasurementsEntity = bodyMeasurementsRepository.findById(id).orElseThrow(() -> new RuntimeException("BodyMeasurements not found"));
        return modelMapper.map(bodyMeasurementsEntity, BodyMeasurementsDTO.class);
    }
}

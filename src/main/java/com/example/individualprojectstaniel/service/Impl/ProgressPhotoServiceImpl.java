package com.example.individualprojectstaniel.service.Impl;

import com.example.individualprojectstaniel.model.dto.ProgressPhotoDTO;
import com.example.individualprojectstaniel.model.entity.ProgressPhotoEntity;
import com.example.individualprojectstaniel.repository.ProgressPhotoRepository;
import com.example.individualprojectstaniel.service.ProgressPhotoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgressPhotoServiceImpl implements ProgressPhotoService {
    private final ProgressPhotoRepository progressPhotoRepository;
    private final ModelMapper modelMapper;

    public ProgressPhotoServiceImpl(ProgressPhotoRepository progressPhotoRepository, ModelMapper modelMapper) {
        this.progressPhotoRepository = progressPhotoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProgressPhotoDTO save(ProgressPhotoDTO progressPhotoDTO) {
        ProgressPhotoEntity progressPhoto = modelMapper.map(progressPhotoDTO, ProgressPhotoEntity.class);
        progressPhoto = progressPhotoRepository.save(progressPhoto);

        return modelMapper.map(progressPhoto, ProgressPhotoDTO.class);
    }

    @Override
    public List<ProgressPhotoDTO> getAll() {
        return progressPhotoRepository.findAll().stream()
                .map(progressPhoto -> modelMapper.map(progressPhoto, ProgressPhotoDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public ProgressPhotoDTO getById(Long id) {
        ProgressPhotoEntity progressPhoto = progressPhotoRepository.findById(id).orElse(null);

        return progressPhoto != null ? modelMapper.map(progressPhoto, ProgressPhotoDTO.class) : null;
    }

    @Override
    public void delete(Long id) {
        progressPhotoRepository.deleteById(id);
    }
}

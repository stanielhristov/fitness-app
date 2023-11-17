package com.example.individualprojectstaniel.service;

import com.example.individualprojectstaniel.model.dto.ProgressPhotoDTO;

import java.util.List;

public interface ProgressPhotoService {
    ProgressPhotoDTO save(ProgressPhotoDTO progressPhoto);
    List<ProgressPhotoDTO> getAll();
    ProgressPhotoDTO getById(Long id);
    void delete(Long id);
}

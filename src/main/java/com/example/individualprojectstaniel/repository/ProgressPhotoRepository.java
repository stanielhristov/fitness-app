package com.example.individualprojectstaniel.repository;

import com.example.individualprojectstaniel.model.entity.ProgressPhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressPhotoRepository extends JpaRepository<ProgressPhotoEntity, Long> {
}

package com.example.individualprojectstaniel.repository;

import com.example.individualprojectstaniel.model.entity.BodyMeasurementsLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodyMeasurementsLogRepository extends JpaRepository<BodyMeasurementsLogEntity, Long> {
    List<BodyMeasurementsLogEntity> findByUserId(Long userId);
}

package com.example.individualprojectstaniel.repository;

import com.example.individualprojectstaniel.model.entity.BodyMeasurementsLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyMeasurementsLogRepository extends JpaRepository<BodyMeasurementsLogEntity, Long> {
}

package com.example.individualprojectstaniel.repository;

import com.example.individualprojectstaniel.model.entity.BodyMeasurementsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyMeasurementsRepository extends JpaRepository<BodyMeasurementsEntity, Long> {
}

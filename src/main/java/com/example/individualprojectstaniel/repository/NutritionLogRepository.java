package com.example.individualprojectstaniel.repository;

import com.example.individualprojectstaniel.model.entity.NutritionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NutritionLogRepository extends JpaRepository<NutritionLogEntity, Long> {

    List<NutritionLogEntity> findByUserId(Long userId);
}

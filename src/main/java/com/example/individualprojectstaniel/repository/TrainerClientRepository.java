package com.example.individualprojectstaniel.repository;

import com.example.individualprojectstaniel.model.entity.TrainerClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerClientRepository extends JpaRepository<TrainerClientEntity, Long> {
    List<TrainerClientEntity> findAllByTrainerId(Long trainerId);
    boolean existsByTrainerIdAndClientId(Long trainerId, Long clientId);



}

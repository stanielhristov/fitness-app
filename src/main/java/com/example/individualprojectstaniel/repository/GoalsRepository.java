package com.example.individualprojectstaniel.repository;

import com.example.individualprojectstaniel.model.entity.GoalsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalsRepository extends JpaRepository<GoalsEntity, Long> {
}

package com.example.individualprojectstaniel.model.entity;

import com.example.individualprojectstaniel.model.dto.NutritionLogDTO;
import com.example.individualprojectstaniel.model.dto.WorkoutLogDTO;
import com.example.individualprojectstaniel.model.enums.FitnessGoalsEnum;
import com.example.individualprojectstaniel.model.enums.PreferredWorkoutEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Map;

@Entity
@Table(name = "myprofiles")
public class MyProfileEntity extends BaseEntity{
    private double bmi;
    private UserEntity user;
    private FitnessGoalsEnum fitnessGoals;
    private PreferredWorkoutEnum preferredWorkouts;


}

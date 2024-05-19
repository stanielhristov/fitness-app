package com.example.individualprojectstaniel.model.dto;

import com.example.individualprojectstaniel.model.entity.UserEntity;
import lombok.Data;

@Data
public class TrainerDTO {
    private Long id;
    private String username;
    private String email;
    private String gender;


    public static TrainerDTO map(UserEntity entity) {
        TrainerDTO trainerDTO = new TrainerDTO();
        trainerDTO.setId(entity.getId());
        trainerDTO.setUsername(entity.getUsername());
        trainerDTO.setEmail(entity.getEmail());
        trainerDTO.setGender(String.valueOf(entity.getGender()));

        return trainerDTO;
    }
}

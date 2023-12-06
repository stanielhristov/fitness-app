package com.example.individualprojectstaniel.model.dto;

import com.example.individualprojectstaniel.model.entity.UserEntity;
import lombok.Getter;

@Getter
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String gender;
    private int height;
    private int weight;
    private String role;



    public static UserDTO map(UserEntity entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setUsername(entity.getUsername());
        userDTO.setEmail(entity.getEmail());
        userDTO.setGender(String.valueOf(entity.getGender()));
        userDTO.setRole(entity.getRoles().get(0).getName());
        userDTO.setHeight(entity.getHeight());
        userDTO.setWeight(entity.getWeight());

        return userDTO;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setRole(String role) {
        this.role = role;
    }




}

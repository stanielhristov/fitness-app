package com.example.individualprojectstaniel.service;

import com.example.individualprojectstaniel.model.dto.MyProfileDTO;
import com.example.individualprojectstaniel.model.dto.UserLoginBindingModel;
import com.example.individualprojectstaniel.model.dto.UserRegisterBindingModel;
import com.example.individualprojectstaniel.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean register(UserRegisterBindingModel userRegisterBindingModel);
    boolean login(UserLoginBindingModel userLoginBindingModel);

    void logout();

    Optional<UserEntity> findUserByUsername(String username);
    MyProfileDTO getUserById(Long id);
    MyProfileDTO editUser(Long id, MyProfileDTO myProfileDTO);
}

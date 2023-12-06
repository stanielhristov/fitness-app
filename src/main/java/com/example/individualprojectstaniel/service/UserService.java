package com.example.individualprojectstaniel.service;

import com.example.individualprojectstaniel.model.dto.*;
import com.example.individualprojectstaniel.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean register(UserRegisterBindingModel userRegisterBindingModel);
    boolean login(UserLoginBindingModel userLoginBindingModel);

    Optional<UserEntity> findUserByUsername(String username);
    MyProfileDTO getUserById(Long id);
    UserDTO getCurrentUserById(Long id);
    MyProfileDTO editUser(Long id, MyProfileDTO myProfileDTO);
    boolean validateCurrentPassword(ResetPasswordDTO resetPasswordDTO);
    void updatePassword(ResetPasswordDTO resetPasswordDTO);
    List<UserDTO> getAllUsers();
    void deleteUser(Long id);
    void changeRole(Long userId, String newRole);

    List<UserDTO> findALl();
}

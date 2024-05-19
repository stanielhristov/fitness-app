package com.example.individualprojectstaniel.service.Impl;

import com.example.individualprojectstaniel.model.dto.*;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.model.entity.UserRoleEntity;
import com.example.individualprojectstaniel.repository.UserRepository;
import com.example.individualprojectstaniel.repository.UserRoleRepository;
import com.example.individualprojectstaniel.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {
        if (userRegisterBindingModel == null) {
            return false;
        }

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        String username = userRegisterBindingModel.getUsername();

        if (this.userRepository.findByUsername(username).isPresent()) {
            return true;
        }
        userRegisterBindingModel.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

        UserEntity user = modelMapper.map(userRegisterBindingModel, UserEntity.class);
        UserRoleEntity userRole = userRoleRepository.findByName(UserRoleEntity.USER);
        user.setRoles(userRole);

        this.userRepository.save(user);

        return true;
    }

    @Override
    public boolean login(UserLoginBindingModel userLoginBindingModel) {
        Optional<UserEntity> user = findUserByUsername(userLoginBindingModel.getUsername());

        return user.isPresent() &&
                passwordEncoder.matches(userLoginBindingModel.getPassword(), user.get().getPassword());
    }


    public Optional<UserEntity> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public MyProfileDTO getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElse(null);

        if (userEntity == null) {
            // You can customize the behavior when the nutrition log is not found, for example:
            return null;
        }

        return modelMapper.map(userEntity, MyProfileDTO.class);
    }

    @Override
    public UserDTO getCurrentUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);

        if (userEntity == null) {
            return null;
        }

        return UserDTO.map(userEntity);
    }

    @Override
    public MyProfileDTO editUser(Long id, MyProfileDTO myProfileDTO) {
        UserEntity existingUserEntity = userRepository.findById(id)
                .orElse(null);

        if (existingUserEntity == null) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }

        modelMapper.map(myProfileDTO, existingUserEntity);

        userRepository.save(existingUserEntity);

        return modelMapper.map(existingUserEntity, MyProfileDTO.class);
    }

    @Override
    public boolean validateCurrentPassword(ResetPasswordDTO resetPasswordDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        UserEntity user = userRepository.findByUsername(username).orElse(null);

        return passwordEncoder.matches(resetPasswordDTO.getCurrentPassword(), user != null ? user.getPassword() : null);
    }

    @Override
    public void updatePassword(ResetPasswordDTO resetPasswordDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        UserEntity user = userRepository.findByUsername(username).orElse(null);

        user.setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));

        userRepository.save(user);

    }

    @Override
    public List<UserDTO> getAllUsers() {

        return userRepository.findAll().stream().map(UserDTO::map).toList();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

//    @Override
//    public void changeRole(Long userId, String newRole) {
//        Optional<UserEntity> optionalUser = userRepository.findById(userId);
//
//        if (optionalUser.isPresent()) {
//            UserEntity userEntity = optionalUser.get();
//            userEntity.getRoles().clear();
//            userEntity.getRoles().add(new UserRoleEntity(newRole));
//            userRepository.save(userEntity);
//
//        } else {
//            throw new IllegalArgumentException("User not found with ID: " + userId);
//        }
//    }

    @Override
    public void changeRole(Long userId, String newRole) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            UserEntity userEntity = optionalUser.get();
            UserRoleEntity role = userRoleRepository.findByName(newRole);
            userEntity.setRoles(role);

            userRepository.save(userEntity);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }

    @Override
    public Long findUserIdByUsername(String username) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);

        return userOptional.map(UserEntity::getId)
                .orElseThrow(() -> new IllegalStateException("User not found with username: " + username));
    }

    @Override
    public List<UserDTO> findALl() {
        return userRepository.findAll().stream().map(UserDTO::map).toList();
    }
}

package com.example.individualprojectstaniel.service.Impl;

import com.example.individualprojectstaniel.model.dto.UserLoginBindingModel;
import com.example.individualprojectstaniel.model.dto.UserRegisterBindingModel;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.repository.UserRepository;
import com.example.individualprojectstaniel.service.LoggedUser;
import com.example.individualprojectstaniel.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, LoggedUser loggedUser, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {
        if (userRegisterBindingModel == null) {
            return false;
        }

        String username = userRegisterBindingModel.getUsername();

        if (this.userRepository.findByUsername(username).isPresent()) {
            return true;
        }
        userRegisterBindingModel.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

        UserEntity user = modelMapper.map(userRegisterBindingModel, UserEntity.class);


        this.userRepository.save(user);

        return true;
    }

    @Override
    public boolean login(UserLoginBindingModel userLoginBindingModel) {
        Optional<UserEntity> user = findUserByUsername(userLoginBindingModel.getUsername());

        if (user.isPresent() && passwordEncoder.matches(userLoginBindingModel.getPassword(), user.get().getPassword())) {
//        if (user.isPresent() && userLoginBindingModel.getPassword().equals(user.get().getPassword())) {
            loggedUser.setUsername(user.get().getUsername());
            loggedUser.setLogged(true);

            return true;
        }

        return false;
    }

    @Override
    public void logout() {
        loggedUser.setUsername(null);
        loggedUser.setLogged(false);
    }


    public Optional<UserEntity> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

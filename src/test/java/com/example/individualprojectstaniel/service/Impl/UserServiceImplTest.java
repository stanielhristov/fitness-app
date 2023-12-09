package com.example.individualprojectstaniel.service.Impl;

import com.example.individualprojectstaniel.model.dto.MyProfileDTO;
import com.example.individualprojectstaniel.model.dto.UserDTO;
import com.example.individualprojectstaniel.model.dto.UserLoginBindingModel;
import com.example.individualprojectstaniel.model.dto.UserRegisterBindingModel;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.model.entity.UserRoleEntity;
import com.example.individualprojectstaniel.repository.UserRepository;
import com.example.individualprojectstaniel.repository.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserRoleRepository userRoleRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl  userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void register_SuccessfulRegistration() {
        UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();
        userRegisterBindingModel.setUsername("testUser");
        userRegisterBindingModel.setPassword("testPassword");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.empty());
        when(userRoleRepository.findByName(UserRoleEntity.USER)).thenReturn(new UserRoleEntity());
        when(modelMapper.map(userRegisterBindingModel, UserEntity.class)).thenReturn(new UserEntity());

        assertTrue(userService.register(userRegisterBindingModel));

        verify(userRepository, times(1)).findByUsername("testUser");
        verify(userRoleRepository, times(1)).findByName(UserRoleEntity.USER);
        verify(passwordEncoder, times(1)).encode("testPassword");
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void register_UserAlreadyExists() {
        UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();
        userRegisterBindingModel.setUsername("existingUser");

        when(userRepository.findByUsername("existingUser")).thenReturn(Optional.of(new UserEntity()));

        assertTrue(userService.register(userRegisterBindingModel));

        verify(userRepository, times(1)).findByUsername("existingUser");
        verify(userRoleRepository, never()).findByName(any());
        verify(passwordEncoder, never()).encode(any());
        verify(userRepository, never()).save(any());

    }

    @Test
    void login_SuccessfulLogin() {
        UserLoginBindingModel userLoginBindingModel = new UserLoginBindingModel();
        userLoginBindingModel.setUsername("testUser");
        userLoginBindingModel.setPassword("testPassword");

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("encodedPassword");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches("testPassword", "encodedPassword")).thenReturn(true);

        assertTrue(userService.login(userLoginBindingModel));

        verify(userRepository, times(1)).findByUsername("testUser");
        verify(passwordEncoder, times(1)).matches("testPassword", "encodedPassword");
    }

    @Test
    void login_InvalidPassword() {
        UserLoginBindingModel userLoginBindingModel = new UserLoginBindingModel();
        userLoginBindingModel.setUsername("testUser");
        userLoginBindingModel.setPassword("invalidPassword");

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("encodedPassword");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches("invalidPassword", "encodedPassword")).thenReturn(false);

        assertFalse(userService.login(userLoginBindingModel));

        verify(userRepository, times(1)).findByUsername("testUser");
        verify(passwordEncoder, times(1)).matches("invalidPassword", "encodedPassword");

    }

    @Test
    void findUserByUsername_UserExists() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testUser");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(userEntity));

        Optional<UserEntity> result = userService.findUserByUsername("testUser");

        verify(userRepository, times(1)).findByUsername("testUser");

        assertTrue(result.isPresent());
        assertEquals(userEntity, result.get());
    }

    @Test
    void findByUsername_UserNotExists() {
        when(userRepository.findByUsername("nonExistingUser")).thenReturn(Optional.empty());

        Optional<UserEntity> result = userService.findUserByUsername("nonExistingUser");

        verify(userRepository, times(1)).findByUsername("nonExistingUser");

        assertTrue(result.isEmpty());
    }

    @Test
    void getUserById_UserExists() {
        Long userId = 1L;
        MyProfileDTO myProfileDTO = new MyProfileDTO();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(modelMapper.map(userEntity, MyProfileDTO.class)).thenReturn(myProfileDTO);

        MyProfileDTO result = userService.getUserById(userId);

        verify(userRepository, times(1)).findById(userId);
        verify(modelMapper, times(1)).map(userEntity, MyProfileDTO.class);

        assertEquals(myProfileDTO, result);
    }

    @Test
    void getUserById_UserNotExists() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        MyProfileDTO result = userService.getUserById(userId);

        verify(userRepository, times(1)).findById(userId);
        verify(modelMapper, never()).map(any(), any());

        assertNull(result);
    }

//    @Test
//    void getCurrentUserById_UserExists() {
//        Long userId = 1L;
//        UserEntity userEntity = new UserEntity();
//        userEntity.setId(userId);
//        UserRoleEntity userRole = new UserRoleEntity();
//        userEntity.setRoles(userRole);
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
//
//        UserDTO user = userService.getCurrentUserById(userId);
//
//        verify(userRepository, times(1)).findById(userId);
//
//        assertNotNull(user);
//        assertEquals(userId, user.getId());
//    }

    @Test
    void getCurrentUserById_UserNotExists() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        UserDTO result = userService.getCurrentUserById(userId);

        verify(userRepository, times(1)).findById(userId);

        assertNull(result);
    }

//    @Test
//    void editUser() {
//        Long userId = 1L;
//        MyProfileDTO myProfileDTO = new MyProfileDTO();
//        UserEntity existingUserEntity = new UserEntity();
//        existingUserEntity.setId(userId);
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUserEntity));
//        when(modelMapper.map(myProfileDTO, UserEntity.class)).thenReturn(existingUserEntity);
//        when(userRepository.save(existingUserEntity)).thenReturn(existingUserEntity);
//        when(modelMapper.map(existingUserEntity, MyProfileDTO.class)).thenReturn(myProfileDTO);
//
//        MyProfileDTO result = userService.editUser(userId, myProfileDTO);
//
//        verify(userRepository, times(1)).findById(userId);
//        verify(modelMapper, times(1)).map(myProfileDTO, UserEntity.class);
//        verify(userRepository, times(1)).save(existingUserEntity);
//        verify(modelMapper, times(1)).map(existingUserEntity, MyProfileDTO.class);
//
//        assertEquals(myProfileDTO, result);
//    }

    @Test
    void validateCurrentPassword() {
    }

    @Test
    void updatePassword() {
    }

//    @Test
//    void getAllUsers() {
//        UserEntity user1 = new UserEntity();
//        user1.setId(1L);
//        UserEntity user2 = new UserEntity();
//        user2.setId(2L);
//
//        List<UserEntity> userList = Arrays.asList(user1, user2);
//        when(userRepository.findAll()).thenReturn(userList);
//
//        UserDTO userDTO1 = new UserDTO();
//        userDTO1.setId(1L);
//        UserDTO userDTO2 = new UserDTO();
//        userDTO2.setId(2L);
//        List<UserDTO> expectedUserDTOList = Arrays.asList(userDTO1, userDTO2);
//        when(modelMapper.map(user1, UserDTO.class)).thenReturn(userDTO1);
//        when(modelMapper.map(user2, UserDTO.class)).thenReturn(userDTO2);
//
//        List<UserDTO> result = userService.getAllUsers();
//
//        verify(userRepository, times(1)).findAll();
//        verify(modelMapper, times(2)).map(any(UserEntity.class), eq(UserDTO.class));
//
//        assertEquals(expectedUserDTOList, result);
//    }

    @Test
    void deleteUser() {
        Long userId = 1L;

        assertDoesNotThrow(() -> userService.deleteUser(userId));

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void changeRole() {
    }

    @Test
    void findALl() {

    }
}
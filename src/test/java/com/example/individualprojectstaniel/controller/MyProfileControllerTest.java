package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.MyProfileDTO;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MyProfileControllerTest {
    @Mock
    private MyProfileController myProfileController;
    @MockBean
    private UserService userService;
    @MockBean
    private Authentication authentication;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void myProfileHome() {
        UserEntity user = new UserEntity();
        when(authentication.getName()).thenReturn("testUser");
        when(userService.findUserByUsername("testUser")).thenReturn(java.util.Optional.of(user));

        Model model = mock(Model.class);

        ModelAndView modelAndView = myProfileController.myProfileHome(authentication);

        verify(userService).findUserByUsername("testUser");

        assertEquals("myprofile", modelAndView.getViewName());

        verify(model).addAttribute("profile", new MyProfileDTO(user));
    }

    @Test
    void showEditProfileForm() {
        Long userId = 1L;
        MyProfileDTO myProfileDTO = new MyProfileDTO();
        when(userService.getUserById(userId)).thenReturn(myProfileDTO);

        ModelAndView modelAndView = myProfileController.showEditProfileForm(userId);

        verify(userService).getUserById(userId);

        assertEquals("editprofile", modelAndView.getViewName());

        verify(modelAndView).addObject("myProfileDTO", myProfileDTO);
        verify(modelAndView).addObject("genders", List.of("MALE", "FEMALE"));
    }

    @Test
    void editMyProfile() {
    }

    @Test
    void error() {
    }
}
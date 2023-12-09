package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.ResetPasswordDTO;
import com.example.individualprojectstaniel.model.dto.UserRegisterBindingModel;
import com.example.individualprojectstaniel.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @MockBean
    private UserController userController;

    @BeforeEach
    void setup() {
        openMocks(this);
        userController = new UserController(userService);
    }

    @Test
    void register_whenUserIsRegistered_shouldReturnLoginPage() {
        UserRegisterBindingModel userRegisterBinding = new UserRegisterBindingModel();
        when(userService.register(userRegisterBinding)).thenReturn(true);

        BindingResult result = mock(BindingResult.class);
        ModelAndView actual = userController.register(userRegisterBinding, result);

        assertThat(actual.getViewName()).isEqualTo("redirect:/login");
    }

    @Test
    void register_whenUserIsNotRegistered_shouldReturnRegisterPage() {
        UserRegisterBindingModel userRegisterBinding = new UserRegisterBindingModel();
        when(userService.register(userRegisterBinding)).thenReturn(false);

        BindingResult result = mock(BindingResult.class);
        ModelAndView actual = userController.register(userRegisterBinding, result);

        assertThat(actual.getViewName()).isEqualTo("register");
    }

    @Test
    void login() {
        ModelAndView actual = userController.login();

        assertThat(actual.getViewName()).isEqualTo("login");
    }

    @Test
    void register() {
        ModelAndView actual = userController.register();

        assertThat(actual.getViewName()).isEqualTo("register");
    }

    @Test
    void resetPassword() {
        ModelAndView actual = userController.resetPassword();

        assertThat(actual.getViewName()).isEqualTo("resetpassword");
    }

    @Test
    void resetPassword_withValidPassword_shouldUpdatePasswordAndReturnLogin() {
        ResetPasswordDTO resetPasswordDTO = new ResetPasswordDTO();
        when(userService.validateCurrentPassword(resetPasswordDTO)).thenReturn(true);

        ModelAndView result = userController.resetPassword(resetPasswordDTO);

        assertEquals("login", result.getViewName());
        assertEquals("Password reset successfully!", result.getModel().get("successMessage"));
        verify(userService).updatePassword(resetPasswordDTO);
    }

    @Test
    void resetPassword_withInvalidPassword() {
        ResetPasswordDTO resetPasswordDTO  = new ResetPasswordDTO();
        when(userService.validateCurrentPassword(resetPasswordDTO)).thenReturn(false);

        ModelAndView result = userController.resetPassword(resetPasswordDTO);

        assertEquals("redirect:/resetpassword?error", result.getViewName());
        assertEquals("Incorrect current password.", result.getModel().get("errorMessage"));

        verify(userService, never()).updatePassword(resetPasswordDTO);

    }

    @Test
    void loginError() {
        ModelAndView result = userController.loginError();

        assertEquals("login", result.getViewName());
        assertEquals("Wrong password or username", result.getModel().get("loginError"));

    }



}

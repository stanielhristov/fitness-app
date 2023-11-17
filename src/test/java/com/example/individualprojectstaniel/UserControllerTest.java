package com.example.individualprojectstaniel;

import com.example.individualprojectstaniel.controller.UserController;
import com.example.individualprojectstaniel.model.dto.UserRegisterBindingModel;
import com.example.individualprojectstaniel.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
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

        ModelAndView actual = userController.register(userRegisterBinding);

        assertThat(actual.getViewName()).isEqualTo("redirect:/login");
    }

    @Test
    void register_whenUserIsNotRegistered_shouldReturnRegisterPage() {
        UserRegisterBindingModel userRegisterBinding = new UserRegisterBindingModel();
        when(userService.register(userRegisterBinding)).thenReturn(false);

        ModelAndView actual = userController.register(userRegisterBinding);

        assertThat(actual.getViewName()).isEqualTo("register");
    }

}

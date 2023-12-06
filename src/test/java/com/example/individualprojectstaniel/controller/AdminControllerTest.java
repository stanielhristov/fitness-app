package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.UserDTO;
import com.example.individualprojectstaniel.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AdminControllerTest {
    @Mock
    private UserService  userService;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }




    @Test
    void getAllUsers() {
        List<UserDTO> mockUsers = List.of(new UserDTO(), new UserDTO());
        when(userService.getAllUsers()).thenReturn(mockUsers);

        Model model = mock(Model.class);

        String viewName = adminController.getAllUsers(model);

        verify(userService).getAllUsers();

        assertEquals("adminpanel", viewName);

        verify(model).addAttribute("users", mockUsers);
    }

    @Test
    void delete() {
        Long userId = 1L;

        ModelAndView modelAndView = adminController.delete(userId);

        verify(userService).deleteUser(userId);

        assertEquals("redirect:/adminpanel", modelAndView.getViewName());

    }

    @Test
    void showChangeRoleForm() {
        Long userId = 1L;
        UserDTO userDTO = new UserDTO();
        when(userService.getCurrentUserById(userId)).thenReturn(userDTO);

        ModelAndView modelAndView = adminController.showChangeRoleForm(userId);

        verify(userService).getCurrentUserById(userId);

        assertEquals("changerole", modelAndView.getViewName());
    }

    @Test
    void changeUserRole() {
        Long userId = 1L;
        String newRole = "ADMIN";

        ModelAndView modelAndView = adminController.changeUserRole(userId, newRole);

        verify(userService).changeRole(userId, newRole);

        assertEquals("redirect:/adminpanel", modelAndView.getViewName());
    }
}
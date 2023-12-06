package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.NutritionLogDTO;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.service.NutritionLogService;
import com.example.individualprojectstaniel.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class NutritionLogControllerTest {
    @Mock
    private NutritionLogService nutritionLogService;
    @Mock
    private UserService userService;
    @MockBean
    private Authentication authentication;
    @MockBean
    private NutritionLogController nutritionLogController;

    @BeforeEach
    void setup() {
        openMocks(this);
        nutritionLogController = new NutritionLogController(nutritionLogService, userService);
    }

    @Test
    public void getAllNutritionLogsForCurrentUser() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authentication.getName()).thenReturn("testUser");

        UserEntity user = new UserEntity();
        user.setId(1L);
        when(userService.findUserByUsername("testUser")).thenReturn(Optional.of(user));

        List<NutritionLogDTO> nutritionLogs = Arrays.asList(new NutritionLogDTO(), new NutritionLogDTO());
        when(nutritionLogService.getAllNutritionLogsByUserId(1L)).thenReturn(nutritionLogs);

        ModelAndView modelAndView = nutritionLogController.getAllNutritionLogsForCurrentUser();

        assertEquals("nutritionlogs", modelAndView.getViewName());
        assertEquals(nutritionLogs, modelAndView.getModel().get("nutritionlogs"));

    }

    @Test
    public void createNutritionLog() {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testUser");

        UserEntity user = new UserEntity();
        user.setId(1L);
        when(userService.findUserByUsername("testUser")).thenReturn(Optional.of(user));

        NutritionLogDTO nutritionLogDTO = new NutritionLogDTO();
        nutritionLogDTO.setUserId(1L);
        when(nutritionLogService.createNutritionLog(nutritionLogDTO)).thenReturn(nutritionLogDTO);

        ModelAndView modelAndView = nutritionLogController.createNutritionLog(nutritionLogDTO, auth);

        assertEquals("redirect:/nutritionlogs", modelAndView.getViewName());
        assertEquals(nutritionLogDTO, modelAndView.getModel().get("nutritionLog"));
    }

    @Test
    void createNutritionLogView() {
        ModelAndView actual = nutritionLogController.createNutritionLog();

        assertThat(actual.getViewName()).isEqualTo("add-nutritionlog");
    }

    @Test
    public void showUpdateForm() {
        Long logId = 1L;
        NutritionLogDTO nutritionLogDTO = new NutritionLogDTO();
        when(nutritionLogService.getNutritionLogById(logId)).thenReturn(nutritionLogDTO);

        ModelAndView modelAndView = nutritionLogController.showUpdateForm(logId);

        assertEquals("updatenutritiontlog", modelAndView.getViewName());
        assertEquals(nutritionLogDTO, modelAndView.getModel().get("nutritionLogDTO"));
    }

    @Test
    public void showUpdateFormWithInvalidId() {
        Long logId = 1L;
        when(nutritionLogService.getNutritionLogById(logId)).thenReturn(null);

        ModelAndView modelAndView = nutritionLogController.showUpdateForm(logId);

        assertEquals("error-page", modelAndView.getViewName());
    }

    @Test
    public void updateNutritionLog() {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testUser");

        UserEntity user = new UserEntity();
        user.setId(1L);
        when(userService.findUserByUsername("testUser")).thenReturn(Optional.of(user));

        NutritionLogDTO nutritionLogDTO = new NutritionLogDTO();
        Long logId = 1L;

        when(nutritionLogService.updateNutritionLog(logId, nutritionLogDTO)).thenReturn(nutritionLogDTO);

        ModelAndView modelAndView = nutritionLogController.updateNutritionLog(nutritionLogDTO, logId, auth);

        verify(nutritionLogService).updateNutritionLog(eq(logId), eq(nutritionLogDTO));

        assertEquals("redirect:/nutritionlogs", modelAndView.getViewName());
    }


    @Test
    public void deleteNutritionLog() {
        Long logId = 1L;

        ModelAndView modelAndView = nutritionLogController.delete(logId);

        verify(nutritionLogService).deleteNutritionLog(eq(logId));

        assertEquals("redirect:/nutritionlogs", modelAndView.getViewName());

    }




}

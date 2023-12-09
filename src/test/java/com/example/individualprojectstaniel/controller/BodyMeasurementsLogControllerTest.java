package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.BodyMeasurementsLogDTO;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.service.BodyMeasurementsLogService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class BodyMeasurementsLogControllerTest {
    @Mock
    private BodyMeasurementsLogService bodyMeasurementsLogService;
    @Mock
    private UserService userService;
    @MockBean
    private Authentication authentication;
    @MockBean
    private BodyMeasurementsLogController bodyMeasurementsLogController;


    @BeforeEach
    void setup() {
        openMocks(this);
        bodyMeasurementsLogController = new BodyMeasurementsLogController(bodyMeasurementsLogService, userService);
    }

    @Test
    public void getAllBodyMeasurementLogsForCurrentUser() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authentication.getName()).thenReturn("testUser");

        UserEntity user = new UserEntity();
        user.setId(1L);
        when(userService.findUserByUsername("testUser")).thenReturn(Optional.of(user));

        List<BodyMeasurementsLogDTO> bodyMeasurementsLogs = Arrays.asList(new BodyMeasurementsLogDTO(), new BodyMeasurementsLogDTO());
        when(bodyMeasurementsLogService.getAllBodyMeasurementsLogsByUserId(1L)).thenReturn(bodyMeasurementsLogs);

        ModelAndView modelAndView = bodyMeasurementsLogController.getAllBodyMeasurementsLogsForCurrentUser();

        assertEquals("bodymeasurementslogs", modelAndView.getViewName());
        assertEquals(bodyMeasurementsLogs, modelAndView.getModel().get("bodymeasurementslogs"));

    }

    @Test
    public void createBodyMeasurementsLog() {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testUser");

        UserEntity user = new UserEntity();
        user.setId(1L);
        when(userService.findUserByUsername("testUser")).thenReturn(Optional.of(user));

        BodyMeasurementsLogDTO bodyMeasurementsLogDTO = new BodyMeasurementsLogDTO();
        bodyMeasurementsLogDTO.setUserId(1L);
        when(bodyMeasurementsLogService.createBodyMeasurementsLog(bodyMeasurementsLogDTO)).thenReturn(bodyMeasurementsLogDTO);

        ModelAndView modelAndView = bodyMeasurementsLogController.createBodyMeasurementsLog(bodyMeasurementsLogDTO, auth);

        assertEquals("redirect:/bodymeasurementslogs", modelAndView.getViewName());
        assertEquals(bodyMeasurementsLogDTO, modelAndView.getModel().get("bodyMeasurementsLog"));
    }

    @Test
    public void showUpdateForm() {
        Long logId = 1L;
        BodyMeasurementsLogDTO bodyMeasurementsLogDTO = new BodyMeasurementsLogDTO();
        when(bodyMeasurementsLogService.getBodyMeasurementsLogById(logId)).thenReturn(bodyMeasurementsLogDTO);

        ModelAndView modelAndView = bodyMeasurementsLogController.showUpdateForm(logId);

        assertEquals("updatebodymeasurementslog", modelAndView.getViewName());
        assertEquals(bodyMeasurementsLogDTO, modelAndView.getModel().get("bodyMeasurementsLogDTO"));
    }

    @Test
    public void showUpdateFormWithInvalidId() {
        Long logId = 1L;
        when(bodyMeasurementsLogService.getBodyMeasurementsLogById(logId)).thenReturn(null);

        ModelAndView modelAndView = bodyMeasurementsLogController.showUpdateForm(logId);

        assertEquals("error-page", modelAndView.getViewName());
    }


}

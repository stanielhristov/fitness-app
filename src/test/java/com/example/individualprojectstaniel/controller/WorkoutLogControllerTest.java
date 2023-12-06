package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.WorkoutLogDTO;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.service.UserService;
import com.example.individualprojectstaniel.service.WorkoutLogService;
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

public class WorkoutLogControllerTest {
    @Mock
    private WorkoutLogService workoutLogService;
    @Mock
    private UserService userService;

    @MockBean
    private Authentication authentication;
    @MockBean
    private WorkoutLogController workoutLogController;

    @BeforeEach
    void setup() {
        openMocks(this);
        workoutLogController = new WorkoutLogController(workoutLogService, userService);
    }

    @Test
    public void getAllWorkoutLogsForCurrentUser() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authentication.getName()).thenReturn("testUser");

        UserEntity user = new UserEntity();
        user.setId(1L);
        when(userService.findUserByUsername("testUser")).thenReturn(Optional.of(user));

        List<WorkoutLogDTO> workoutLogs = Arrays.asList(new WorkoutLogDTO(), new WorkoutLogDTO());
        when(workoutLogService.getAllWorkoutLogsByUserId(1L)).thenReturn(workoutLogs);

        ModelAndView modelAndView = workoutLogController.getAllWorkoutLogsForCurrentUser();

        assertEquals("workout-logs", modelAndView.getViewName());
        assertEquals(workoutLogs, modelAndView.getModel().get("workoutlogs"));
    }

    @Test
    public void createWorkoutLog() {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testUser");

        UserEntity user = new UserEntity();
        user.setId(1L);
        when(userService.findUserByUsername("testUser")).thenReturn(Optional.of(user));

        WorkoutLogDTO workoutLogDTO = new WorkoutLogDTO();
        workoutLogDTO.setUserId(1L);
        when(workoutLogService.createWorkoutLog(workoutLogDTO)).thenReturn(workoutLogDTO);

        ModelAndView modelAndView = workoutLogController.createWorkoutLog(workoutLogDTO, auth);

        assertEquals("redirect:/workoutlogs", modelAndView.getViewName());
        assertEquals(workoutLogDTO, modelAndView.getModel().get("workoutLog"));
    }

    @Test
    public void showUpdateForm() {
        Long logId = 1L;
        WorkoutLogDTO workoutLogDTO = new WorkoutLogDTO();
        when(workoutLogService.getWorkoutLogById(logId)).thenReturn(workoutLogDTO);

        ModelAndView modelAndView = workoutLogController.showUpdateForm(logId);

        assertEquals("updateworkoutlog", modelAndView.getViewName());
        assertEquals(workoutLogDTO, modelAndView.getModel().get("workoutLogDTO"));
    }

    @Test
    public void showUpdateFormWithInvalidId() {
        Long logId = 1L;
        when(workoutLogService.getWorkoutLogById(logId)).thenReturn(null);

        ModelAndView modelAndView = workoutLogController.showUpdateForm(logId);

        assertEquals("error-page", modelAndView.getViewName());
    }

}



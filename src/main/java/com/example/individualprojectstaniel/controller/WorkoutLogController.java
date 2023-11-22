package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.WorkoutLogDTO;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.service.UserService;
import com.example.individualprojectstaniel.service.WorkoutLogService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/workoutlogs")
public class WorkoutLogController {
    private final WorkoutLogService workoutLogService;
    private final UserService userService;

    public WorkoutLogController(WorkoutLogService workoutLogService, UserService userService) {
        this.workoutLogService = workoutLogService;
        this.userService = userService;
    }


    @GetMapping
    public ModelAndView getAllWorkoutLogsForCurrentUser() {
        ModelAndView modelAndView = new ModelAndView("workout-logs");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.findUserByUsername(auth.getName()).orElseThrow(() -> new IllegalStateException("Username not found!" + auth.getName()));

        List<WorkoutLogDTO> workoutLogs = workoutLogService.getAllWorkoutLogsByUserId(user.getId());

        modelAndView.addObject("workoutlogs", workoutLogs);

        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id) {
        WorkoutLogDTO workoutLog = workoutLogService.getWorkoutLogById(id);

        ModelAndView modelAndView = new ModelAndView();

        if (workoutLog != null) {
            modelAndView.setViewName("updateworkoutlog");
            modelAndView.addObject("workoutLogDTO", workoutLog);
        } else {
            modelAndView.setViewName("error-page");
        }

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createWorkoutLog() {

        return new ModelAndView("add-workout-log");
    }

    @PostMapping
    public ModelAndView createWorkoutLog(WorkoutLogDTO workoutLogDTO, Authentication auth) {
        UserEntity user = userService.findUserByUsername(auth.getName()).orElseThrow(() -> new IllegalStateException("Username not found!" + auth.getName()));
        workoutLogDTO.setUserId(user.getId());

        WorkoutLogDTO createdWorkoutLog = workoutLogService.createWorkoutLog(workoutLogDTO);

        ModelAndView modelAndView = new ModelAndView("redirect:/workoutlogs");
        modelAndView.addObject("workoutLog", createdWorkoutLog);

        return modelAndView;
    }

    @PostMapping(value = "/update/{id}")
    public ModelAndView updateWorkoutLog(WorkoutLogDTO workoutLogDTO, @PathVariable Long id, Authentication auth) {
        UserEntity user = userService.findUserByUsername(auth.getName()).orElseThrow(() -> new IllegalStateException("Username not found!" + auth.getName()));
        workoutLogDTO.setUserId(user.getId());

        workoutLogService.updateWorkoutLog(id, workoutLogDTO);
        return new ModelAndView("redirect:/workoutlogs");
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        workoutLogService.deleteWorkoutLog(id);

        return new ModelAndView("redirect:/workoutlogs");
    }

    @PostMapping(value = "/{id}", consumes = "application/json")
    public ModelAndView update(@PathVariable Long id, @RequestBody WorkoutLogDTO workoutLogDTO) {
        WorkoutLogDTO updatedWorkoutLog = workoutLogService.updateWorkoutLog(id, workoutLogDTO);

        ModelAndView modelAndView = new ModelAndView("redirect:/workoutlogs");
        modelAndView.addObject("workoutLog", updatedWorkoutLog);

        return modelAndView;
    }
}
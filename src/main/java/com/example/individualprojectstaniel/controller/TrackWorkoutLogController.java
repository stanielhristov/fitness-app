package com.example.individualprojectstaniel.controller;


import com.example.individualprojectstaniel.model.dto.UserDTO;
import com.example.individualprojectstaniel.model.dto.WorkoutLogDTO;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.repository.TrainerClientRepository;
import com.example.individualprojectstaniel.service.UserService;
import com.example.individualprojectstaniel.service.WorkoutLogService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/clients/trackworkouts")
@RolesAllowed(value = {"TRAINER"})
public class TrackWorkoutLogController {
    private  final WorkoutLogService workoutLogService;
    private final UserService userService;
    private final TrainerClientRepository trainerClientRepository;

    public TrackWorkoutLogController(WorkoutLogService workoutLogService, UserService userService, TrainerClientRepository trainerClientRepository) {
        this.workoutLogService = workoutLogService;
        this.userService = userService;
        this.trainerClientRepository = trainerClientRepository;
    }

    @GetMapping("/{clientId}")
    ModelAndView showWorkoutsForClient(@PathVariable Long clientId) {

        ModelAndView modelAndView = new ModelAndView("trackworkoutlogs");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity trainer = userService.findUserByUsername(auth.getName()).orElseThrow(() -> new IllegalStateException("Username not found!" + auth.getName()));

        boolean isAssigned = trainerClientRepository.existsByTrainerIdAndClientId(trainer.getId(), clientId);
        if (!isAssigned) {
            throw new IllegalStateException("Client not assigned to current trainer!");
        }

        UserDTO client = userService.getCurrentUserById(clientId);


        List<WorkoutLogDTO> workoutLogs = workoutLogService.getAllWorkoutLogsByUserId(client.getId());

        modelAndView.addObject("workoutlogs", workoutLogs);
        modelAndView.addObject("client", client);

        return modelAndView;

    }
}

package com.example.individualprojectstaniel.controller;


import com.example.individualprojectstaniel.model.dto.NutritionLogDTO;
import com.example.individualprojectstaniel.model.dto.UserDTO;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.repository.TrainerClientRepository;
import com.example.individualprojectstaniel.service.ClientService;
import com.example.individualprojectstaniel.service.NutritionLogService;
import com.example.individualprojectstaniel.service.UserService;
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
@RequestMapping("/clients/tracknutritions")
@RolesAllowed(value = {"TRAINER"})
public class TrackNutritionLogController {

    private final NutritionLogService nutritionLogService;
    private final UserService userService;
    private final ClientService clientService;
    private final TrainerClientRepository trainerClientRepository;

    public TrackNutritionLogController(NutritionLogService nutritionLogService, UserService userService, ClientService clientService, TrainerClientRepository trainerClientRepository) {
        this.nutritionLogService = nutritionLogService;
        this.userService = userService;
        this.clientService = clientService;
        this.trainerClientRepository = trainerClientRepository;
    }

    @GetMapping("/{clientId}")
    ModelAndView showTrackNutritionForClient(@PathVariable Long clientId) {

        ModelAndView modelAndView = new ModelAndView("tracknutritionlogs");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity trainer = userService.findUserByUsername(auth.getName()).orElseThrow(() -> new IllegalStateException("Username not found!" + auth.getName()));

        boolean isAssigned = trainerClientRepository.existsByTrainerIdAndClientId(trainer.getId(), clientId);
        if (!isAssigned) {
            throw new IllegalStateException("Client not assigned to current trainer!");
        }


        UserDTO client = userService.getCurrentUserById(clientId);


        List<NutritionLogDTO> nutritionLogs = nutritionLogService.getAllNutritionLogsByUserId(client.getId());

        modelAndView.addObject("nutritionlogs", nutritionLogs);
        modelAndView.addObject("client", client);

        return modelAndView;

    }

}

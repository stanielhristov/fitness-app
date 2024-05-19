package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.BodyMeasurementsLogDTO;
import com.example.individualprojectstaniel.model.dto.UserDTO;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.repository.TrainerClientRepository;
import com.example.individualprojectstaniel.service.BodyMeasurementsLogService;
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
@RequestMapping("/clients/trackbodymeasurements")
@RolesAllowed(value = {"TRAINER"})
public class TrackBodyMeasurementsLogController {
    private final BodyMeasurementsLogService bodyMeasurementsLogService;
    private final UserService userService;
    private final TrainerClientRepository trainerClientRepository;

    public TrackBodyMeasurementsLogController(BodyMeasurementsLogService bodyMeasurementsLogService, UserService userService, TrainerClientRepository trainerClientRepository) {
        this.bodyMeasurementsLogService = bodyMeasurementsLogService;
        this.userService = userService;
        this.trainerClientRepository = trainerClientRepository;
    }

    @GetMapping("/{clientId}")
    ModelAndView showTrackBodyMeasurementsForClient(@PathVariable Long clientId) {
        ModelAndView modelAndView = new ModelAndView("trackbodymeasurementslogs");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity trainer = userService.findUserByUsername(auth.getName()).orElseThrow(() -> new IllegalStateException("Username not found!" + auth.getName()));

        boolean isAssigned = trainerClientRepository.existsByTrainerIdAndClientId(trainer.getId(), clientId);
        if (!isAssigned) {
            throw new IllegalStateException("Client not assigned to current trainer!");
        }

        UserDTO client = userService.getCurrentUserById(clientId);

        List<BodyMeasurementsLogDTO> bodyMeasurementsLogs = bodyMeasurementsLogService.getAllBodyMeasurementsLogsByUserId(client.getId());

        modelAndView.addObject("bodymeasurementslogs", bodyMeasurementsLogs);
        modelAndView.addObject("client", client);

        return modelAndView;
    }
}

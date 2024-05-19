package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.AskQuestionDto;
import com.example.individualprojectstaniel.model.dto.TrainerDTO;
import com.example.individualprojectstaniel.model.entity.TrainerClientEntity;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.repository.TrainerClientRepository;
import com.example.individualprojectstaniel.service.Impl.UserServiceImpl;
import com.example.individualprojectstaniel.service.MessagesService;
import com.example.individualprojectstaniel.service.TrainerService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/trainers")
public class TrainersController {

    private final TrainerService trainerService;
    private final TrainerClientRepository trainerClientRepository;
    private final MessagesService messagesService;
    private final UserServiceImpl userServiceImpl;


    public TrainersController(TrainerService trainerService, TrainerClientRepository trainerClientRepository, MessagesService messagesService, UserServiceImpl userServiceImpl) {
        this.trainerService = trainerService;
        this.trainerClientRepository = trainerClientRepository;
        this.messagesService = messagesService;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    public ModelAndView viewAllTrainers() {
        List<TrainerDTO> trainers = trainerService.getAllTrainers();

        ModelAndView modelAndView = new ModelAndView("trainerslist");
        modelAndView.addObject("trainers", trainers);

        return modelAndView;
    }


    @GetMapping("/askquestion/{id}")
    public ModelAndView askQuestionView(@PathVariable String id) {

        ModelAndView modelAndView = new ModelAndView("askquestion");
        modelAndView.addObject("trainerId", id);
        return modelAndView;
    }

    @PostMapping("/askquestion/{trainerId}")
    public ModelAndView askQuestion(@PathVariable Long trainerId, AskQuestionDto question) {
        TrainerDTO trainer = trainerService.getById(trainerId);
        trainerService.askTrainer(trainer.getUsername(), question);

        return new ModelAndView("redirect:/trainers");
    }

    @PostMapping("/trainme/{trainerId}")
    public ModelAndView trainMe(@PathVariable Long trainerId) {
        TrainerDTO trainer = trainerService.getById(trainerId);
        trainerService.trainMe(trainer);

        return new ModelAndView("redirect:/trainers");
    }

    @GetMapping("/{trainerId}/respond/{clientId}")
    @RolesAllowed({"TRAINER"})
    public ModelAndView respond(@PathVariable String trainerId, @PathVariable String clientId, @RequestParam String answer, Authentication auth) {
        UserEntity loggedInTrainer = userServiceImpl.findUserByUsername(auth.getName()).orElseThrow();
        if (!(loggedInTrainer.getId() == Long.parseLong(trainerId))) {
            throw new AccessDeniedException("You are not the trainer of this client");
        }

        if (answer.equalsIgnoreCase("yes")) {
            if (!isClientAlreadyAssigned(Long.parseLong(trainerId), Long.parseLong(clientId))) {
                trainerClientRepository.save(new TrainerClientEntity(Long.parseLong(trainerId), Long.parseLong(clientId)));
                messagesService.sendConfirmationMessage(clientId);

            } else {
                return new ModelAndView("redirect:/error-page");
            }

        } else {
            messagesService.sendDeclineMessage(clientId);
        }

        return new ModelAndView("redirect:/trainers");
    }

//    @GetMapping("/track/nutritionlog/{clientId}")
//    @RolesAllowed("TRAINER")
//    ModelAndView trackNutritionLog(@PathVariable String clientId) {
//        // TODO Validate logged in trainer is the trainer of clientId
//        // TODO Make view tracknutritionlog
//        ModelAndView modelAndView = new ModelAndView("tracknutritionlog");
//        // TODO Grab nutLogs for the user
//        List<NutritionLogDTO> nutLogs;
//
//        modelAndView.addObject("logs", nutLogs);
//
//        return modelAndView;
//    }

    private boolean isClientAlreadyAssigned(Long trainerId, Long clientId) {
        return trainerClientRepository.existsByTrainerIdAndClientId(trainerId, clientId);

    }
}

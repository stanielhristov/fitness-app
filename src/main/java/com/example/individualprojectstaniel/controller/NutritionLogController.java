package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.NutritionLogDTO;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.service.NutritionLogService;
import com.example.individualprojectstaniel.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/nutritionlogs")
public class NutritionLogController {

    private final NutritionLogService nutritionLogService;
    private final UserService userService;

    public NutritionLogController(NutritionLogService nutritionLogService, UserService userService) {
        this.nutritionLogService = nutritionLogService;
        this.userService = userService;
    }

//    @GetMapping
//    public ModelAndView getAllNutritionLogs() {
//        List<NutritionLogDTO> nutritionLogs = nutritionLogService.getAllNutritionLogs();
//
//        ModelAndView modelAndView = new ModelAndView("nutritionlogs");
//        modelAndView.addObject("nutritionlogs", nutritionLogs);
//
//        return modelAndView;
//    }

    @GetMapping
    public ModelAndView getAllNutritionLogsForCurrentUser() {
        ModelAndView modelAndView = new ModelAndView("nutritionlogs");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.findUserByUsername(auth.getName()).orElseThrow(() -> new IllegalStateException("Username not found!" + auth.getName()));

        List<NutritionLogDTO> nutritionLogs = nutritionLogService.getAllNutritionLogsByUserId(user.getId());

        modelAndView.addObject("nutritionlogs", nutritionLogs);

        return modelAndView;

    }



    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id) {
        NutritionLogDTO nutritionLog = nutritionLogService.getNutritionLogById(id);

        ModelAndView modelAndView = new ModelAndView();

        if (nutritionLog != null) {
            modelAndView.setViewName("updatenutritiontlog");
            modelAndView.addObject("nutritionLogDTO", nutritionLog);
        } else {
            modelAndView.setViewName("error-page");
        }

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createNutritionLog() {

        return new ModelAndView("add-nutritionlog");
    }


    @PostMapping()
    public ModelAndView createNutritionLog(NutritionLogDTO nutritionLogDTO, Authentication auth) {
        UserEntity user = userService.findUserByUsername(auth.getName()).orElseThrow(() -> new IllegalStateException("Username not found!" + auth.getName()));
        nutritionLogDTO.setUserId(user.getId());


        NutritionLogDTO createdNutritionLog = nutritionLogService.createNutritionLog(nutritionLogDTO);

        ModelAndView modelAndView = new ModelAndView("redirect:/nutritionlogs");
        modelAndView.addObject("nutritionLog", createdNutritionLog);

        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateNutritionLog(NutritionLogDTO nutritionLogDTO, @PathVariable Long id, Authentication auth) {

        UserEntity user = userService.findUserByUsername(auth.getName()).orElseThrow(() -> new IllegalStateException("Username not found!" + auth.getName()));
        nutritionLogDTO.setUserId(user.getId());


        nutritionLogService.updateNutritionLog(id, nutritionLogDTO);
        return new ModelAndView("redirect:/nutritionlogs");
    }



    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        nutritionLogService.deleteNutritionLog(id);

        return new ModelAndView("redirect:/nutritionlogs");
    }

    @PostMapping(value = "/{id}", consumes = "application/json")
    public ModelAndView update(@PathVariable Long id, @RequestBody NutritionLogDTO nutritionLogDTO) {
        return updateNutritionLog(id, nutritionLogDTO);
    }

    private ModelAndView updateNutritionLog(Long id, NutritionLogDTO nutritionLogDTO) {
        NutritionLogDTO updatedNutritionLog = nutritionLogService.updateNutritionLog(id, nutritionLogDTO);

        ModelAndView modelAndView = new ModelAndView("redirect:/nutritionlogs");
        modelAndView.addObject("nutritionLog", updatedNutritionLog);

        return modelAndView;
    }
}
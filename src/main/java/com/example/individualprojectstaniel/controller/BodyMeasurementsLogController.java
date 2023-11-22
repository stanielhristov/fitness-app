package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.BodyMeasurementsLogDTO;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.service.BodyMeasurementsLogService;
import com.example.individualprojectstaniel.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/bodymeasurementslogs")
public class BodyMeasurementsLogController {
    private final BodyMeasurementsLogService bodyMeasurementsLogService;
    private final UserService userService;

    public BodyMeasurementsLogController(BodyMeasurementsLogService bodyMeasurementsLogService, UserService userService) {
        this.bodyMeasurementsLogService = bodyMeasurementsLogService;
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getAllBodyMeasurementsLogsForCurrentUser() {
        ModelAndView modelAndView = new ModelAndView("bodymeasurementslogs");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.findUserByUsername(auth.getName()).orElseThrow(() -> new IllegalStateException("Username not found!" + auth.getName()));

        List<BodyMeasurementsLogDTO> bodyMeasurementsLogs = bodyMeasurementsLogService.getAllBodyMeasurementsLogsByUserId(user.getId());

        modelAndView.addObject("bodymeasurementslogs", bodyMeasurementsLogs);

        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id) {
        BodyMeasurementsLogDTO bodyMeasurementsLog = bodyMeasurementsLogService.getBodyMeasurementsLogById(id);

        ModelAndView modelAndView = new ModelAndView();

        if (bodyMeasurementsLog != null) {
            modelAndView.setViewName("updatebodymeasurementslog");
            modelAndView.addObject("bodyMeasurementsLogDTO", bodyMeasurementsLog);
        } else {
            modelAndView.setViewName("error-page");
        }

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createBodyMeasurementsLog() {
        return new ModelAndView("add-bodymeasurementslog");
    }

    @PostMapping()
    public ModelAndView createBodyMeasurementsLog(BodyMeasurementsLogDTO bodyMeasurementsLogDTO, Authentication auth) {
        UserEntity user = userService.findUserByUsername(auth.getName()).orElseThrow(() -> new IllegalStateException("Username not found!" + auth.getName()));
        bodyMeasurementsLogDTO.setUserId(user.getId());

        BodyMeasurementsLogDTO createdBodyMeasurementsLog = bodyMeasurementsLogService.createBodyMeasurementsLog(bodyMeasurementsLogDTO);

        ModelAndView modelAndView = new ModelAndView("redirect:/bodymeasurementslogs");
        modelAndView.addObject("bodyMeasurementsLog", createdBodyMeasurementsLog);

        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateBodyMeasurementsLog(BodyMeasurementsLogDTO bodyMeasurementsLogDTO, @PathVariable Long id, Authentication auth) {
        UserEntity user = userService.findUserByUsername(auth.getName()).orElseThrow(() -> new IllegalStateException("Username not found!" + auth.getName()));
        bodyMeasurementsLogDTO.setUserId(user.getId());

        bodyMeasurementsLogService.updateBodyMeasurementsLog(id, bodyMeasurementsLogDTO, auth);

        return new ModelAndView("redirect:/bodymeasurementslogs");
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        bodyMeasurementsLogService.deleteBodyMeasurementsLog(id);

        return new ModelAndView("redirect:/bodymeasurementslogs");
    }

}

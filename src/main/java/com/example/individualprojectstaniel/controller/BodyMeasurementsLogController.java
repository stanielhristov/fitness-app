package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.BodyMeasurementsLogDTO;
import com.example.individualprojectstaniel.service.BodyMeasurementsLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/bodymeasurementslogs")
public class BodyMeasurementsLogController {
    private final BodyMeasurementsLogService bodyMeasurementsLogService;

    public BodyMeasurementsLogController(BodyMeasurementsLogService bodyMeasurementsLogService) {
        this.bodyMeasurementsLogService = bodyMeasurementsLogService;
    }

    @GetMapping
    public ModelAndView getAllNutritionLogs() {
        List<BodyMeasurementsLogDTO> bodyMeasurementsLogs = bodyMeasurementsLogService.getAllBodyMeasurementsLogs();

        ModelAndView modelAndView = new ModelAndView("bodymeasurementslogs");
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
    public ModelAndView createBodyMeasurementsLog(BodyMeasurementsLogDTO bodyMeasurementsLogDTO) {
        BodyMeasurementsLogDTO createdBodyMeasurementsLog = bodyMeasurementsLogService.createBodyMeasurementsLog(bodyMeasurementsLogDTO);

        ModelAndView modelAndView = new ModelAndView("redirect:/bodymeasurementslogs");
        modelAndView.addObject("bodyMeasurementsLog", createdBodyMeasurementsLog);

        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateBodyMeasurementsLog(BodyMeasurementsLogDTO bodyMeasurementsLogDTO, @PathVariable Long id) {
        BodyMeasurementsLogDTO updateBodyMeasurementsLog = bodyMeasurementsLogService.updateBodyMeasurementsLog(id, bodyMeasurementsLogDTO);

        ModelAndView modelAndView = new ModelAndView("redirect:/bodymeasurementslogs");

        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView modelAndView;
        modelAndView = deleteBodyMeasurementsLog(id);

        return modelAndView;
    }

    @PostMapping(value = "/{id}", consumes = "application/json")
    public ModelAndView update(@PathVariable Long id, @RequestBody BodyMeasurementsLogDTO bodyMeasurementsLogDTO) {
        ModelAndView modelAndView;
        modelAndView = updateBodyMeasurementsLog(id, bodyMeasurementsLogDTO);

        return modelAndView;
    }


    private ModelAndView updateBodyMeasurementsLog(Long id, BodyMeasurementsLogDTO bodyMeasurementsLogDTO) {
        BodyMeasurementsLogDTO updatedBodyMeasurementsLog = bodyMeasurementsLogService.updateBodyMeasurementsLog(id, bodyMeasurementsLogDTO);

        ModelAndView modelAndView = new ModelAndView("redirect:/bodymeasurementslogs");
        modelAndView.addObject("bodyMeasurementsLog", updatedBodyMeasurementsLog);

        return modelAndView;
    }

    private ModelAndView deleteBodyMeasurementsLog(Long id) {
        bodyMeasurementsLogService.deleteBodyMeasurementsLog(id);

        return new ModelAndView("redirect:/bodymeasurementslogs");
    }
}

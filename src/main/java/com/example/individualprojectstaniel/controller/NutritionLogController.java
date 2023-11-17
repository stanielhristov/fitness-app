package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.NutritionLogDTO;
import com.example.individualprojectstaniel.service.NutritionLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/nutritionlogs")
public class NutritionLogController {

    private final NutritionLogService nutritionLogService;

    public NutritionLogController(NutritionLogService nutritionLogService) {
        this.nutritionLogService = nutritionLogService;
    }

    @GetMapping
    public ModelAndView getAllNutritionLogs() {
        List<NutritionLogDTO> nutritionLogs = nutritionLogService.getAllNutritionLogs();

        ModelAndView modelAndView = new ModelAndView("nutritionlogs");
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
    public ModelAndView createNutritionLog(NutritionLogDTO nutritionLogDTO) {
        NutritionLogDTO createdNutritionLog = nutritionLogService.createNutritionLog(nutritionLogDTO);

        ModelAndView modelAndView = new ModelAndView("add-nutritionlog");
        modelAndView.addObject("nutritionLog", createdNutritionLog);

        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateNutritionLog(NutritionLogDTO nutritionLogDTO, @PathVariable Long id) {
        NutritionLogDTO updateNutritionLog = nutritionLogService.updateNutritionLog(id, nutritionLogDTO);

        ModelAndView modelAndView = new ModelAndView("redirect:/nutritionlogs");

        return modelAndView;
    }



    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView modelAndView;
        modelAndView = deleteNutritionLog(id);

        return modelAndView;
    }

    @PostMapping(value = "/{id}", consumes = "application/json")
    public ModelAndView update(@PathVariable Long id, @RequestBody NutritionLogDTO nutritionLogDTO) {
        ModelAndView modelAndView;
        modelAndView = updateNutritionLog(id, nutritionLogDTO);

        return modelAndView;
    }

    private ModelAndView updateNutritionLog(Long id, NutritionLogDTO nutritionLogDTO) {
        NutritionLogDTO updatedNutritionLog = nutritionLogService.updateNutritionLog(id, nutritionLogDTO);

        ModelAndView modelAndView = new ModelAndView("redirect:/nutritionlogs");
        modelAndView.addObject("nutritionLog", updatedNutritionLog);

        return modelAndView;
    }

    private ModelAndView deleteNutritionLog(Long id) {
        nutritionLogService.deleteNutritionLog(id);

        return new ModelAndView("redirect:/nutritionlogs");
    }

}
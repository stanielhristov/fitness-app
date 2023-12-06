package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.BMIDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/bmicalculator")
public class BMIController {

    @GetMapping
    public ModelAndView calculateBmi() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("bmi", 0);
        modelAndView.setViewName("bmicalculator");

        return modelAndView;
    }


    @PostMapping
    public ModelAndView calculateBmi(@ModelAttribute BMIDto bmiDto) {
        String errorMessage = null;
        try {
            validateBmi(bmiDto);
        } catch (IllegalArgumentException ex) {
            errorMessage = ex.getMessage();
        }

        ModelAndView modelAndView = new ModelAndView("bmicalculator");
        if (errorMessage != null) {
            modelAndView.addObject("errorMessage", errorMessage);
            return modelAndView;
        }

        double heightInMeters = bmiDto.getHeight() / 100;
        double bmi = bmiDto.getWeight() / (heightInMeters * heightInMeters);
        String formattedBMI = String.format("%.2f", bmi);
        modelAndView.addObject("bmi", formattedBMI);
        modelAndView.setViewName("bmicalculator");

        return modelAndView;
    }

    public void validateBmi(BMIDto bmiDto) {
        if (bmiDto.getHeight() < 10) {
            throw new IllegalArgumentException("Height must be greater than 10");
        }
    }
}

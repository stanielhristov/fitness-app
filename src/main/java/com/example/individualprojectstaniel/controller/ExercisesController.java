package com.example.individualprojectstaniel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/exercises")
public class ExercisesController {

    @GetMapping()
    public ModelAndView exercises() {
        return new ModelAndView("exercises");
    }
}

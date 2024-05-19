package com.example.individualprojectstaniel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cardioexercises")
public class CardioExercisesController {

    @GetMapping()
    public ModelAndView cardioexercises() {
        return new ModelAndView("cardioexercises");
    }
}
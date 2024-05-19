package com.example.individualprojectstaniel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mealdetails")
public class MealDetailsController {

    @GetMapping()
    public ModelAndView mealDetails() {
        return new ModelAndView("mealdetails");
    }
}

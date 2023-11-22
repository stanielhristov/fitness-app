package com.example.individualprojectstaniel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/homepage")
public class HomeController {

    @GetMapping()
    public ModelAndView home() {
        return new ModelAndView("homepage");
    }


    @GetMapping("/error")
    public ModelAndView error(BindingResult result) {
        return new ModelAndView("homepage");
    }

}

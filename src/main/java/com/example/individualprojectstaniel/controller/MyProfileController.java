package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.MyProfileDTO;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/myprofile")
public class MyProfileController {
    private final UserService userService;

    public MyProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ModelAndView myProfileHome() {

        ModelAndView modelAndView = new ModelAndView("myprofile");
        MyProfileDTO myProfileDTO = new MyProfileDTO();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.findUserByUsername(auth.getName()).orElseThrow(() -> new IllegalStateException("Username not found!" + auth.getName()));

        myProfileDTO.setHeight(user.getHeight());
        myProfileDTO.setWeight(user.getWeight());
        Double BMI = myProfileDTO.getWeight() / (myProfileDTO.getHeight() / 100 * myProfileDTO.getHeight() / 100);
        myProfileDTO.setBMI(BMI);

        modelAndView.addObject("profile", myProfileDTO);

        return modelAndView;
    }

    @GetMapping("/error")
    public ModelAndView error(BindingResult result) {
        return new ModelAndView("myprofile");
    }
}

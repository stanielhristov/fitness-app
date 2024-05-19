package com.example.individualprojectstaniel.controller;


import com.example.individualprojectstaniel.model.dto.ResetPasswordDTO;
import com.example.individualprojectstaniel.model.dto.UserRegisterBindingModel;
import com.example.individualprojectstaniel.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public ModelAndView login() {

        return new ModelAndView("login");
    }


    @GetMapping("/login/error")
    public ModelAndView loginError() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("loginError", "Wrong password or username");

        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("register");

            modelAndView.addObject("errorMessage", bindingResult.getAllErrors().get(0).getDefaultMessage());
            modelAndView.addObject("userState", userRegisterBindingModel);

            return modelAndView;
        }
        boolean isRegistered;
        try {
            isRegistered = userService.register(userRegisterBindingModel);
        } catch (IllegalArgumentException e) {
            ModelAndView modelAndView = new ModelAndView("register");
            modelAndView.addObject("errorMessage", e.getMessage());
            modelAndView.addObject("userState", userRegisterBindingModel);
            return modelAndView;
        }

        String view = isRegistered ? "redirect:/login" : "register";

        return new ModelAndView(view);
    }

    @GetMapping("/resetpassword")
    public ModelAndView resetPassword() {
        return new ModelAndView("resetpassword");
    }

    @PostMapping("/resetpassword")
    public ModelAndView resetPassword(ResetPasswordDTO resetPasswordDTO) {
        ModelAndView modelAndView = new ModelAndView();

        if (userService.validateCurrentPassword(resetPasswordDTO)) {

            userService.updatePassword(resetPasswordDTO);
            modelAndView.addObject("successMessage", "Password reset successfully!");
            modelAndView.setViewName("login");
        } else {
            modelAndView.addObject("errorMessage", "Incorrect current password.");
            modelAndView.setViewName("redirect:/resetpassword?error");
        }

        return modelAndView;
    }

}

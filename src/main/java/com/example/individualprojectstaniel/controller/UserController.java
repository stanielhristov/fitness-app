package com.example.individualprojectstaniel.controller;


import com.example.individualprojectstaniel.model.dto.UserLoginBindingModel;
import com.example.individualprojectstaniel.model.dto.UserRegisterBindingModel;
import com.example.individualprojectstaniel.service.LoggedUser;
import com.example.individualprojectstaniel.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
//        if (loggedUser.isLogged()) {
//            return new ModelAndView("redirect:/home");
//        }

        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(
            @Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
            BindingResult bindingResult) {
//        if (loggedUser.isLogged()) {
//            return new ModelAndView("redirect:/home");
//        }

//        boolean hasErrors = bindingResult.hasErrors();
//
//        if (hasErrors) {
//
//        }

        boolean isLogged = userService.login(userLoginBindingModel);

        String view = isLogged ? "index" : "login";

        return new ModelAndView(view);
    }
    @GetMapping("/register")
    public ModelAndView register() {
//        if (loggedUser.isLogged()) {
//            return new ModelAndView("redirect:/home");
//        }

        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(UserRegisterBindingModel userRegisterBindingModel) {
//        if (loggedUser.isLogged()) {
//            return new ModelAndView("redirect:/home");
//        }

       boolean isRegistered = userService.register(userRegisterBindingModel);

        String view = isRegistered ? "redirect:/login" : "register";

        return new ModelAndView(view);
    }


//
//    @PostMapping("/logout")
//    public ModelAndView logout() {
////        if (!loggedUser.isLogged()) {
////            return new ModelAndView("redirect:/home");
////        }
//
//        userService.logout();
//        return new ModelAndView("login");
//    }


}

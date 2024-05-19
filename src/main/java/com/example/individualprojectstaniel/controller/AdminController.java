package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.UserDTO;
import com.example.individualprojectstaniel.model.entity.UserRoleEntity;
import com.example.individualprojectstaniel.repository.UserRoleRepository;
import com.example.individualprojectstaniel.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/adminpanel")
@RolesAllowed(value = {"ADMIN"})
public class AdminController {

    private final UserService userService;
    private final UserRoleRepository roleRepository;

    public AdminController(UserService userService, UserRoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }


    @GetMapping
    public String getAllUsers(Model model) {

        List<UserDTO> users = userService.getAllUsers();

        model.addAttribute("users", users);

        return "adminpanel";
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        userService.deleteUser(id);

        return new ModelAndView("redirect:/adminpanel");
    }

    @GetMapping("/changerole/{id}")
    public ModelAndView showChangeRoleForm(@PathVariable Long id) {
        UserDTO userDTO = userService.getCurrentUserById(id);

        ModelAndView modelAndView = new ModelAndView();

        if (userDTO != null) {
            modelAndView.setViewName("changerole");
            modelAndView.addObject("userDTO", userDTO);
            modelAndView.addObject("roles", roleRepository.findAll().stream().map(UserRoleEntity::getName).toList());
        } else {
            modelAndView.setViewName("error-page");
        }

        return modelAndView;
    }

    @PostMapping("/changerole/{id}")
    public ModelAndView changeUserRole(@PathVariable Long id, @RequestParam String role) {
        userService.changeRole(id, role);
        return new ModelAndView("redirect:/adminpanel");
    }

}

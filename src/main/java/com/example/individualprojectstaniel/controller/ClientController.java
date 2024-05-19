package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.ClientDTO;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.service.ClientService;
import com.example.individualprojectstaniel.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/clients")

public class ClientController {
    private final ClientService clientService;
    private final UserService userService;

    public ClientController(ClientService clientService, UserService userService) {
        this.clientService = clientService;
        this.userService = userService;
    }

    @GetMapping("")
    public ModelAndView clients(Authentication auth) {
        UserEntity user = userService.findUserByUsername(auth.getName()).orElseThrow(() -> new IllegalStateException("Username not found!" + auth.getName()));
        List<ClientDTO> clients = clientService.getAllClientsByTrainer(user.getId());

        ModelAndView modelAndView = new ModelAndView("clients");
        modelAndView.addObject("clients", clients);

        return modelAndView;

    }
}

package com.example.individualprojectstaniel.controller;


import com.example.individualprojectstaniel.model.dto.MessagesDTO;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.service.MessagesService;
import com.example.individualprojectstaniel.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessagesController {

    private final MessagesService messagesService;
    private final UserService userService;

    public MessagesController(MessagesService messagesService, UserService userService) {
        this.messagesService = messagesService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    ModelAndView byId(@PathVariable Long id) {
        MessagesDTO message = messagesService.getById(id);

        ModelAndView modelAndView = new ModelAndView("message");
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @GetMapping("/inbox")
    ModelAndView inbox() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.findUserByUsername(auth.getName()).orElseThrow(() -> new IllegalStateException("Username not found!" + auth.getName()));

        List<MessagesDTO> messages = messagesService.getReceivedMessagesByUser(user.getId());

        ModelAndView modelAndView = new ModelAndView("inbox");
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }

    @GetMapping("/sent")
    ModelAndView sent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.findUserByUsername(auth.getName()).orElseThrow(() -> new IllegalStateException("Username not found!" + auth.getName()));

        List<MessagesDTO> messages = messagesService.getSentMessagesByUser(user.getId());

        ModelAndView modelAndView = new ModelAndView("sentmessages");
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }

    @GetMapping("/send")
    ModelAndView send() {
        return new ModelAndView("sendmessage");
    }

    @PostMapping
    ModelAndView send(MessagesDTO message) {
        messagesService.send(message);
        return new ModelAndView("redirect:/messages/inbox");
    }

    @GetMapping("/view/{id}")
    public ModelAndView showViewMessage(@PathVariable Long id, Authentication loginUser) {
        MessagesDTO messagesDTO = messagesService.getById(id);
        if (messagesDTO == null) {
            return new ModelAndView("error-page");
        }

        if (!messagesDTO.getTo().equals(loginUser.getName()) && !messagesDTO.getFrom().equals(loginUser.getName())) {
            return new ModelAndView("error-page");
        }

        ModelAndView modelAndView = new ModelAndView("viewmessage");
        modelAndView.addObject("message", messagesDTO);

        return modelAndView;
    }


    @DeleteMapping("/inbox/{id}")
    public ModelAndView deleteInboxMessages(@PathVariable Long id) {
        messagesService.deleteMessage(id);

        return new ModelAndView("redirect:/messages/inbox");
    }

    @DeleteMapping("/sent/{id}")
    public ModelAndView deleteSentMessages(@PathVariable Long id) {
        messagesService.deleteMessage(id);

        return new ModelAndView("redirect:/messages/sent");
    }
}

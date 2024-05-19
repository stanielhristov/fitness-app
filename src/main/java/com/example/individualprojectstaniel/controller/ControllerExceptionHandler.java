package com.example.individualprojectstaniel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.security.access.AccessDeniedException;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String globalException(Exception e) {
        log.error("Exception: {}", e.getMessage(), e);
        return "error-page";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String denied(Exception e) {
        log.error("Access denied: {}", e.getMessage(), e);
        return "denied-page";
    }
}

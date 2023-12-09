package com.example.individualprojectstaniel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String globalException(Exception e) {
        log.error("Exception: {}", e.getMessage(), e);
        return "error-page";
    }
}

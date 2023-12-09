package com.example.individualprojectstaniel.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;

public class HomeControllerTest {
    @MockBean
    private HomeController homeController;

    @BeforeEach
    void setup() {
        homeController = new HomeController();
    }

    @Test
    void homepage() {
        ModelAndView actual = homeController.home();

        assertThat(actual.getViewName()).isEqualTo("homepage");
    }
}

package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.controller.HomeController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;

public class HomeControllerTest {
    @MockBean
    private HomeController homeController;


    @Test
    void homepage() {
        ModelAndView actual = homeController.home();

        assertThat(actual.getViewName()).isEqualTo("homepage");
    }
}

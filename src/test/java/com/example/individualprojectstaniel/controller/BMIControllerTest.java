package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.BMIDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BMIControllerTest {

    @MockBean
    private BMIController bmiController;

    @Test
    void testCalculateBmi() {
        BMIController bmiController = new BMIController();
        ModelAndView result = bmiController.calculateBmi();


        assertEquals("bmicalculator", result.getViewName());
        assertEquals(0, result.getModel().get("bmi"));
    }

    @Test
    void testCalculateBmiWithValidData() {
        BMIController bmiController = new BMIController();
        BMIDto bmiDto = new BMIDto();
        bmiDto.setHeight(170.0);
        bmiDto.setWeight(70.0);

        ModelAndView result = bmiController.calculateBmi(bmiDto);

        assertEquals("bmicalculator", result.getViewName());

        assertEquals("24.22", result.getModel().get("bmi"));
    }

    @Test
    void testCalculateBmiWithInvalidHeight() {
        BMIController bmiController = new BMIController();
        BMIDto bmiDto = new BMIDto();
        bmiDto.setHeight(5.0);

        ModelAndView result = bmiController.calculateBmi(bmiDto);

        assertEquals("bmicalculator", result.getViewName());


        assertEquals("Height must be greater than 10", result.getModel().get("errorMessage"));

    }
}

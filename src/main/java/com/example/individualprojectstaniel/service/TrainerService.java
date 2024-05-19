package com.example.individualprojectstaniel.service;

import com.example.individualprojectstaniel.model.dto.AskQuestionDto;
import com.example.individualprojectstaniel.model.dto.TrainerDTO;

import java.util.List;

public interface TrainerService {
    List<TrainerDTO> getAllTrainers();

    TrainerDTO getById(Long id);

    void askTrainer(String to, AskQuestionDto question);

    void trainMe(TrainerDTO username);

}

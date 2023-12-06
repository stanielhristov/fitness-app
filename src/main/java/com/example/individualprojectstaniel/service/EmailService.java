package com.example.individualprojectstaniel.service;

import com.example.individualprojectstaniel.model.dto.EmailDTO;
import com.example.individualprojectstaniel.model.dto.UserDTO;
import com.example.individualprojectstaniel.model.dto.WorkoutLogDTO;

public interface EmailService {
    EmailDTO createEmail(UserDTO user, WorkoutLogDTO last);

    void send(EmailDTO email);
}

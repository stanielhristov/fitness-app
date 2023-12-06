package com.example.individualprojectstaniel.schedule;

import com.example.individualprojectstaniel.model.dto.EmailDTO;
import com.example.individualprojectstaniel.model.dto.UserDTO;
import com.example.individualprojectstaniel.model.dto.WorkoutLogDTO;
import com.example.individualprojectstaniel.service.EmailService;
import com.example.individualprojectstaniel.service.UserService;
import com.example.individualprojectstaniel.service.WorkoutLogService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class WorkoutReminderSchedule {

    private final UserService userService;
    private final WorkoutLogService workoutLogService;
    private final EmailService emailService;

    public WorkoutReminderSchedule(UserService userService, WorkoutLogService workoutLogService, EmailService emailService) {
        this.userService = userService;
        this.workoutLogService = workoutLogService;
        this.emailService = emailService;
    }

    @Scheduled(cron= "0/10 * * ? * *")
    void schedule() {
        List<UserDTO> users = userService.findALl();

        for (UserDTO user : users) {
            WorkoutLogDTO last = workoutLogService.getAllWorkoutLogsByUserId(user.getId()).get(0);
            LocalDate lastDate = last.getDate();

            if (lastDate.plusDays(3).isBefore(LocalDate.now())) {
                EmailDTO email = EmailDTO.createEmail(user);
                emailService.send(email);
            }
        }
    }
}

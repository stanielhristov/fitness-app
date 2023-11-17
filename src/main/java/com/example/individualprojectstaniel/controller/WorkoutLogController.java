package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.WorkoutLogDTO;
import com.example.individualprojectstaniel.service.WorkoutLogService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/workoutlogs")
public class WorkoutLogController {
    private final WorkoutLogService workoutLogService;
    private final ModelMapper modelMapper;

    public WorkoutLogController(WorkoutLogService workoutLogService, ModelMapper modelMapper) {
        this.workoutLogService = workoutLogService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ModelAndView getAllWorkoutLogs() {
        List<WorkoutLogDTO> workoutLogs = workoutLogService.getAllWorkoutLogs();
        List<WorkoutLogDTO> workoutLogDTOs = workoutLogs.stream()
                .map(workoutLog -> modelMapper.map(workoutLog, WorkoutLogDTO.class))
                .collect(Collectors.toList());

        ModelAndView modelAndView = new ModelAndView("workout-logs");
        modelAndView.addObject("workoutlogs", workoutLogDTOs);
        return modelAndView;
    }
    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id) {
        WorkoutLogDTO workoutLog = workoutLogService.getWorkoutLogById(id);

        ModelAndView modelAndView = new ModelAndView();

        if (workoutLog != null) {
            modelAndView.setViewName("updateworkoutlog");
            modelAndView.addObject("workoutLogDTO", workoutLog);
        } else {
            modelAndView.setViewName("error-page");
        }

        return modelAndView;
    }


    @GetMapping("/{id}")
    public ResponseEntity<WorkoutLogDTO> getWorkoutLogById(@PathVariable Long id) {
        WorkoutLogDTO workoutLog = workoutLogService.getWorkoutLogById(id);

        if (workoutLog == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout log not found");
        }
        WorkoutLogDTO workoutLogDTO = modelMapper.map(workoutLog, WorkoutLogDTO.class);

        return ResponseEntity.ok(workoutLogDTO);
    }

    @GetMapping("/create")
    public ModelAndView createWorkoutLog() {

        return new ModelAndView("add-workout-log");
    }

    @PostMapping
    public ModelAndView createWorkoutLog(WorkoutLogDTO workoutLogDTO) {

        workoutLogDTO.setCompleted(workoutLogDTO.isCompleted());

        WorkoutLogDTO createdWorkoutLog = workoutLogService.createWorkoutLog(workoutLogDTO);

        ModelAndView modelAndView = new ModelAndView("add-workout-log");
        modelAndView.addObject("workoutLog", createdWorkoutLog);


        return modelAndView;
//        WorkoutLogDTO workoutLog = modelMapper.map(createDTO, WorkoutLogDTO.class);
//        WorkoutLogDTO createdWorkoutLog = workoutLogService.createWorkoutLog(workoutLog);
//        WorkoutLogDTO workoutLogDTO = modelMapper.map(createdWorkoutLog, WorkoutLogDTO.class);
//
//        return new ResponseEntity<>(workoutLogDTO, HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateWorkoutLog(WorkoutLogDTO workoutLogDTO, @PathVariable Long id) {
        WorkoutLogDTO updateWorkoutLogLog = workoutLogService.updateWorkoutLog(id, workoutLogDTO);

        ModelAndView modelAndView = new ModelAndView("redirect:/workoutlogs");

        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView modelAndView;
        modelAndView = deleteWorkoutLog(id);

        return modelAndView;
    }

    @PostMapping(value = "/{id}", consumes = "application/json")
    public ModelAndView update(@PathVariable Long id, @RequestBody WorkoutLogDTO workoutLogDTO) {
        ModelAndView modelAndView;
        modelAndView = updatedWorkoutLog(id, workoutLogDTO);

        return modelAndView;
    }


    private ModelAndView updatedWorkoutLog(Long id, WorkoutLogDTO workoutLogDTO) {
        WorkoutLogDTO updatedWorkoutLog = workoutLogService.updateWorkoutLog(id, workoutLogDTO);

        ModelAndView modelAndView = new ModelAndView("redirect:/workoutlogs");
        modelAndView.addObject("workoutLog", updatedWorkoutLog);

        return modelAndView;
    }

    private ModelAndView deleteWorkoutLog(Long id) {
        workoutLogService.deleteWorkoutLog(id);

        return new ModelAndView("redirect:/workoutlogs");
    }

}
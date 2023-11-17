package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.GoalsDTO;
import com.example.individualprojectstaniel.service.GoalsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goals")
public class GoalsController {

    private GoalsService goalsService;

    @PostMapping
    public ResponseEntity<GoalsDTO> createGoals(@RequestBody GoalsDTO goalsDTO) {
        GoalsDTO createdGoals = goalsService.createGoals(goalsDTO);

        if (createdGoals != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGoals);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoalsDTO> getGoalsById(@PathVariable Long id) {
        GoalsDTO goals = goalsService.getGoalsById(id);
        if (goals != null) {
            return ResponseEntity.ok(goals);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<GoalsDTO> getAllGoals() {
        return goalsService.getAllGoals();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GoalsDTO> updateGoals(@PathVariable Long id, @RequestBody GoalsDTO updatedGoalsDTO) {
        GoalsDTO updatedGoals = goalsService.updateGoals(id, updatedGoalsDTO);
        if (updatedGoals != null) {
            return ResponseEntity.ok(updatedGoals);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoals(@PathVariable Long id) {
        goalsService.deleteGoals(id);
        return ResponseEntity.noContent().build();
    }
}
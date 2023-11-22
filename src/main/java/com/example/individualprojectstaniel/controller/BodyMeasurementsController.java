package com.example.individualprojectstaniel.controller;

import com.example.individualprojectstaniel.model.dto.BodyMeasurementsDTO;
import com.example.individualprojectstaniel.service.BodyMeasurementsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/body-measurements")
//TODO Delete
public class BodyMeasurementsController {

    private final BodyMeasurementsService bodyMeasurementsService;

    public BodyMeasurementsController(BodyMeasurementsService bodyMeasurementsService) {
        this.bodyMeasurementsService = bodyMeasurementsService;
    }

    @PostMapping
    public ResponseEntity<BodyMeasurementsDTO> create(@RequestBody BodyMeasurementsDTO bodyMeasurementsDto) {
        return new ResponseEntity<>(bodyMeasurementsService.create(bodyMeasurementsDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BodyMeasurementsDTO> update(@PathVariable Long id, @RequestBody BodyMeasurementsDTO bodyMeasurementsDto) {
        return new ResponseEntity<>(bodyMeasurementsService.update(id, bodyMeasurementsDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bodyMeasurementsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BodyMeasurementsDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(bodyMeasurementsService.findById(id), HttpStatus.OK);
    }
}

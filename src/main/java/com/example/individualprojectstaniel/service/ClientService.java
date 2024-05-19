package com.example.individualprojectstaniel.service;

import com.example.individualprojectstaniel.model.dto.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClientsByTrainer(Long trainerId);


}

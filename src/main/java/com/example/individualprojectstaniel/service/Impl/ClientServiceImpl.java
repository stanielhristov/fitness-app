package com.example.individualprojectstaniel.service.Impl;

import com.example.individualprojectstaniel.model.dto.ClientDTO;
import com.example.individualprojectstaniel.model.entity.TrainerClientEntity;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.repository.TrainerClientRepository;
import com.example.individualprojectstaniel.repository.UserRepository;
import com.example.individualprojectstaniel.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final TrainerClientRepository trainerClientRepository;
    private final UserRepository userRepository;

    public ClientServiceImpl(TrainerClientRepository trainerClientRepository, UserRepository userRepository) {
        this.trainerClientRepository = trainerClientRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<ClientDTO> getAllClientsByTrainer(Long trainerId) {
        List<Long> clientIds = trainerClientRepository.findAllByTrainerId(trainerId).stream()
                .map(TrainerClientEntity::getClientId)
                .distinct()
                .toList();
        List<ClientDTO> clients = new ArrayList<>();

        for (Long clientId : clientIds) {
            UserEntity user = userRepository.findById(clientId).orElseThrow(()-> new NullPointerException("No user found"));

            ClientDTO client = new ClientDTO(user.getId(), user.getUsername(), user.getEmail(), user.getGender().toString());

            clients.add(client);
        }

        return clients;

    }



    public Boolean findClientByIdAndTrainerId(Long clientId, Long trainerId) {
        return trainerClientRepository.existsByTrainerIdAndClientId(clientId, trainerId);
    }


}

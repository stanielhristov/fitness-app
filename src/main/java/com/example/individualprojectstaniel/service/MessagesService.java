package com.example.individualprojectstaniel.service;

import com.example.individualprojectstaniel.model.dto.MessagesDTO;

import java.util.List;

public interface MessagesService {
    MessagesDTO send (MessagesDTO message);
    MessagesDTO getById(Long id);
    List<MessagesDTO> getReceivedMessagesByUser (Long userId);
    List<MessagesDTO> getSentMessagesByUser (Long userId);


    boolean deleteMessage(Long id);

    void sendConfirmationMessage(String clientId);

    void sendDeclineMessage(String clientId);
}

package com.example.individualprojectstaniel.service.Impl;

import com.example.individualprojectstaniel.model.dto.MessagesDTO;
import com.example.individualprojectstaniel.model.entity.MessageEntity;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.repository.MessagesRepository;
import com.example.individualprojectstaniel.repository.UserRepository;
import com.example.individualprojectstaniel.service.MessagesService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessagesServiceImpl implements MessagesService {
    private final MessagesRepository messagesRepository;
    private final UserRepository userRepository;

    public MessagesServiceImpl(MessagesRepository messagesRepository, UserRepository userRepository) {
        this.messagesRepository = messagesRepository;
        this.userRepository = userRepository;
    }


    @Override
    public MessagesDTO send(MessagesDTO message) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity to = userRepository.findByUsername(message.getTo()).orElseThrow();
        UserEntity from = userRepository.findByUsername(auth.getName()).orElseThrow(() -> new IllegalStateException("Username not found!" + auth.getName()));

        MessageEntity messageEntity = new MessageEntity(message, to, from);

        MessageEntity save = messagesRepository.save(messageEntity);
        return save.toDto();
    }

    @Override
    public MessagesDTO getById(Long id) {
        return messagesRepository.findById(id)
                .map(MessageEntity::toDto)
                .orElseThrow(() -> new IllegalStateException("Message with id: " + id + " not found"));
    }

    @Override
    public List<MessagesDTO> getReceivedMessagesByUser(Long userId) {
        UserEntity toUser = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User with id: " + userId + " not found"));
        return messagesRepository.findAllByTo(toUser).stream()
                .map(MessageEntity::toDto)
                .toList();
    }

    @Override
    public List<MessagesDTO> getSentMessagesByUser(Long userId) {
        UserEntity fromUser = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User with id: " + userId + " not found"));
        return messagesRepository.findAllByFrom(fromUser).stream()
                .map(MessageEntity::toDto)
                .toList();
    }

    @Override
    public boolean deleteMessage(Long id) {
        if (messagesRepository.existsById(id)) {
            messagesRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void sendConfirmationMessage(String clientId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity client = userRepository.findById(Long.parseLong(clientId))
                .orElseThrow(() -> new IllegalStateException("User not found with id: " + clientId));

        String messageText = "Your trainer has accepted your request to train you!";


        MessagesDTO message = new MessagesDTO();
        message.setFrom(auth.getName());
        message.setTo(client.getUsername());
        message.setSubject("Confirmation Message");
        message.setBody(messageText);

        send(message);

    }

    @Override
    public void sendDeclineMessage(String clientId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity client = userRepository.findById(Long.parseLong(clientId))
                .orElseThrow(() -> new IllegalStateException("User not found with id: " + clientId));

        String messageText = "Unfortunately, the trainer has declined your training request.";

        MessagesDTO messageDTO = new MessagesDTO();
        messageDTO.setFrom(auth.getName());
        messageDTO.setTo(client.getUsername());
        messageDTO.setSubject("Training Request Declined");
        messageDTO.setBody(messageText);

        send(messageDTO);


    }


}

package com.example.individualprojectstaniel.service.Impl;

import com.example.individualprojectstaniel.model.dto.AskQuestionDto;
import com.example.individualprojectstaniel.model.dto.MessagesDTO;
import com.example.individualprojectstaniel.model.dto.TrainerDTO;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.repository.TrainerClientRepository;
import com.example.individualprojectstaniel.repository.UserRepository;
import com.example.individualprojectstaniel.service.TrainerService;
import com.example.individualprojectstaniel.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    private final UserRepository userRepository;
    private final MessagesServiceImpl messagesServiceImpl;
    private final UserService userService;
    private final TrainerClientRepository trainerClientRepository;

    public TrainerServiceImpl(UserRepository userRepository, MessagesServiceImpl messagesServiceImpl, UserService userService, TrainerClientRepository trainerClientRepository) {
        this.userRepository = userRepository;
        this.messagesServiceImpl = messagesServiceImpl;
        this.userService = userService;
        this.trainerClientRepository = trainerClientRepository;
    }

    @Override
    public List<TrainerDTO> getAllTrainers() {
        List<UserEntity> trainers = userRepository.findAll().stream().filter(UserEntity::isTrainer).toList();

        return trainers.stream().map(TrainerDTO::map).toList();
    }

    @Override
    public TrainerDTO getById(Long id) {


        return userRepository.findById(id).filter(UserEntity::isTrainer).map(TrainerDTO::map).orElseThrow(() -> new IllegalStateException("Trainer not found!"));
    }

    @Override
    public void askTrainer(String to, AskQuestionDto question) {
        String templateBody = """
                <h1> test </h1>
                Hello, Trainer %s
                You have a question from a client
                Subject: %s
                Question: %s
                """.formatted(to, question.subject(), question.body());

        MessagesDTO messagesDTO = new MessagesDTO();
        messagesDTO.setBody(templateBody);
        messagesDTO.setSubject(question.subject());
        messagesDTO.setTo(to);


        messagesServiceImpl.send(messagesDTO);
    }

    @Override
    public void trainMe(TrainerDTO trainer) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Long clientId = userService.findUserIdByUsername(username);

        String templateBody = generateTemplate(trainer, clientId);

        MessagesDTO messagesDTO = new MessagesDTO();
        messagesDTO.setBody(templateBody);
        messagesDTO.setTo(trainer.getUsername());
        messagesDTO.setSubject("Trainer request");

        messagesServiceImpl.send(messagesDTO);
    }

    private static String generateTemplate(TrainerDTO trainer, Long clientId) {
        Long trainerId = trainer.getId();
        String urlYes = String.format("http://localhost:8080/trainers/%s/respond/%s?answer=YES", trainerId, clientId);
        String urlNo = String.format("http://localhost:8080/trainers/%s/respond/%s?answer=NO", trainerId, clientId);

        return String.format("""
        <div class="email-template">
            <p>Hello, Trainer %s</p>
            <p>Client wants you to be his trainer.</p>
            <p>Click yes or no</p>
            <div class="button-group">
                <button onclick="window.location.href='%s'">YES</button>
                <button onclick="window.location.href='%s'">NO</button>
            </div>
        </div>
        """, trainer.getUsername(), urlYes, urlNo);
    }
}

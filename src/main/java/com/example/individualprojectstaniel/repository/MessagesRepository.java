package com.example.individualprojectstaniel.repository;

import com.example.individualprojectstaniel.model.entity.MessageEntity;
import com.example.individualprojectstaniel.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findAllByTo(UserEntity to);
    List<MessageEntity> findAllByFrom(UserEntity from);
}

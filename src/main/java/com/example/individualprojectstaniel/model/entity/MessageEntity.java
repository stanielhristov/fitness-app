package com.example.individualprojectstaniel.model.entity;

import com.example.individualprojectstaniel.model.dto.MessagesDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "messages")
public class MessageEntity extends BaseEntity{
    private UserEntity from;
    private UserEntity to;
    @Getter
    private String subject;
    @Getter @Column(length=8000)
    private String body;


    public MessageEntity() {
    }

    public MessageEntity(UserEntity from, UserEntity to, String subject, String body) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public MessageEntity(MessagesDTO message, UserEntity to, UserEntity from) {
        this.setId(message.getId());
        this.from = from;
        this.to = to;
        this.subject = message.getSubject();
        this.body = message.getBody();
    }

    @ManyToOne
    public UserEntity getFrom() {
        return from;
    }

    public void setFrom(UserEntity from) {
        this.from = from;
    }

    @ManyToOne
    public UserEntity getTo() {
        return to;
    }

    public void setTo(UserEntity to) {
        this.to = to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public MessagesDTO toDto() {
        MessagesDTO message = new MessagesDTO();
        message.setId(this.getId());
        message.setFrom(this.getFrom().getUsername());
        message.setTo(this.getTo().getUsername());
        message.setSubject(this.getSubject());
        message.setBody(this.getBody());
        return message;
    }
}

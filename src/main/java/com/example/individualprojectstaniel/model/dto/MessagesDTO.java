package com.example.individualprojectstaniel.model.dto;

import lombok.Data;

@Data
public class MessagesDTO {
    private Long id;
    private String from;
    private String to;
    private String subject;
    private String body;
}

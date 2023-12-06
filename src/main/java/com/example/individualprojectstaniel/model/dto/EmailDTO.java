package com.example.individualprojectstaniel.model.dto;

public class EmailDTO {
    private String to;
    private String from;


    private String subject;
    private String body;

    public EmailDTO() {
    }

    public static EmailDTO createEmail(UserDTO user) {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setTo("hristovstaniel@gmail.com");
        emailDTO.setFrom("fitnessapp@abv.bg");
        emailDTO.setSubject("Hello world");
        emailDTO.setBody("Hello world");

        return emailDTO;
    }

    public String getTo() {
        return to;
    }
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

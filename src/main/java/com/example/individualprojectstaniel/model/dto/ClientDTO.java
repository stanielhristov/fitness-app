package com.example.individualprojectstaniel.model.dto;

public record ClientDTO(Long id, String username, String email, String gender) {

    @Override
    public String toString() {
        return "ClientDTO[" +
                "id=" + id + ", " +
                "username=" + username + ", " +
                "email=" + email + ", " +
                "gender=" + gender + ']';
    }
}

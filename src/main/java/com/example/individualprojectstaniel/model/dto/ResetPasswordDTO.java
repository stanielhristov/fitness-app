package com.example.individualprojectstaniel.model.dto;

import lombok.Getter;

@Getter
public class ResetPasswordDTO {
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

package com.example.individualprojectstaniel.model.dto;

import com.example.individualprojectstaniel.model.enums.UserGenderEnum;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public class UserRegisterBindingModel {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private UserGenderEnum gender;
    private Integer height;
    private Integer weight;


    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 25, message = "Username length must be between 3 and 25 characters!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank(message = "Password is required")
    @Length(min = 6, message = "Password must be at least 6 characters long")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank(message = "Confirm password is required")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotNull(message = "Gender is required")
    public UserGenderEnum getGender() {
        return gender;
    }

    public void setGender(UserGenderEnum gender) {
        this.gender = gender;
    }

    @NotNull(message = "Height is required")
    @Positive(message = "Height must be a positive number")
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @NotNull(message = "Weight is required")
    @Positive(message = "Weight must be a positive number")
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}

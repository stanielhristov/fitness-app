package com.example.individualprojectstaniel.model.dto;

import com.example.individualprojectstaniel.model.entity.UserEntity;

public class MyProfileDTO {
    private Long id;

    public MyProfileDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String username;

    private String email;
    private String gender;
    private double height;
    private double weight;
    private double BMI;

    public MyProfileDTO(UserEntity user) {
        this.setId(user.getId());
        this.setUser(user.getUsername());
        this.setEmail(user.getEmail());
        this.setGender(user.getGender().toString());
        this.setHeight(user.getHeight());
        this.setWeight(user.getWeight());
        double bmi = this.getWeight() / (this.getHeight() / 100 * this.getHeight() / 100);
        bmi = Double.parseDouble(String.format("%.2f", bmi));
        this.setBMI(bmi);
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender.toString();
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String user) {
        this.username = user;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }
}

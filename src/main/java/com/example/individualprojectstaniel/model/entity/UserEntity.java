package com.example.individualprojectstaniel.model.entity;

import com.example.individualprojectstaniel.model.enums.UserGenderEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    private String username;
    private String email;
    private String password;
    private UserGenderEnum gender;

    private Integer height;
    private Integer weight;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
    }

    public void setRoles(UserRoleEntity role) {
        this.roles = List.of(role);
    }

    private List<UserRoleEntity> roles = new ArrayList<>();

    public UserEntity() {

    }


    @Column(name = "username", unique = true)
   @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Column(name = "email", unique = true)
    @Email
    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    public UserGenderEnum getGender() {
        return gender;
    }

    public void setGender(UserGenderEnum gender) {
        this.gender = gender;
    }

    @Column(name = "height")
    @NotNull
    @Min(value = 50, message = "Height must be at least 50 cm")
    @Max(value = 300, message = "Height cannot exceed 300 cm")
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Column(name = "weight")
    @NotNull
    @Min(value = 10, message = "Weight must be at least 10 kg")
    @Max(value = 300, message = "Weight cannot exceed 300 kg")
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public boolean hasRole(String role) {
        List<String> roles = this.roles.stream().map(UserRoleEntity::getName).toList();

        return roles.contains(role);
    }

    @Transient
    public boolean isTrainer() {
        return hasRole(UserRoleEntity.TRAINER_ROLE.getName());
    }
}

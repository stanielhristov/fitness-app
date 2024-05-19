package com.example.individualprojectstaniel.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
@Data
public class UserRoleEntity extends BaseEntity {
    public static String USER = "USER";
    public static String ADMIN = "ADMIN";

    public static UserRoleEntity TRAINER_ROLE = new UserRoleEntity("TRAINER");

    @Column(name = "name")
    private String name;

    public UserRoleEntity() {
    }

    public UserRoleEntity(String name) {
        this.name = name;
    }
}

package com.example.individualprojectstaniel.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class TrainerClientEntity extends BaseEntity {
    @Column(nullable = false, name = "trainer_id")
    private Long trainerId;

    @Column(nullable = false, name = "client_id")
    private Long clientId;

    public TrainerClientEntity(long trainerId, long clientId) {
        this.trainerId = trainerId;
        this.clientId = clientId;
    }
}

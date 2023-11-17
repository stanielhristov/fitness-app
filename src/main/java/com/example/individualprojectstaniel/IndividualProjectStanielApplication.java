package com.example.individualprojectstaniel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class IndividualProjectStanielApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndividualProjectStanielApplication.class, args);
    }

}

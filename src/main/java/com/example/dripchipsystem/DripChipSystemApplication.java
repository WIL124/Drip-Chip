package com.example.dripchipsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EntityScan("com.example.dripchipsystem.*.model")
@EnableJpaRepositories("com.example.dripchipsystem.*.repository")
@EnableWebSecurity
public class DripChipSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(DripChipSystemApplication.class, args);
    }
}

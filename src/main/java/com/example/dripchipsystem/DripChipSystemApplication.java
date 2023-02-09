package com.example.dripchipsystem;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.dripchipsystem.model")
@EnableJpaRepositories("com.example.dripchipsystem.repo")
public class DripChipSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DripChipSystemApplication.class, args);
    }
}

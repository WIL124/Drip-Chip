package com.example.dripchipsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan("com.example.dripchipsystem.model")
@EnableJpaRepositories("com.example.dripchipsystem.repo")
public class DripChipSystemApplication
//        implements ApplicationListener<ApplicationReadyEvent>
{
//
//    public DripChipSystemApplication(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }

    public static void main(String[] args) {
        SpringApplication.run(DripChipSystemApplication.class, args);

    }
//    private final ObjectMapper objectMapper;
//    @Override
//    public void onApplicationEvent(ApplicationReadyEvent event) {
//        objectMapper.setDateFormat();
//        objectMapper.setTimeZone(TimeZone.getTimeZone("UTS"))
//    }
}

package com.example.dripchipsystem.endpoint.impl;

import com.example.dripchipsystem.dto.AnimalDto;
import com.example.dripchipsystem.endpoint.AnimalEndpoint;
import com.example.dripchipsystem.model.Animal;
import com.example.dripchipsystem.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class AnimalEndpointImpl implements AnimalEndpoint {
    private AnimalService animalService;
    @Override
    public Animal getAnimal(Long id) {
        return animalService.getAnimal(id);
    }

    @Override
    public List<Animal> search(LocalDateTime startDateTime, LocalDateTime endDateTime, Integer chipperId, Long chippingLocationId, String lifeStatus, String gender, Integer from, Integer size) {
        return null;
    }

    @Override
    public Animal create(AnimalDto dto) {
        return null;
    }

    @Override
    public Animal update(AnimalDto dto) {
        return null;
    }

    @Override
    public void delete() {

    }
}

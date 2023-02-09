package com.example.dripchipsystem.endpoint;

import com.example.dripchipsystem.dto.AnimalDto;
import com.example.dripchipsystem.model.Animal;

import java.time.LocalDateTime;
import java.util.List;

public interface AnimalEndpoint {
    Animal getAnimal(Long id);

    List<Animal> search(LocalDateTime startDateTime,
                        LocalDateTime endDateTime,
                        Integer chipperId,
                        Long chippingLocationId,
                        String lifeStatus,
                        String gender,
                        Integer from,
                        Integer size);

    Animal create(AnimalDto dto);

    Animal update(AnimalDto dto);

    void delete();
}

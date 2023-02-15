package com.example.dripchipsystem.service.impl;

import com.example.dripchipsystem.model.Animal;
import com.example.dripchipsystem.model.Gender;
import com.example.dripchipsystem.repo.AnimalRepository;
import com.example.dripchipsystem.service.AbstractService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnimalService extends AbstractService<Animal, AnimalRepository> {
    public AnimalService(AnimalRepository repository) {
        super(repository);
    }

    public List<Animal> search(LocalDateTime startDateTime, LocalDateTime endDateTime,
                               Integer chipperId, Long chippingLocationId,
                               String lifeStatus, String gender, int from, int size) {
        return repository.search(startDateTime, endDateTime, chipperId, chippingLocationId, lifeStatus, gender, from, size);
    }
}

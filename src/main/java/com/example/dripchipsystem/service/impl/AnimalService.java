package com.example.dripchipsystem.service.impl;

import com.example.dripchipsystem.dto.UpdateAnimalTypeDto;
import com.example.dripchipsystem.dto.impl.AnimalDto;
import com.example.dripchipsystem.mapper.impl.AnimalMapper;
import com.example.dripchipsystem.model.AbstractEntity;
import com.example.dripchipsystem.model.Animal;
import com.example.dripchipsystem.model.AnimalType;
import com.example.dripchipsystem.model.AnimalVisitedLocation;
import com.example.dripchipsystem.repo.AnimalRepository;
import com.example.dripchipsystem.repo.AnimalTypeRepository;
import com.example.dripchipsystem.service.AbstractService;
import com.example.dripchipsystem.service.CommonService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService extends AbstractService<Animal, AnimalRepository, AnimalMapper, AnimalDto>
        implements CommonService<AnimalDto> {
    private final AnimalTypeRepository animalTypeRepository;

    public AnimalService(AnimalRepository repository, AnimalMapper animalMapper, AnimalTypeRepository animalTypeRepository) {
        super(repository, animalMapper);
        this.animalTypeRepository = animalTypeRepository;
    }

    public List<AnimalDto> search(LocalDateTime startDateTime, LocalDateTime endDateTime,
                                  Integer chipperId, Long chippingLocationId,
                                  String lifeStatus, String gender, int from, int size) {
        return repository.search(startDateTime, endDateTime, chipperId, chippingLocationId, lifeStatus, gender, from, size)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public AnimalDto addTypeToAnimal(Long animalId, Long typeId) {
        Animal animal = getEntityOrThrow(animalId);
        AnimalType animalType = animalTypeRepository.findById(typeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        animal.getAnimalTypes().add(animalType);
        try {
            return mapper.toDto(repository.save(animal));
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public AnimalDto deleteTypeFromAnimal(Long animalId, Long typeId) {
        Animal animal = getEntityOrThrow(animalId);
        AnimalType animalType = animalTypeRepository.findById(typeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        animal.getAnimalTypes().remove(animalType);
        return mapper.toDto(animal);
    }

    public AnimalDto updateAnimalType(Long animalId, UpdateAnimalTypeDto dto) {
        Animal animal = getEntityOrThrow(animalId);
        AnimalType oldType = animal.getAnimalTypes().stream()
                .filter(type -> type.getId().equals(dto.getOldTypeId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        animal.getAnimalTypes().remove(oldType);

        AnimalType newAnimalType = animalTypeRepository.findById(dto.getNewTypeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (animal.getAnimalTypes().contains(newAnimalType)) throw new ResponseStatusException(HttpStatus.CONFLICT);
        animal.getAnimalTypes().add(newAnimalType);
        return mapper.toDto(animal);
    }
}

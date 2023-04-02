package com.example.dripchipsystem.animal.service;

import com.example.dripchipsystem.animal.dto.AnimalUpdateRequest;
import com.example.dripchipsystem.animalType.dto.UpdateAnimalTypeRequest;
import com.example.dripchipsystem.animal.dto.AnimalDto;
import com.example.dripchipsystem.animal.mapper.AnimalMapper;
import com.example.dripchipsystem.animal.model.Animal;
import com.example.dripchipsystem.animalType.model.AnimalType;
import com.example.dripchipsystem.animal.model.Gender;
import com.example.dripchipsystem.animal.model.LifeStatus;
import com.example.dripchipsystem.animal.repository.AnimalRepository;
import com.example.dripchipsystem.animalType.repository.AnimalTypeRepository;
import com.example.dripchipsystem.common.service.AbstractService;
import com.example.dripchipsystem.common.service.CommonService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
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

    @Override
    public void delete(Long id) {
        Animal entity = getEntityOrThrow(id, repository);
        if (!entity.getVisitedLocations().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        try {
            repository.delete(entity);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public List<AnimalDto> search(OffsetDateTime startDateTime, OffsetDateTime endDateTime,
                                  Integer chipperId, Long chippingLocationId,
                                  LifeStatus lifeStatus, Gender gender, int from, int size) {
        return repository.search(startDateTime, endDateTime, chipperId, chippingLocationId, lifeStatus, gender, from, size)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public AnimalDto addTypeToAnimal(Long animalId, Long typeId) {
        Animal animal = getEntityOrThrow(animalId, repository);
        AnimalType animalType = getEntityOrThrow(typeId, animalTypeRepository);
        animal.getAnimalTypes().add(animalType);
        try {
            return mapper.toDto(repository.save(animal));
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public AnimalDto deleteTypeFromAnimal(Long animalId, Long typeId) {
        Animal animal = getEntityOrThrow(animalId, repository);
        if (animal.getAnimalTypes().size() == 1) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        AnimalType animalType = getEntityOrThrow(typeId, animalTypeRepository);
        if (!animal.getAnimalTypes().remove(animalType)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return mapper.toDto(repository.save(animal));
    }

    public AnimalDto updateAnimalType(Long animalId, UpdateAnimalTypeRequest dto) {
        Animal animal = getEntityOrThrow(animalId, repository);
        AnimalType oldType = getEntityOrThrow(dto.getOldTypeId(), animalTypeRepository);
        AnimalType newAnimalType = getEntityOrThrow(dto.getNewTypeId(), animalTypeRepository);

        if (!animal.getAnimalTypes().contains(oldType)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if (animal.getAnimalTypes().contains(newAnimalType)) throw new ResponseStatusException(HttpStatus.CONFLICT);

        animal.getAnimalTypes().remove(oldType);
        animal.getAnimalTypes().add(newAnimalType);
        return mapper.toDto(animal);
    }

    public AnimalDto updateEntity(Long id, AnimalUpdateRequest dto) {
        Animal entity = getEntityOrThrow(id, repository);
        if (!entity.getVisitedLocations().isEmpty()
                && entity.getVisitedLocations().get(0).getLocationPoint().getId().equals(dto.getChippingLocationId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        mapper.updateEntityFromDto(entity, dto);
        try {
            return mapper.toDto(repository.save(entity));
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }
}

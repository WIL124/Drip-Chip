package com.example.dripchipsystem.mapper.impl;

import com.example.dripchipsystem.dto.AnimalUpdateRequest;
import com.example.dripchipsystem.dto.impl.AnimalDto;
import com.example.dripchipsystem.mapper.AbstractMapper;
import com.example.dripchipsystem.model.AbstractEntity;
import com.example.dripchipsystem.model.Animal;
import com.example.dripchipsystem.model.LifeStatus;
import com.example.dripchipsystem.repo.AccountRepository;
import com.example.dripchipsystem.repo.AnimalTypeRepository;
import com.example.dripchipsystem.repo.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AnimalMapper extends AbstractMapper<Animal, AnimalDto> {
    private AnimalTypeRepository animalTypeRepository;
    private AccountRepository accountRepository;
    private LocationRepository locationRepository;

    @Override
    public Animal entityFromDto(AnimalDto dto) {
        return Animal.builder()
                .weight(dto.getWeight())
                .length(dto.getLength())
                .height(dto.getHeight())
                .gender(dto.getGender())
                .lifeStatus(LifeStatus.ALIVE)
                .chipper(
                        accountRepository.findById(dto.getChipperId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .animalTypes(dto.getAnimalTypes()
                        .stream()
                        .map(id -> animalTypeRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
                        .collect(Collectors.toList()))
                .chippingLocation(locationRepository.findById(dto.getChippingLocationId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .build();
    }

    @Override
    public AnimalDto toDto(Animal entity) {
        List<Long> list = entity.getVisitedLocations() == null ?
                new ArrayList<>() :
                entity.getVisitedLocations().stream().mapToLong(AbstractEntity::getId).boxed().collect(Collectors.toList());
        return AnimalDto.builder()
                .id(entity.getId())
                .chipperId(entity.getChipper().getId())
                .chippingDateTime(entity.getChippingDateTime())
                .lifeStatus(entity.getLifeStatus())
                .gender(entity.getGender())
                .length(entity.getLength())
                .height(entity.getHeight())
                .weight(entity.getWeight())
                .animalTypes(entity.getAnimalTypes().
                        stream()
                        .mapToLong(AbstractEntity::getId)
                        .boxed()
                        .collect(Collectors.toList()))
                .chippingLocationId(entity.getChippingLocation().getId())
                .deathDateTime(entity.getDeathDateTime())
                .visitedLocations(list)
                .build();
    }

    @Override
    @Deprecated
    public void updateEntityFromDto(Animal entity, AnimalDto dto) {
    }

    public void updateEntityFromDto(Animal entity, AnimalUpdateRequest dto) {
        entity.setWeight(dto.getWeight());
        entity.setHeight(dto.getHeight());
        entity.setLength(dto.getLength());
        entity.setGender(dto.getGender());
        entity.setChipper(accountRepository.findById(dto.getChipperId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        entity.setChippingLocation(locationRepository.findById(dto.getChippingLocationId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        if (dto.getLifeStatus() == LifeStatus.DEAD) {
            entity.setDeathDateTime(OffsetDateTime.now());
        }
        entity.setLifeStatus(dto.getLifeStatus());
    }
}

package com.example.dripchipsystem.mapper.impl;

import com.example.dripchipsystem.dto.impl.AnimalDto;
import com.example.dripchipsystem.mapper.AbstractMapper;
import com.example.dripchipsystem.model.Animal;
import com.example.dripchipsystem.repo.AccountRepository;
import com.example.dripchipsystem.repo.AnimalTypeRepository;
import com.example.dripchipsystem.repo.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AnimalMapper extends AbstractMapper<Animal, AnimalDto> {
    private AnimalTypeRepository animalTypeRepository;
    private AccountRepository accountRepository;
    private LocationRepository locationRepository;
    @Override
    public Animal createEntityFromDto(AnimalDto dto) {
        return Animal.builder()
                .weight(dto.getWeight())
                .length(dto.getLength())
                .height(dto.getHeight())
                .gender(dto.getGender())
                .chipper(
                        accountRepository.findById(
                                dto.getChipperId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .animalTypes(
                        dto.getAnimalTypes().stream()
                                .map(id -> animalTypeRepository.findById(id)
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
                                .collect(Collectors.toList()))
                .chippingLocation(
                        locationRepository.findById(dto.getChippingLocationId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .build();
    }

    @Override
    public AnimalDto toDto(Animal dto) {
        return null;
    }

    @Override
    public void updateEntityFromDto(Animal entity, AnimalDto dto) {
        entity.setWeight(dto.getWeight());
        entity.setHeight(dto.getHeight());
        entity.setLength(dto.getLength());
        entity.setGender(dto.getGender());
        entity.setChipper(accountRepository.findById(dto.getChipperId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        entity.setChippingLocation(locationRepository.findById(dto.getChippingLocationId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        entity.setLifeStatus(dto.getLifeStatus());
    }

}

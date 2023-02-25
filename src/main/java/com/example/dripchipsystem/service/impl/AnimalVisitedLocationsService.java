package com.example.dripchipsystem.service.impl;

import com.example.dripchipsystem.dto.impl.AnimalVisitedLocationDto;
import com.example.dripchipsystem.mapper.impl.AnimalVisitedLocationMapper;
import com.example.dripchipsystem.model.Animal;
import com.example.dripchipsystem.model.AnimalVisitedLocation;
import com.example.dripchipsystem.model.LocationPoint;
import com.example.dripchipsystem.repo.AnimalRepository;
import com.example.dripchipsystem.repo.AnimalVisitedLocationRepository;
import com.example.dripchipsystem.repo.LocationRepository;
import com.example.dripchipsystem.service.AbstractService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalVisitedLocationsService
        extends AbstractService<AnimalVisitedLocation, AnimalVisitedLocationRepository, AnimalVisitedLocationMapper, AnimalVisitedLocationDto> {
    private final AnimalRepository animalRepository;
    private final LocationRepository locationRepository;

    public AnimalVisitedLocationsService(AnimalVisitedLocationRepository repository, AnimalVisitedLocationMapper mapper,
                                         AnimalRepository animalRepository, LocationRepository locationRepository) {
        super(repository, mapper);
        this.animalRepository = animalRepository;
        this.locationRepository = locationRepository;
    }

    public List<AnimalVisitedLocationDto> searchVisitedLocations(Long animalId, LocalDateTime startDateTime,
                                                                 LocalDateTime endDateTime, int from, int size) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return animal.getVisitedLocations().stream()
                .filter(location -> startDateTime == null || location.getDateTimeOfVisitLocationPoint().isAfter(startDateTime))
                .filter(location -> endDateTime == null || location.getDateTimeOfVisitLocationPoint().isBefore(endDateTime))
                .sorted()
                .skip(from)
                .limit(size)
                .map(location -> mapper.toDto(location))
                .collect(Collectors.toList());
//        return repository.search(animal, startDateTime, endDateTime, from, size)
//                .stream()
//                .map(mapper::toDto)
//                .collect(Collectors.toList());
    }

    public AnimalVisitedLocationDto create(Long animalId, Long pointId) {
        LocationPoint locationPoint = locationRepository.findById(pointId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mapper.toDto(
                repository.save(
                        new AnimalVisitedLocation(null, locationPoint, animal)));
    }
}

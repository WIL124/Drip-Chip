package com.example.dripchipsystem.service.impl;

import com.example.dripchipsystem.dto.impl.AnimalVisitedLocationDto;
import com.example.dripchipsystem.mapper.impl.AnimalVisitedLocationMapper;
import com.example.dripchipsystem.model.AbstractEntity;
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

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalVisitedLocationsService extends AbstractService<AnimalVisitedLocation, AnimalVisitedLocationRepository, AnimalVisitedLocationMapper, AnimalVisitedLocationDto> {
    private final AnimalRepository animalRepository;
    private final LocationRepository locationRepository;

    public AnimalVisitedLocationsService(AnimalVisitedLocationRepository repository, AnimalVisitedLocationMapper mapper, AnimalRepository animalRepository, LocationRepository locationRepository) {
        super(repository, mapper);
        this.animalRepository = animalRepository;
        this.locationRepository = locationRepository;
    }

    public List<AnimalVisitedLocationDto> searchVisitedLocations(Long animalId, OffsetDateTime startDateTime, OffsetDateTime endDateTime, int from, int size) {
        Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return animal.getVisitedLocations().stream()
                .filter(location -> startDateTime == null || location.getDateTimeOfVisitLocationPoint().isAfter(startDateTime))
                .filter(location -> endDateTime == null || location.getDateTimeOfVisitLocationPoint().isBefore(endDateTime))
                .sorted(Comparator.comparingLong(AbstractEntity::getId))
                .skip(from)
                .limit(size)
                .map(location -> mapper.toDto(location))
                .collect(Collectors.toList());
    }

    public AnimalVisitedLocationDto create(Long animalId, Long pointId) {
        LocationPoint locationPoint = locationRepository.findById(pointId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<AnimalVisitedLocation> visits = animal.getVisitedLocations();
        if (visits.size() != 0) {
            AnimalVisitedLocation lastVisit = visits.get(visits.size() - 1);
            if (lastVisit.getLocationPoint().getId().equals(locationPoint.getId()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return mapper.toDto(repository.save(new AnimalVisitedLocation(null, locationPoint, animal)));
    }

    public void delete(Long animalId, Long visitedPointId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        AnimalVisitedLocation location = animal.getVisitedLocations().stream()
                .filter(visitedLocation -> visitedLocation.getId().equals(visitedPointId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        animal.getVisitedLocations().remove(location);
        animalRepository.save(animal);
    }
}

package com.example.dripchipsystem.service.impl;

import com.example.dripchipsystem.dto.UpdateVisitedLocationRequest;
import com.example.dripchipsystem.dto.impl.AnimalVisitedLocationDto;
import com.example.dripchipsystem.mapper.impl.AnimalVisitedLocationMapper;
import com.example.dripchipsystem.model.*;
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
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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
        if (animal.getLifeStatus().equals(LifeStatus.DEAD)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<AnimalVisitedLocation> visits = animal.getVisitedLocations();
        if (animal.getVisitedLocations().isEmpty() && pointId.equals(animal.getChippingLocation().getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
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
        AnimalVisitedLocation animalVisitedLocation = animal.getVisitedLocations().stream()
                .filter(loc -> loc.getId().equals(visitedPointId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<AnimalVisitedLocation> visits = animal.getVisitedLocations();
        boolean isFirst = visits.indexOf(animalVisitedLocation) == 0;
        boolean secondIsEqualChippingLocation = visits.size() > 1 && visits.get(1).getAnimal().getId().equals(animalId);
        if (isFirst && secondIsEqualChippingLocation) {
            repository.delete(visits.get(1));
        }
        repository.delete(animalVisitedLocation);
    }

    public AnimalVisitedLocationDto update(Long animalId, UpdateVisitedLocationRequest request) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        AnimalVisitedLocation animalVisitedLocation = animal.getVisitedLocations().stream()
                .filter(visitedLocation -> visitedLocation.getId().equals(request.getVisitedLocationPointId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        LocationPoint locationPoint = locationRepository.findById(request.getLocationPointId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<AnimalVisitedLocation> visits = animal.getVisitedLocations();

        int currentIndex = visits.indexOf(animalVisitedLocation);
        AnimalVisitedLocation previousVisitedLocation = currentIndex > 0 ? visits.get(currentIndex - 1) : null;
        AnimalVisitedLocation nextVisitedLocation = currentIndex + 1 != visits.size() ? visits.get(currentIndex + 1) : null;
        if (previousVisitedLocation != null && previousVisitedLocation.getLocationPoint().getId().equals(request.getLocationPointId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (nextVisitedLocation != null && nextVisitedLocation.getLocationPoint().getId().equals(request.getLocationPointId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (currentIndex == 0 && request.getLocationPointId().equals(animal.getChippingLocation().getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        if (animalVisitedLocation.getLocationPoint().getId().equals(request.getLocationPointId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        if (location.getLocationPoint().getId().equals(animal.getChippingLocation().getId()))
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        animalVisitedLocation.setLocationPoint(locationPoint);
        return mapper.toDto(repository.save(animalVisitedLocation));
    }
}

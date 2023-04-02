package com.example.dripchipsystem.service.impl;

import com.example.dripchipsystem.dto.UpdateVisitedLocationRequest;
import com.example.dripchipsystem.dto.childs.AnimalVisitedLocationDto;
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
        Animal animal = getEntityOrThrow(animalId, animalRepository);
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
        LocationPoint locationPoint = getEntityOrThrow(pointId, locationRepository);
        Animal animal = getEntityOrThrow(animalId, animalRepository);
        if (animal.isDead()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //Животное находится в точке чипирования и никуда не перемещалось,
        //попытка добавить точку локации, равную точке чипирования
        if (!animal.hasVisits() && pointId.equals(animal.getChippingLocation().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //Попытка добавить точку локации, в которой уже находится животное
        AnimalVisitedLocation lastVisit = animal.getLastVisit();
        if (lastVisit != null && lastVisit.getLocationPoint().equals(locationPoint)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return mapper.toDto(repository.save(new AnimalVisitedLocation(locationPoint, animal)));
    }

    public void delete(Long animalId, Long visitedPointId) {
        Animal animal = getEntityOrThrow(animalId, animalRepository);
        AnimalVisitedLocation animalVisitedLocation = getEntityOrThrow(visitedPointId, repository);
        if (!animal.getVisitedLocations().contains(animalVisitedLocation))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        List<AnimalVisitedLocation> visits = animal.getVisitedLocations();

        boolean isFirst = visits.indexOf(animalVisitedLocation) == 0;
        boolean secondIsEqualChippingLocation = visits.size() > 1 && visits.get(1).getAnimal().equals(animal);
        if (isFirst && secondIsEqualChippingLocation) {
            repository.delete(visits.get(1));
        }
        repository.delete(animalVisitedLocation);
    }

    public AnimalVisitedLocationDto update(Long animalId, UpdateVisitedLocationRequest request) {
        Animal animal = getEntityOrThrow(animalId, animalRepository);
        AnimalVisitedLocation animalVisitedLocation = getEntityOrThrow(request.getVisitedLocationPointId(), repository);
        LocationPoint locationPoint = getEntityOrThrow(request.getLocationPointId(), locationRepository);

        if (!animal.getVisitedLocations().contains(animalVisitedLocation))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        List<AnimalVisitedLocation> visits = animal.getVisitedLocations();
        int indexOfVisit = visits.indexOf(animalVisitedLocation);
        AnimalVisitedLocation previousVisitedLocation = indexOfVisit > 0 ? visits.get(indexOfVisit - 1) : null;
        AnimalVisitedLocation nextVisitedLocation = indexOfVisit + 1 != visits.size() ? visits.get(indexOfVisit + 1) : null;

        if (previousVisitedLocation != null && previousVisitedLocation.getLocationPoint().getId().equals(request.getLocationPointId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (nextVisitedLocation != null && nextVisitedLocation.getLocationPoint().getId().equals(request.getLocationPointId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (indexOfVisit == 0 && request.getLocationPointId().equals(animal.getChippingLocation().getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        if (animalVisitedLocation.getLocationPoint().getId().equals(request.getLocationPointId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        animalVisitedLocation.setLocationPoint(locationPoint);
        return mapper.toDto(repository.save(animalVisitedLocation));
    }
}

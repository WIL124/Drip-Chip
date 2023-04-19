package com.example.dripchipsystem.area.service;

import com.example.dripchipsystem.animal.model.Animal;
import com.example.dripchipsystem.animalType.model.AnimalType;
import com.example.dripchipsystem.animalVisitedLocation.model.AnimalVisitedLocation;
import com.example.dripchipsystem.area.dto.AnimalsAnalytics;
import com.example.dripchipsystem.area.dto.AreaAnalytics;
import com.example.dripchipsystem.area.dto.AreaDto;
import com.example.dripchipsystem.area.mapper.AreaMapper;
import com.example.dripchipsystem.area.model.Area;
import com.example.dripchipsystem.area.repository.AreaRepository;
import com.example.dripchipsystem.common.service.AbstractService;
import com.example.dripchipsystem.locationPoint.model.LocationPoint;
import com.example.dripchipsystem.locationPoint.repository.LocationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class AreaService extends AbstractService<Area, AreaRepository, AreaMapper, AreaDto> {
    private final AreaAnalyser areaAnalyser;
    private final LocationRepository locationRepository;

    public AreaService(AreaRepository repository, AreaMapper mapper, AreaAnalyser areaAnalyser, LocationRepository locationRepository) {
        super(repository, mapper);
        this.areaAnalyser = areaAnalyser;
        this.locationRepository = locationRepository;
    }

    @Override
    public AreaDto create(AreaDto dto) {
        if (!areaAnalyser.isValidAreaPoints(dto.getAreaPoints())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<Area> areas = repository.findAll();
        if (areaAnalyser.hasIntersections(dto.getAreaPoints(), areas)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return super.create(dto);
    }

    @Transactional
    public AreaAnalytics getAreaAnalytics(Long areaId, LocalDate startDate, LocalDate endDate) {
        checkDatesOrThrow(startDate, endDate);
        Area area = getEntityOrThrow(areaId, repository);
        Set<LocationPoint> locationsInArea = getAllPointsInArea(area);

        if (locationsInArea.size() == 0) {
            return AreaAnalytics.EMPTY;
        }
        //Из всех точек получить всех животных, чипированных в зоне
        Set<Animal> chippedAnimals = getAnimalsChippedInPointsBetweenDates(locationsInArea, startDate, endDate);

        // и перемещавшихся в зоне
        Set<Animal> animalsArrivedOrGone = getAnimalsWhoVisitsPointsBetweenDates(locationsInArea, startDate, endDate);

        //Объединение множеств
        Set<Animal> intersection = new HashSet<>();
        intersection.addAll(chippedAnimals);
        intersection.addAll(animalsArrivedOrGone);

        Map<AnimalType, AnimalsAnalytics> map = new HashMap<>();
        long totalQuantity = 0L;
        long totalGone = 0L;
        long totalArrived = 0L;
        for (Animal animal : intersection) {
            boolean arrive = isArrived(animal, locationsInArea, startDate, endDate);
            boolean gone = isGone(animal, locationsInArea, startDate, endDate);
            LocationPoint lastPoint = animal.getLastVisit() == null ? animal.getChippingLocation() : animal.getLastVisit().getLocationPoint();
            boolean quantity = locationsInArea.contains(lastPoint);
            for (AnimalType type : animal.getAnimalTypes()) {
                map.merge(type, new AnimalsAnalytics(type.getType(), type.getId(), quantity ? 1L : 0L, arrive ? 1L : 0L, gone ? 1L : 0L),
                        AnimalsAnalytics::sum);
            }
            totalQuantity += quantity ? 1L : 0L;
            totalArrived += arrive ? 1L : 0L;
            totalGone += gone ? 1L : 0L;
        }
        List<AnimalsAnalytics> animalsAnalytics = new ArrayList<>(map.values());
        return new AreaAnalytics(totalQuantity, totalArrived, totalGone, animalsAnalytics);
    }

    private Set<LocationPoint> getAllPointsInArea(Area area) {
        return areaAnalyser.pointsInArea(area, locationRepository.findAll());
    }

    private boolean isArrived(Animal animal, Set<LocationPoint> locationPointsInArea, LocalDate startDate, LocalDate endDate) {
        List<AnimalVisitedLocation> sortedByVisitsList = animal.getVisitedLocations().stream()
                .filter(startFilter(startDate))
                .filter(endFilter(endDate))
                .sorted(Comparator.comparingLong(AnimalVisitedLocation::getId))
                .toList();

        LocationPoint previous = animal.getChippingLocation();
        for (AnimalVisitedLocation animalVisitedLocation : sortedByVisitsList) {
            LocationPoint next = animalVisitedLocation.getLocationPoint();
            if (!locationPointsInArea.contains(previous) && locationPointsInArea.contains(next)) {
                return true;
            }
            previous = next;
        }
        return false;
    }

    private boolean isGone(Animal animal, Set<LocationPoint> locationPointsInArea, LocalDate startDate, LocalDate endDate) {
        List<AnimalVisitedLocation> sortedByVisitsList = animal.getVisitedLocations().stream()
                .filter(startFilter(startDate))
                .filter(endFilter(endDate))
                .sorted(Comparator.comparingLong(AnimalVisitedLocation::getId))
                .toList();
        LocationPoint previous = animal.getChippingLocation();
        for (AnimalVisitedLocation animalVisitedLocation : sortedByVisitsList) {
            LocationPoint next = animalVisitedLocation.getLocationPoint();
            if (locationPointsInArea.contains(previous) && !locationPointsInArea.contains(next)) {
                return true;
            }
            previous = next;
        }
        return false;
    }

    private void checkDatesOrThrow(LocalDate startDate, LocalDate endDate) {
        if (startDate != null && endDate != null) {
            if (endDate.isBefore(startDate)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    private Set<Animal> getAnimalsChippedInPointsBetweenDates(Set<LocationPoint> locationsInArea, LocalDate startDate, LocalDate endDate) {
        return locationsInArea.stream()
                .flatMap(location -> location.getChippedAnimals().stream())
                .filter(animal -> animal.getChippingDateTime().toLocalDateTime().isBefore(endDate.atStartOfDay()))
                .filter(animal -> animal.getChippingDateTime().toLocalDateTime().isAfter(startDate.atTime(LocalTime.MAX)))
                .collect(Collectors.toSet());
    }

    private Set<Animal> getAnimalsWhoVisitsPointsBetweenDates(Set<LocationPoint> locationsInArea, LocalDate startDate, LocalDate endDate) {
        return locationsInArea.stream()
                .flatMap(location -> location.getVisitedLocations().stream())
                .filter(endFilter(endDate))
                .filter(startFilter(startDate))
                .map(AnimalVisitedLocation::getAnimal)
                .collect(Collectors.toSet());
    }

    private Predicate<AnimalVisitedLocation> startFilter(LocalDate startDate) {
        return visitedLocation -> visitedLocation.getDateTimeOfVisitLocationPoint().toLocalDateTime().isAfter(startDate.atTime(LocalTime.MAX));
    }

    private Predicate<AnimalVisitedLocation> endFilter(LocalDate endDate) {
        return visitedLocation -> visitedLocation.getDateTimeOfVisitLocationPoint().toLocalDateTime().isBefore(endDate.atStartOfDay());
    }
}

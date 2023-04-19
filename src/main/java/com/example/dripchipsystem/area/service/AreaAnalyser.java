package com.example.dripchipsystem.area.service;

import com.example.dripchipsystem.animal.model.Animal;
import com.example.dripchipsystem.animalType.model.AnimalType;
import com.example.dripchipsystem.visit.model.AnimalVisitedLocation;
import com.example.dripchipsystem.area.dto.AnimalsAnalytics;
import com.example.dripchipsystem.area.dto.AreaAnalytics;
import com.example.dripchipsystem.area.model.Area;
import com.example.dripchipsystem.location.model.LocationPoint;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AreaAnalyser {
    private final Area area;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final AreaValidator areaValidator;
    private final List<LocationPoint> allPoints;

    @Transactional
    public AreaAnalytics analyse() {
        Set<LocationPoint> locationsInArea = getAllPointsInArea(area);
        if (locationsInArea.size() == 0) {
            return AreaAnalytics.EMPTY;
        }
        Set<Animal> chippedAnimals = getAnimalsChippedInPointsBetweenDates(locationsInArea, startDate, endDate);
        Set<Animal> animalsArrivedOrGone = getAnimalsWhoVisitsPointsBetweenDates(locationsInArea, startDate, endDate);
        Set<Animal> intersection = new HashSet<>();
        intersection.addAll(chippedAnimals);
        intersection.addAll(animalsArrivedOrGone);

        Map<AnimalType, AnimalsAnalytics> map = new HashMap<>();
        long totalQuantity = 0L;
        long totalGone = 0L;
        long totalArrived = 0L;
        for (Animal animal : intersection) {
            List<LocationPoint> sortedByVisitsList = getSortedByVisitTimeLocationPoints(animal);
            boolean arrive = isArrived(animal, locationsInArea, sortedByVisitsList);
            boolean gone = isGone(animal, locationsInArea, sortedByVisitsList);
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
        return areaValidator.pointsInArea(area, allPoints);
    }


    private boolean isArrived(Animal animal, Set<LocationPoint> locationPointsInArea, List<LocationPoint> sortedByVisitsList) {
        LocationPoint previous = animal.getChippingLocation();
        for (LocationPoint locationPoint : sortedByVisitsList) {
            if (!locationPointsInArea.contains(previous) && locationPointsInArea.contains(locationPoint)) {
                return true;
            }
            previous = locationPoint;
        }
        return false;
    }

    private boolean isGone(Animal animal, Set<LocationPoint> locationPointsInArea, List<LocationPoint> sortedByVisitsList) {
        LocationPoint previous = animal.getChippingLocation();
        for (LocationPoint locationPoint : sortedByVisitsList) {
            if (locationPointsInArea.contains(previous) && !locationPointsInArea.contains(locationPoint)) {
                return true;
            }
            previous = locationPoint;
        }
        return false;
    }

    private List<LocationPoint> getSortedByVisitTimeLocationPoints(Animal animal) {
        return animal.getVisitedLocations().stream()
                .filter(startFilter(startDate))
                .filter(endFilter(endDate))
                .sorted(Comparator.comparingLong(AnimalVisitedLocation::getId))
                .map(AnimalVisitedLocation::getLocationPoint)
                .toList();
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

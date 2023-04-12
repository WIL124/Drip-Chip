package com.example.dripchipsystem.area.service;

import com.example.dripchipsystem.animal.model.Animal;
import com.example.dripchipsystem.animalType.model.AnimalType;
import com.example.dripchipsystem.animalVisitedLocation.model.AnimalVisitedLocation;
import com.example.dripchipsystem.area.dto.AreaAnalytics;
import com.example.dripchipsystem.area.dto.AreaDto;
import com.example.dripchipsystem.area.mapper.AreaMapper;
import com.example.dripchipsystem.area.model.Area;
import com.example.dripchipsystem.area.repository.AreaRepository;
import com.example.dripchipsystem.common.service.AbstractService;
import com.example.dripchipsystem.locationPoint.model.LocationPoint;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class AreaService extends AbstractService<Area, AreaRepository, AreaMapper, AreaDto> {
    private final AreaValidator areaValidator;

    public AreaService(AreaRepository repository, AreaMapper mapper, AreaValidator areaValidator) {
        super(repository, mapper);
        this.areaValidator = areaValidator;
    }

    @Override
    public AreaDto create(AreaDto dto) {
        if (!areaValidator.isValidAreaPoints(dto.getAreaPoints())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<Area> areas = repository.findAll();
        if (areaValidator.hasIntersections(dto.getAreaPoints(), areas)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return super.create(dto);
    }

    public AreaAnalytics getAreaAnalytics(Long areaId, LocalDate startDate, LocalDate endDate) {
        checkDatesOrThrow(startDate, endDate);
        Area area = getEntityOrThrow(areaId, repository);
        //TODO Получить все LocationPoint относящиеся к зоне
        List<LocationPoint> locationsInArea = getAllPointsInArea(area);
        //Из всех точек получить всех животных, чипированных
        Set<Animal> chippedAnimals = locationsInArea.stream()
                .flatMap(location -> location.getChippedAnimals().stream())
                .collect(Collectors.toSet());
        // и переместившихся в ней
        Set<Animal> animalsArrivedOrGone = locationsInArea.stream()
                .flatMap(location -> location.getVisitedLocations().stream())
                .map(AnimalVisitedLocation::getAnimal)
                .collect(Collectors.toSet());
        //Объединение множеств
        Set<Animal> intersection = new HashSet<>();
        intersection.addAll(chippedAnimals);
        intersection.addAll(animalsArrivedOrGone);
        //Мб использовать хешмапу тип -> животное
        Map<AnimalType, Animal> map = new HashMap<>();
        for (Animal animal: intersection) {
        }
        return new AreaAnalytics(2L,1L,1L, new ArrayList<>());
    }

    private List<LocationPoint> getAllPointsInArea(Area area) {
        //TODO
        return null;
    }

    private void checkDatesOrThrow(LocalDate startDate, LocalDate endDate) {
        if (startDate != null && endDate != null){
            if (endDate.isBefore(startDate)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}

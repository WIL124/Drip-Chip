package com.example.dripchipsystem.animalVisitedLocation.repository;

import com.example.dripchipsystem.animal.model.Animal;
import com.example.dripchipsystem.animalVisitedLocation.model.AnimalVisitedLocation;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AnimalVisitedLocationRepositoryCustom {
    List<AnimalVisitedLocation> search(Animal animal, LocalDateTime startDateTime, LocalDateTime endDateTime,
                                       int from, int size);
}

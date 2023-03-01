package com.example.dripchipsystem.repo.impl;

import com.example.dripchipsystem.model.Animal;
import com.example.dripchipsystem.model.AnimalVisitedLocation;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AnimalVisitedLocationRepositoryCustom {
    List<AnimalVisitedLocation> search(Animal animal, LocalDateTime startDateTime, LocalDateTime endDateTime,
                                       int from, int size);
}

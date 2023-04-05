package com.example.dripchipsystem.animalVisitedLocation.repository;

import com.example.dripchipsystem.animalVisitedLocation.model.AnimalVisitedLocation;
import com.example.dripchipsystem.common.repository.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface AnimalVisitedLocationRepository
        extends CommonRepository<AnimalVisitedLocation> {
    Stream<AnimalVisitedLocation> streamAll();
}
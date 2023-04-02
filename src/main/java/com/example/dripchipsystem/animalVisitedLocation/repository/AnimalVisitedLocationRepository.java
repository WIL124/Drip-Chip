package com.example.dripchipsystem.animalVisitedLocation.repository;

import com.example.dripchipsystem.common.repository.CommonRepository;
import com.example.dripchipsystem.animalVisitedLocation.model.AnimalVisitedLocation;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalVisitedLocationRepository
        extends CommonRepository<AnimalVisitedLocation>, AnimalVisitedLocationRepositoryCustom {
}

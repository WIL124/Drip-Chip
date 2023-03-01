package com.example.dripchipsystem.repo;

import com.example.dripchipsystem.model.AnimalVisitedLocation;
import com.example.dripchipsystem.repo.impl.AnimalVisitedLocationRepositoryCustom;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalVisitedLocationRepository
        extends CommonRepository<AnimalVisitedLocation>, AnimalVisitedLocationRepositoryCustom {
}

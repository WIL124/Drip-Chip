package com.example.dripchipsystem.repo;

import com.example.dripchipsystem.model.AnimalVisitedLocation;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalVisitedLocationRepository
        extends CommonRepository<AnimalVisitedLocation>, AnimalVisitedLocationRepositoryCustom{
}

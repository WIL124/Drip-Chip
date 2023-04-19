package com.example.dripchipsystem.location.repository;

import com.example.dripchipsystem.common.repository.CommonRepository;
import com.example.dripchipsystem.location.model.LocationPoint;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends CommonRepository<LocationPoint> {
    Optional<LocationPoint> findByLatitudeAndLongitude(Double latitude, Double longitude);
}

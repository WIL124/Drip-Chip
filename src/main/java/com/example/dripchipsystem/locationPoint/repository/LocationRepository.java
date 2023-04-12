package com.example.dripchipsystem.locationPoint.repository;

import com.example.dripchipsystem.common.repository.CommonRepository;
import com.example.dripchipsystem.locationPoint.model.LocationPoint;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends CommonRepository<LocationPoint> {
    Optional<LocationPoint> findByLatitudeAndLongitude(Double latitude, Double longitude);
}

package com.example.dripchipsystem.repo;

import com.example.dripchipsystem.model.LocationPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CommonRepository<LocationPoint> {
}

package com.example.dripchipsystem.service.impl;

import com.example.dripchipsystem.model.LocationPoint;
import com.example.dripchipsystem.repo.LocationRepository;
import com.example.dripchipsystem.service.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class LocationService extends AbstractService<LocationPoint, LocationRepository> {
    public LocationService(LocationRepository repository) {
        super(repository);
    }
}

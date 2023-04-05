package com.example.dripchipsystem.locationPoint.service;

import com.example.dripchipsystem.common.service.AbstractService;
import com.example.dripchipsystem.common.service.CommonService;
import com.example.dripchipsystem.locationPoint.dto.LocationPointDto;
import com.example.dripchipsystem.locationPoint.mapper.LocationPointMapper;
import com.example.dripchipsystem.locationPoint.model.LocationPoint;
import com.example.dripchipsystem.locationPoint.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationService
        extends AbstractService<LocationPoint, LocationRepository, LocationPointMapper, LocationPointDto>
        implements CommonService<LocationPointDto> {
    public LocationService(LocationRepository repository, LocationPointMapper locationPointMapper) {
        super(repository, locationPointMapper);
    }
}

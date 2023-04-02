package com.example.dripchipsystem.service.impl;

import com.example.dripchipsystem.dto.childs.LocationPointDto;
import com.example.dripchipsystem.mapper.impl.LocationPointMapper;
import com.example.dripchipsystem.model.LocationPoint;
import com.example.dripchipsystem.repo.LocationRepository;
import com.example.dripchipsystem.service.AbstractService;
import com.example.dripchipsystem.service.CommonService;
import org.springframework.stereotype.Service;

@Service
public class LocationService
        extends AbstractService<LocationPoint, LocationRepository, LocationPointMapper, LocationPointDto>
        implements CommonService<LocationPointDto> {
    public LocationService(LocationRepository repository, LocationPointMapper locationPointMapper) {
        super(repository, locationPointMapper);
    }
}

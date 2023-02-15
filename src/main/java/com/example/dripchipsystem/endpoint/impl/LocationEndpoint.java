package com.example.dripchipsystem.endpoint.impl;

import com.example.dripchipsystem.dto.Dto;
import com.example.dripchipsystem.dto.LocationPointDto;
import com.example.dripchipsystem.endpoint.AbstractEndpoint;
import com.example.dripchipsystem.model.LocationPoint;
import com.example.dripchipsystem.service.impl.LocationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
public class LocationEndpoint extends AbstractEndpoint<LocationPoint, LocationService> {
    public LocationEndpoint(LocationService service) {
        super(service);
    }

    @Override
    protected Dto<LocationPoint> toDto(LocationPoint entity) {
        return LocationPointDto.builder()
                .id(entity.getId())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .build();
    }
}

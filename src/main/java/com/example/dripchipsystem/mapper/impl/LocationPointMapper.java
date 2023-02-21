package com.example.dripchipsystem.mapper.impl;

import com.example.dripchipsystem.dto.impl.LocationPointDto;
import com.example.dripchipsystem.mapper.AbstractMapper;
import com.example.dripchipsystem.model.LocationPoint;
import org.springframework.stereotype.Component;

@Component
public class LocationPointMapper extends AbstractMapper<LocationPoint, LocationPointDto> {
    @Override
    public LocationPoint createEntityFromDto(LocationPointDto dto) {
        return LocationPoint.builder()
                .id(dto.getId())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();
    }

    @Override
    public LocationPointDto toDto(LocationPoint dto) {
        return LocationPointDto.builder()
                .id(dto.getId())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();
    }
}

package com.example.dripchipsystem.location.mapper;

import com.example.dripchipsystem.common.mapper.Mapper;
import com.example.dripchipsystem.location.dto.LocationPointDto;
import com.example.dripchipsystem.location.model.LocationPoint;
import org.springframework.stereotype.Component;

@Component
public class LocationPointMapper implements Mapper<LocationPoint, LocationPointDto> {
    @Override
    public LocationPoint entityFromDto(LocationPointDto dto) {
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

    @Override
    public void updateEntityFromDto(LocationPoint entity, LocationPointDto dto) {
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
    }
}

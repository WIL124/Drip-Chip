package com.example.dripchipsystem.mapper.impl;

import com.example.dripchipsystem.dto.childs.AnimalVisitedLocationDto;
import com.example.dripchipsystem.mapper.Mapper;
import com.example.dripchipsystem.model.AnimalVisitedLocation;
import org.springframework.stereotype.Component;

@Component
public class AnimalVisitedLocationMapper
        implements Mapper<AnimalVisitedLocation, AnimalVisitedLocationDto> {
    @Override
    public AnimalVisitedLocation entityFromDto(AnimalVisitedLocationDto dto) {
        return null;
    }

    @Override
    public AnimalVisitedLocationDto toDto(AnimalVisitedLocation entity) {
        return AnimalVisitedLocationDto.builder()
                .dateTimeOfVisitLocationPoint(entity.getDateTimeOfVisitLocationPoint())
                .id(entity.getId())
                .locationPointId(entity.getLocationPoint().getId())
                .build();
    }

    @Override
    @Deprecated
    public void updateEntityFromDto(AnimalVisitedLocation entity, AnimalVisitedLocationDto dto) {
    }
}

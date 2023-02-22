package com.example.dripchipsystem.mapper.impl;

import com.example.dripchipsystem.dto.impl.AnimalVisitedLocationDto;
import com.example.dripchipsystem.mapper.AbstractMapper;
import com.example.dripchipsystem.model.AnimalVisitedLocation;
import org.springframework.stereotype.Component;

@Component
public class AnimalVisitedLocationMapper
        extends AbstractMapper<AnimalVisitedLocation, AnimalVisitedLocationDto> {
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
    public void updateEntityFromDto(AnimalVisitedLocation entity, AnimalVisitedLocationDto dto) {

    }
}

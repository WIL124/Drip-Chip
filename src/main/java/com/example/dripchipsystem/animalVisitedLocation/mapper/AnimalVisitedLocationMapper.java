package com.example.dripchipsystem.animalVisitedLocation.mapper;

import com.example.dripchipsystem.animalVisitedLocation.dto.AnimalVisitedLocationDto;
import com.example.dripchipsystem.common.mapper.Mapper;
import com.example.dripchipsystem.animalVisitedLocation.model.AnimalVisitedLocation;
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

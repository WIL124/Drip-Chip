package com.example.dripchipsystem.visit.mapper;

import com.example.dripchipsystem.visit.dto.AnimalVisitedLocationDto;
import com.example.dripchipsystem.common.mapper.Mapper;
import com.example.dripchipsystem.visit.model.AnimalVisitedLocation;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneId;

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
                .dateTimeOfVisitLocationPoint(OffsetDateTime.ofInstant(entity.getDateTimeOfVisitLocationPoint().toInstant(), ZoneId.systemDefault()))
                .id(entity.getId())
                .locationPointId(entity.getLocationPoint().getId())
                .build();
    }

    @Override
    @Deprecated
    public void updateEntityFromDto(AnimalVisitedLocation entity, AnimalVisitedLocationDto dto) {
    }
}

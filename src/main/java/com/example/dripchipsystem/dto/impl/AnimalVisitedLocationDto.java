package com.example.dripchipsystem.dto.impl;

import com.example.dripchipsystem.dto.AbstractDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AnimalVisitedLocationDto extends AbstractDto {
    private LocalDateTime dateTimeOfVisitLocationPoint;
    private Long locationPointId;

    @Builder
    public AnimalVisitedLocationDto(Long id, LocalDateTime dateTimeOfVisitLocationPoint, Long locationPointId) {
        super(id);
        this.dateTimeOfVisitLocationPoint = dateTimeOfVisitLocationPoint;
        this.locationPointId = locationPointId;
    }
}

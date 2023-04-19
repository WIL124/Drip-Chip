package com.example.dripchipsystem.visit.dto;

import com.example.dripchipsystem.common.dto.AbstractDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AnimalVisitedLocationDto extends AbstractDto {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dateTimeOfVisitLocationPoint;
    @NotNull
    @Positive
    private Long locationPointId;

    @Builder
    public AnimalVisitedLocationDto(Long id, OffsetDateTime dateTimeOfVisitLocationPoint, Long locationPointId) {
        super(id);
        this.dateTimeOfVisitLocationPoint = dateTimeOfVisitLocationPoint;
        this.locationPointId = locationPointId;
    }
}

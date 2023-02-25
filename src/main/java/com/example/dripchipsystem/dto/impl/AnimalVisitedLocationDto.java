package com.example.dripchipsystem.dto.impl;

import com.example.dripchipsystem.dto.AbstractDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AnimalVisitedLocationDto extends AbstractDto {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateTimeOfVisitLocationPoint;
    @NotNull
    @Positive
    private Long locationPointId;

    @Builder
    public AnimalVisitedLocationDto(Long id, LocalDateTime dateTimeOfVisitLocationPoint, Long locationPointId) {
        super(id);
        this.dateTimeOfVisitLocationPoint = dateTimeOfVisitLocationPoint;
        this.locationPointId = locationPointId;
    }
}
